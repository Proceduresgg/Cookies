package us.rengo.cookies.commands.punishment;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.contexts.OnlinePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import us.rengo.cookies.CookiesPlugin;
import us.rengo.cookies.player.PlayerProfile;
import us.rengo.cookies.punishment.BanType;
import us.rengo.cookies.punishment.type.Ban;

@CommandPermission("rengo.mod")
public class BanCommand extends BaseCommand {

    @Dependency private CookiesPlugin plugin;

    @CommandAlias("ban")
    @CommandCompletion("@players")
    @Syntax("<player> <reason>")
    public void onBan(Player player, OnlinePlayer targetOnlinePlayer, String reason) {
        Player target = targetOnlinePlayer.getPlayer();
        PlayerProfile targetProfile = this.plugin.getPlayerDataManager().getData(target);
        Ban ban = new Ban(player.getUniqueId(), target.getUniqueId(), BanType.BAN, Long.MAX_VALUE, reason);

        targetProfile.getPunishments().add(ban);
        targetProfile.save();

        target.kickPlayer(ban.getMessage());
        Bukkit.broadcastMessage(ChatColor.DARK_RED + target.getName() + ChatColor.GOLD + " has been permanently banned by " + player.getName() + " for " + ChatColor.DARK_RED + reason + ChatColor.GOLD + ".");
    }

    @CommandAlias("unban")
    @CommandCompletion("@players")
    @Syntax("<player> <reason>")
    public void onUnban(Player player, OfflinePlayer offlinePlayer, String reason) {
        new PlayerProfile(offlinePlayer.getUniqueId()).load().whenComplete(((profile, throwable) -> {
            if (throwable != null) {
                player.sendMessage(ChatColor.RED + "Error Retrieving Data");
                throwable.printStackTrace();

            } else if (!profile.isBanned()) {
                player.sendMessage(ChatColor.RED + "That player is not banned.");

            } else {
                profile.getPunishments()
                        .stream()
                        .filter(punishment -> punishment instanceof Ban)
                        .forEach(punishment -> punishment.setActive(false));

                player.sendMessage(ChatColor.YELLOW + "That player has been unbanned.");
                profile.save();
            }
        }));
    }
}
