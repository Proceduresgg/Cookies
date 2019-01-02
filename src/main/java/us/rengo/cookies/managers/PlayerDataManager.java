package us.rengo.cookies.managers;

import lombok.Getter;
import org.bukkit.entity.Player;
import us.rengo.cookies.player.PlayerProfile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager {

    @Getter private Map<UUID, PlayerProfile> playerDataMap = new HashMap<>();

    public PlayerProfile getData(UUID uuid) {
        PlayerProfile playerProfile = this.playerDataMap.get(uuid);

        if (playerProfile == null) {
            playerProfile = this.playerDataMap.put(uuid, new PlayerProfile(uuid));
        }

        return playerProfile;
    }

    public PlayerProfile getData(Player player) {
        return this.getData(player.getUniqueId());
    }
}
