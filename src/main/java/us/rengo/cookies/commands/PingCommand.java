package us.rengo.cookies.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CatchUnknown;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import us.rengo.cookies.CoreConfiguration;
import us.rengo.cookies.utils.PingUtil;

@CommandAlias("ping")
public class PingCommand extends BaseCommand {

    @Default
    @CatchUnknown
    @CommandCompletion("@players")
    public void onDefault(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(CoreConfiguration.serverColorBright + "Your Ping: " + CoreConfiguration.serverColorDim + PingUtil.getPing(player));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(CoreConfiguration.serverColorBright + "That player is not online.");
            return;
        }

        player.sendMessage(CoreConfiguration.serverColorBright + target.getName() + "'s Ping: " + CoreConfiguration.serverColorDim + PingUtil.getPing(target));
    }
}