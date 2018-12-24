package us.rengo.cookies.listeners;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import us.rengo.cookies.CookiesPlugin;
import us.rengo.cookies.player.PlayerData;

@AllArgsConstructor
public class PlayerListener implements Listener {

    private final CookiesPlugin plugin;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerData data = this.plugin.getPlayerDataManager().getData(player);

        event.setJoinMessage(null);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerData data = this.plugin.getPlayerDataManager().getPlayerDataMap().remove(player.getUniqueId());
    }
}
