package us.rengo.cookies.managers;

import lombok.Getter;
import org.bukkit.entity.Player;
import us.rengo.cookies.player.PlayerProfile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager {

    @Getter private Map<UUID, PlayerProfile> playerDataMap = new HashMap<>();

    public PlayerProfile getData(Player player) {
        return this.playerDataMap.get(player.getUniqueId());
    }
}
