package us.rengo.cookies.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import us.rengo.cookies.CoreConfiguration;

@CommandAlias("ip")
@CommandPermission("rengo.manager")
public class IpCommand extends BaseCommand {

    @Default
    @CatchUnknown
    @CommandCompletion("@players")
    public void onDefault(Player player, String[] args) {
        if (args.length == 0 || Bukkit.getPlayer(args[0]) == null) {
            player.sendMessage(CoreConfiguration.serverColorBright + "Usage: /ip [player]");
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        player.sendMessage(CoreConfiguration.serverColorBright+ target.getName() + "'s " + CoreConfiguration.serverColorDim + "IP is " + CoreConfiguration.serverColorBright + target.getAddress().getHostString());
    }
}
