package us.rengo.cookies.commands.player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Syntax;
import co.aikar.commands.contexts.OnlinePlayer;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GamemodeCommand extends BaseCommand {

    @CommandAlias("gm|gamemode")
    @Syntax("<gamemode>")
    @CommandPermission("rengo.build")
    public void onGamemode(Player player, String gamemode) {
        switch (gamemode.toLowerCase()) {
            case "c":
            case "creative":
                player.setGameMode(GameMode.CREATIVE);
                break;

            case "s":
            case "survival":
                player.setGameMode(GameMode.SURVIVAL);
                break;

            default:
                player.sendMessage(ChatColor.AQUA + "The specified gamemode is non-existent.");
                break;
        }
    }

    @CommandAlias("gm|gamemode")
    @Syntax("<player> <gamemode>")
    @CommandCompletion("@players")
    @CommandPermission("rengo.build")
    public void onGamemode(Player player, OnlinePlayer targetOnlinePlayer, String gamemode) {
        Player target = targetOnlinePlayer.getPlayer();

        switch (gamemode.toLowerCase()) {
            case "c":
            case "creative":
                target.setGameMode(GameMode.CREATIVE);
                player.sendMessage(ChatColor.DARK_AQUA + target.getName() + "'s " + ChatColor.BLUE + "gamemode was updated.");
                break;

            case "s":
            case "survival":
                target.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(ChatColor.DARK_AQUA + target.getName() + "'s " + ChatColor.BLUE + "gamemode was updated.");
                break;

            default:
                player.sendMessage(ChatColor.DARK_AQUA + "That gamemode does not exist.");
                break;
        }
    }
}
