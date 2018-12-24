package us.rengo.cookies.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.ChatColor;

import java.util.UUID;

@Getter @Setter
@RequiredArgsConstructor
public class PlayerData {

    private final UUID identifier;

    private UUID latestMessanger;

    private ChatColor messageColor = ChatColor.LIGHT_PURPLE;

    private boolean messagesEnabled = true;
}
