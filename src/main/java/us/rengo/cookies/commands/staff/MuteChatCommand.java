package us.rengo.cookies.commands.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Dependency;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import us.rengo.cookies.CookiesPlugin;

@CommandPermission("rengo.staff")
public class MuteChatCommand extends BaseCommand {

    @Dependency private CookiesPlugin plugin;

    @CommandAlias("mutechat")
    public void onMute(Player player) {
        this.plugin.setChatEnabled(!this.plugin.isChatEnabled());
        Bukkit.broadcastMessage(ChatColor.BLUE + "Chat has been disabled by " + ChatColor.DARK_AQUA + player.getName() + ".");
    }
}
