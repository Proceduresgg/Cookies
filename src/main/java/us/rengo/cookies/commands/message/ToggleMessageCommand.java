package us.rengo.cookies.commands.message;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import us.rengo.cookies.CookiesPlugin;
import us.rengo.cookies.player.PlayerProfile;

public class ToggleMessageCommand extends BaseCommand {

    @Dependency private CookiesPlugin plugin;

    @CommandAlias("togglemessages|tpm")
    public void onDefault(Player player) {
        PlayerProfile playerProfile = this.plugin.getPlayerDataManager().getData(player);

        playerProfile.setMessagesEnabled(!playerProfile.isMessagesEnabled());
        player.sendMessage(ChatColor.DARK_AQUA+ "Your messages have been " + (playerProfile.isMessagesEnabled() ? "disabled" : "enabled") + ".");
    }
}
