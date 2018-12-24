package us.rengo.cookies.listeners;

import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import us.rengo.cookies.CookiesPlugin;

@AllArgsConstructor
public class ChatListener implements Listener {

    private final CookiesPlugin plugin;

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("rengo.staff")) {
            if (!this.plugin.isChatEnabled()) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.DARK_AQUA + "Chat is currently disabled.");
                return;
            }

            String message = event.getMessage();

            if (this.plugin.getFilterManager().testMessage(message)) {
                String fullMessage = ChatColor.GREEN + player.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE + message;

                player.sendMessage(fullMessage);
                event.setCancelled(true);

                this.plugin.getFilterManager().alertFilteredMessage(fullMessage);
            }
        }
    }
}
