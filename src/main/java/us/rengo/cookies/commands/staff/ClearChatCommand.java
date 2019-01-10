package us.rengo.cookies.commands.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandPermission("rengo.staff")
public class ClearChatCommand extends BaseCommand {

    @CommandAlias("cc|clearchat")
    public void onClear(Player player) {
            Bukkit.getOnlinePlayers()
                    .stream()
                    .filter(onlinePlayer -> !onlinePlayer.hasPermission("rengo.staff"))
                    .forEach(onlinePlayer -> onlinePlayer.sendMessage(StringUtils.repeat(" \n", 100)));

        Bukkit.broadcastMessage(ChatColor.DARK_AQUA + player.getName() + ChatColor.BLUE + " has cleared the chat.");
    }
}
