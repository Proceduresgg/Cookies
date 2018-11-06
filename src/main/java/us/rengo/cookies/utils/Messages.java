package us.rengo.cookies.utils;

import lombok.Getter;
import us.rengo.cookies.CoreConfiguration;

@Getter
public enum Messages {
    PLAYER_NOT_ONLINE(CoreConfiguration.serverColorBright + "The player you specified is not online"),;

    private String message;

    Messages(String message) {
        this.message = message;
    }
}
