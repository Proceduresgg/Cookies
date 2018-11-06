package us.rengo.cookies.managers.type;

import org.bukkit.entity.Player;
import us.rengo.cookies.player.impl.PlayerData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager {

    private Map<UUID, PlayerData> playerDataMap = new HashMap<>();

    public PlayerData getData(UUID uuid) {
        PlayerData playerData = this.playerDataMap.get(uuid);

        if (playerData == null) {
            playerData = this.playerDataMap.put(uuid, new PlayerData(uuid));
        }

        return playerData;
    }

    public PlayerData getData(Player player) {
        return this.getData(player.getUniqueId());
    }
}
