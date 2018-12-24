package us.rengo.cookies.commands.player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Syntax;
import co.aikar.commands.contexts.OnlinePlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandPermission("rengo.staff")
public class ClearCommand extends BaseCommand {

    @CommandAlias("clear")
    @Syntax("<player>")
    @CommandCompletion("@players")
    public void onClear(Player player, OnlinePlayer targetOnlinePlayer) {
        Player target = targetOnlinePlayer.getPlayer();

        target.getInventory().setContents(new ItemStack[36]);
        target.getInventory().setArmorContents(new ItemStack[4]);

        player.sendMessage(ChatColor.BLUE + "Cleared " + ChatColor.DARK_AQUA + target.getName() + "'s " + ChatColor.BLUE + "inventory.");
    }
}
