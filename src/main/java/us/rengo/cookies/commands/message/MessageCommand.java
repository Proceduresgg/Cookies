package us.rengo.cookies.commands.message;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.contexts.OnlinePlayer;
import org.bukkit.entity.Player;
import us.rengo.cookies.CookiesPlugin;
import us.rengo.cookies.player.PlayerData;
import us.rengo.milk.MilkPlugin;

public class MessageCommand extends BaseCommand {

    @Dependency private CookiesPlugin plugin;

    @CommandAlias("message|msg|tell|w|whisper|m|t")
    @Syntax("<target> <message>")
    @CommandCompletion("@players")
    public void onMessage(Player player, @Conditions("messages-enabled") OnlinePlayer targetOnlinePlayer, String message) {
        Player target = targetOnlinePlayer.getPlayer();
        PlayerData targetData = this.plugin.getPlayerDataManager().getData(target);
        PlayerData playerData = this.plugin.getPlayerDataManager().getData(player);

        String toPlayer = playerData.getMessageColor() + "(To " +
                MilkPlugin.getInstance().getProfileManager().getProfile(target).getRank().getPrefix()
                + target.getName() + playerData.getMessageColor() + ") " + playerData.getMessageColor();

        String toTarget = targetData.getMessageColor() + "(From " +
                MilkPlugin.getInstance().getProfileManager().getProfile(player).getRank().getPrefix()
                + player.getName() + targetData.getMessageColor() + ") " + targetData.getMessageColor();

        player.sendMessage(toPlayer + message);
        target.sendMessage(toTarget + message);

        targetData.setLatestMessanger(player.getUniqueId());
        playerData.setLatestMessanger(target.getUniqueId());
    }
}
