package us.rengo.cookies.commands.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandPermission("rengo.admin")
public class BroadcastCommand extends BaseCommand {

    @CommandAlias("broadcast|bc|say|me|alert")
    public void onBroadcast(Player player, String message) {
        Bukkit.broadcastMessage(ChatColor.BLUE + "(" + ChatColor.DARK_AQUA + "Announcement" + ChatColor.BLUE + ") " + ChatColor.WHITE + message);
    }
}
