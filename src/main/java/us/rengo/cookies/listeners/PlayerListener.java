package us.rengo.cookies.listeners;

import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import us.rengo.cookies.CookiesPlugin;
import us.rengo.cookies.player.PlayerProfile;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class PlayerListener implements Listener {

    private final CookiesPlugin plugin;

    private static final String[] PERMISSIONS = {"help", "plugins", "version", "me", "list", "say"};

    @EventHandler
    public void onPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        CompletableFuture<PlayerProfile> load = new PlayerProfile(event.getUniqueId()).load();
        try {
            PlayerProfile profile = load.get();
            if (profile.isBanned()) {
                event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, profile.getActiveBan().getMessage());
            } else {
                this.plugin.getPlayerDataManager().getPlayerDataMap().put(profile.getIdentifier(), profile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ChatColor.RED + "Error Loading Data Try Again");
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        Player player = event.getPlayer();

        Arrays.stream(PERMISSIONS)
                .forEach(permission -> player.addAttachment(this.plugin, "bukkit.command." + permission, false));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        Player player = event.getPlayer();
        this.plugin.getPlayerDataManager().getPlayerDataMap().remove(player.getUniqueId()).save();
    }

    @EventHandler
    public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
        if (!event.getPlayer().isOp()) {
            if (event.getMessage().toLowerCase().contains("minecraft:me")) {
                event.setCancelled(true);
            }
        }
    }
}
