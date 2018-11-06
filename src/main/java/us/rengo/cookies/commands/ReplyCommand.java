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

@CommandAlias("r|reply")
public class ReplyCommand extends BaseCommand {

    @Dependency
    private ManagerHandler managerHandler;

    @Default
    @CatchUnknown
    @CommandCompletion("@players")
    public void onDefault(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(CoreConfiguration.serverColorBright + "Usage: /r [message]");
            return;
        }

        PlayerData playerData = this.managerHandler.getPlayerDataManager().getData(player);

        Player target = Bukkit.getServer().getPlayer(playerData.getLatestMessanger());

        if (target == null) {
            player.sendMessage(Messages.PLAYER_NOT_ONLINE.getMessage());
            return;
        }

        PlayerData targetData = this.managerHandler.getPlayerDataManager().getData(target);

        if (!targetData.isMessagesEnabled()) {
            player.sendMessage(CoreConfiguration.serverColorBright + "The specified player does not have messages enabled.");
            return;

        } else if (!playerData.isMessagesEnabled()) {
            player.sendMessage(CoreConfiguration.serverColorBright + "You do not have messages enabled.");
            return;
        }

        String toPlayer = playerData.getMessageColor() + "(To" + ChatColor.GREEN + target.getName() + playerData.getMessageColor() + ") " + playerData.getMessageColor();

        String toTarget = targetData.getMessageColor() + "(From" + ChatColor.GREEN + player.getName() + targetData.getMessageColor() + ") " + targetData.getMessageColor();

        StringBuilder message = new StringBuilder();

        for (int x = 0; x < args.length; x++) {
            message.append(args[x]).append(" ");
        }

        player.sendMessage(toPlayer + message.toString());
        target.sendMessage(toTarget + message.toString());
    }
}
