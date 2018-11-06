package us.rengo.cookies.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Dependency;
import org.bukkit.entity.Player;
import us.rengo.cookies.CoreConfiguration;
import us.rengo.cookies.managers.ManagerHandler;
import us.rengo.cookies.player.impl.PlayerData;

@CommandAlias("togglemessages|tpm")
public class ToggleMessageCommand extends BaseCommand {

    @Dependency
    private ManagerHandler managerHandler;

    @Default
    public void onDefault(Player player) {
        PlayerData playerData = this.managerHandler.getPlayerDataManager().getData(player);

        playerData.setMessagesEnabled(!playerData.isMessagesEnabled());

        player.sendMessage(CoreConfiguration.serverColorBright + "Your messages have been " + (playerData.isMessagesEnabled() ? "disabled" : "enabled") + ".");
    }
}
