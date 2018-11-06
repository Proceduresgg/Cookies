package us.rengo.cookies.managers;

import lombok.Getter;
import us.rengo.cookies.managers.type.PlayerDataManager;

@Getter
public class ManagerHandler {

    private PlayerDataManager playerDataManager;

    public ManagerHandler() {
        this.playerDataManager = new PlayerDataManager();
    }
}
