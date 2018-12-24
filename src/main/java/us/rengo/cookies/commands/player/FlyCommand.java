package us.rengo.cookies.commands.player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.contexts.OnlinePlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandPermission("rengo.admin")
public class FlyCommand extends BaseCommand {

    @CommandAlias("fly")
    public void onFly(Player player) {
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.setFlying(false);

            player.sendMessage(ChatColor.DARK_AQUA + "Your fight has been disabled.");
        } else {
            player.setAllowFlight(true);
            player.setFlying(true);

            player.sendMessage(ChatColor.DARK_AQUA + "Your fight has been enabled.");
        }
    }

    @CommandAlias("fly")
    @Syntax("<player>")
    @CommandCompletion("@players")
    public void onFly(Player player, OnlinePlayer targetOnlinePlayer) {
        Player target = targetOnlinePlayer.getPlayer();

        if (target.getAllowFlight()) {
            target.setAllowFlight(false);
            target.setFlying(false);

            target.sendMessage(ChatColor.DARK_AQUA + "Your fight has been disabled.");
            player.sendMessage(ChatColor.DARK_AQUA + target.getName() + "'s " + ChatColor.BLUE + "flight has been disabled.");
        } else {
            target.setAllowFlight(true);
            target.setFlying(true);

            target.sendMessage(ChatColor.DARK_AQUA + "Your fight has been enabled.");
            player.sendMessage(ChatColor.DARK_AQUA + target.getName() + "'s " + ChatColor.BLUE + "flight has been enabled.");
        }
    }
}
