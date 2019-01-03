package us.rengo.cookies.managers;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import us.rengo.cookies.config.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

@Getter
public class FilterManager {

    private List<String> filteredWords = new ArrayList<>();

    public boolean testMessage(String message) {
        if (testRegex(message)) {
            return true;

        } else return testWords(message);
    }

    private boolean testRegex(String message) {
        try {
            Matcher matcher = Configuration.URL_REGEX.matcher(message);
            return matcher.matches();

        } catch (RuntimeException exception) {
            return false;
        }
    }

    private boolean testWords(String message) {
        for (String filteredWord : this.filteredWords) {
            if (!message.toLowerCase().contains(filteredWord.toLowerCase())) {
                continue;
            }
            return true;
        }
        return false;
    }

    public void alertFilteredMessage(String message) {
        String prefix = ChatColor.RED + "[Filter]";

        Bukkit.getServer().getOnlinePlayers().stream()
                .filter(onlinePlayer -> onlinePlayer.hasPermission("rengo.staff"))
                .forEach(staffMember -> staffMember.sendMessage(prefix + " " + message));
    }
}
