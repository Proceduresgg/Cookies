package us.rengo.cookies.commands.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import us.rengo.cookies.CookiesPlugin;
import us.rengo.cookies.config.CoreConfiguration;

@CommandAlias("filter")
@CommandPermission("rengo.manager")
public class FilterCommand extends BaseCommand {

    @Dependency private CookiesPlugin plugin;

    @Subcommand("add")
    public void onAdd(Player player, String word) {
        if (this.plugin.getFilterManager().getFilteredWords().contains(word)) {
            player.sendMessage(ChatColor.DARK_AQUA + "That word is already filtered.");
            return;
        }

        this.plugin.getFilterManager().getFilteredWords().add(word);
        player.sendMessage(ChatColor.DARK_AQUA + "The specified word has been filtered.");
    }
}
