package us.rengo.cookies.config;

import org.bukkit.ChatColor;

import java.util.regex.Pattern;

public class CoreConfiguration {

    public static ChatColor SERVER_COLOR_BRIGHT = ChatColor.DARK_AQUA;
    public static ChatColor SERVER_COLOR_DIM = ChatColor.GRAY;

    public static final Pattern URL_REGEX = Pattern.compile("^(http://www\\.|https://www\\.|http://|https://)?[a-z0-9]+([\\-.][a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(/.*)?$");
}
