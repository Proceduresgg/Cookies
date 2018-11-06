package us.rengo.cookies.player.impl;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;

import java.util.UUID;

@Getter
@Setter
public class PlayerData {
    private UUID identifier;
    private UUID latestMessanger;

    private ChatColor messageColor;

    private boolean messagesEnabled;

    public PlayerData(UUID identifier) {
        this.identifier = identifier;
    }
}
