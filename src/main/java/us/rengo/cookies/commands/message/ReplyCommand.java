package us.rengo.cookies.commands.message;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import us.rengo.cookies.CookiesPlugin;
import us.rengo.cookies.player.PlayerProfile;
import us.rengo.cookies.utils.Messages;
import us.rengo.milk.MilkPlugin;

public class ReplyCommand extends BaseCommand {

    @Dependency private CookiesPlugin plugin;

    @CommandAlias("r|reply")
    @Syntax("<message>")
    public void onReply(Player player, String message) {
        PlayerProfile playerProfile = this.plugin.getPlayerDataManager().getData(player);
        Player target = Bukkit.getServer().getPlayer(playerProfile.getRecent());

        if (target == null) {
            player.sendMessage(Messages.PLAYER_NOT_ONLINE.getMessage());
            return;
        }

        PlayerProfile targetData = this.plugin.getPlayerDataManager().getData(target);

        if (!targetData.isMessagesEnabled()) {
            player.sendMessage(ChatColor.DARK_AQUA + "The specified player does not have messages enabled.");
            return;

        } else if (!playerProfile.isMessagesEnabled()) {
            player.sendMessage(ChatColor.DARK_AQUA + "You do not have messages enabled.");
            return;
        }

        String toPlayer = playerProfile.getMessageColor() + "(To " +
                MilkPlugin.getInstance().getProfileManager().getProfile(target).getRank().getPrefix()
                + target.getName() + playerProfile.getMessageColor() + ") " + playerProfile.getMessageColor();

        String toTarget = targetData.getMessageColor() + "(From " +
                MilkPlugin.getInstance().getProfileManager().getProfile(player).getRank().getPrefix()
                + player.getName() + targetData.getMessageColor() + ") " + targetData.getMessageColor();

        player.sendMessage(toPlayer + message);
        target.sendMessage(toTarget + message);
    }
}
