package us.rengo.cookies.utils;

import lombok.Getter;
import org.bukkit.ChatColor;

@Getter
public enum Messages {

    PLAYER_NOT_ONLINE(ChatColor.DARK_AQUA + "The player you specified is not online"),;

    private String message;

    Messages(String message) {
        this.message = message;
    }
}
