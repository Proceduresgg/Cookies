package us.rengo.cookies.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import us.rengo.cookies.CoreConfiguration;
import us.rengo.cookies.managers.ManagerHandler;
import us.rengo.cookies.player.impl.PlayerData;
import us.rengo.cookies.utils.Messages;

@CommandAlias("message|msg|tell|w|whisper|m|t")
public class MessageCommand extends BaseCommand {

    @Dependency
    private ManagerHandler managerHandler;

    @Default
    @CatchUnknown
    @CommandCompletion("@players")
    public void onDefault(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(CoreConfiguration.serverColorBright + "Usage: /message [player] [message]");
            return;

        } else if (Bukkit.getServer().getPlayer(args[0]) == null) {
            player.sendMessage(Messages.PLAYER_NOT_ONLINE.getMessage());
            return;
        }

        Player target = Bukkit.getServer().getPlayer(args[0]);

        PlayerData targetData = this.managerHandler.getPlayerDataManager().getData(target);
        PlayerData playerData = this.managerHandler.getPlayerDataManager().getData(player);

        if (!targetData.isMessagesEnabled()) {
            player.sendMessage(CoreConfiguration.serverColorBright + "The specified player does not have their messages enabled.");
            return;
        }

        // TODO: Change the color of the players name to their rank
        String toPlayer = playerData.getMessageColor() + "(To" + ChatColor.GREEN + target.getName() + playerData.getMessageColor() + ") " + playerData.getMessageColor();

        String toTarget = targetData.getMessageColor() + "(From" + ChatColor.GREEN + player.getName() + targetData.getMessageColor() + ") " + targetData.getMessageColor();

        StringBuilder message = new StringBuilder();

        for (int x = 1; x < args.length; x++) {
            message.append(args[x]).append(" ");
        }

        player.sendMessage(toPlayer + message.toString());
        target.sendMessage(toTarget + message.toString());
    }
}
