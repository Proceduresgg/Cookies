package us.rengo.cookies.commands.player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TimeCommand extends BaseCommand {

    @CommandAlias("day")
    public void onDay(Player player) {
        player.setPlayerTime(6000, false);
        player.sendMessage(ChatColor.YELLOW + "Set time to day.");
    }

    @CommandAlias("night")
    public void onNight(Player player) {
        player.setPlayerTime(18000, false);
        player.sendMessage(ChatColor.BLUE + "Set time to night.");
    }
}
