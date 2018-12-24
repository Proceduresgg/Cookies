package us.rengo.cookies.commands.other;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import me.joeleoli.BukkitReflection;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandPermission("rengo.developer")
public class SetSlotsCommand extends BaseCommand {

    @CommandAlias("setslots")
    public void onSetSlots(Player player, int slots) {
        BukkitReflection.setMaxPlayers(player.getServer(), slots);
        player.sendMessage(ChatColor.DARK_AQUA + "Slots have been set to " + slots);
    }
}
