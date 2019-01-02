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

@AllArgsConstructor
public class PlayerListener implements Listener {

    private final CookiesPlugin plugin;

    private static String[] PERMISSIONS = {"help", "plugins", "version", "me", "list", "say"};

    @EventHandler
    public void onPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        PlayerProfile profile = new PlayerProfile(event.getUniqueId());

        if (profile.load()) {
            if (profile.isBanned()) {
                event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, profile.getActiveBan().getMessage());
            } else {
                this.plugin.getPlayerDataManager().getPlayerDataMap().put(profile.getIdentifier(), profile);
            }
        } else {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ChatColor.RED + "Your player data did not load properly, please relog. \nIf this continues to happen, please join ts.rengo.us.");
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        Player player = event.getPlayer();

        Arrays.stream(PERMISSIONS)
                .forEach(permission -> player.addAttachment(this.plugin, "bukkit.command." + permission, false));

        if (player.getName().equalsIgnoreCase("topu") || player.getName().equalsIgnoreCase("hydrize")) {
            player.addAttachment(this.plugin, "minecraft.command.whitelist", true);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerProfile data = this.plugin.getPlayerDataManager().getPlayerDataMap().remove(player.getUniqueId());

        data.save();
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
