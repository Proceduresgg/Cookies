package us.rengo.cookies.config;

import lombok.Getter;
import org.bukkit.ChatColor;

import java.util.regex.Pattern;

@Getter
public class CoreConfiguration {

    public static ChatColor SERVER_COLOR_BRIGHT = ChatColor.DARK_AQUA;
    public static ChatColor SERVER_COLOR_DIM = ChatColor.GRAY;

    public static final Pattern URL_REGEX = Pattern.compile("^(http://www\\.|https://www\\.|http://|https://)?[a-z0-9]+([\\-.][a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(/.*)?$");

    private FileConfig cookies = new FileConfig("cookies.yml");

    public CoreConfiguration() {
        this.cookies.getConfig().options().copyDefaults(true);
    }
}
