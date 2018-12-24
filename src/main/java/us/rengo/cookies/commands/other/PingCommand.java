package us.rengo.cookies.commands.other;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.contexts.OnlinePlayer;
import me.joeleoli.BukkitReflection;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PingCommand extends BaseCommand {

    @CommandAlias("ping")
    public void onPing(Player player) {
        player.sendMessage(ChatColor.DARK_AQUA + "Your Ping: " + ChatColor.BLUE + BukkitReflection.getPing(player) + ChatColor.GRAY + " (ping rengo.us in CMD for more accuracy)");
    }

    @CommandAlias("ping")
    @CommandCompletion("@players")
    @Syntax("<player>")
    public void onPing(Player player, OnlinePlayer targetOnlinePlayer) {
        Player target = targetOnlinePlayer.getPlayer();

        player.sendMessage(ChatColor.DARK_AQUA + target.getName() + "'s Ping: " + ChatColor.BLUE + BukkitReflection.getPing(target));
    }
}
