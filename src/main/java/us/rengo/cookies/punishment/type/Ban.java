package us.rengo.cookies.punishment.type;

import com.google.gson.JsonObject;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import us.rengo.cookies.punishment.AbstractPunishment;
import us.rengo.cookies.punishment.BanType;

import java.util.UUID;

@Getter
public class Ban extends AbstractPunishment {

    private final BanType type;

    private long endStamp;

    public Ban(UUID addedBy, UUID who, BanType type, long timeStamp, long endStamp, String reason) {
        super(addedBy, who, timeStamp, reason);
        this.type = type;
        this.endStamp = endStamp;
    }

    public Ban(UUID addedBy, UUID who, BanType type, long endStamp, String reason) {
        super(addedBy, who, reason);
        this.type = type;
        this.endStamp = endStamp;
    }

    @Override
    public boolean active() {
        if (this.type == BanType.BLACKLIST) {
            return true;

        } else return this.endStamp > System.currentTimeMillis();
    }

    @Override
    public boolean temporary() {
        if (this.endStamp == Long.MAX_VALUE) {
            return false;

        } else return this.type != BanType.BLACKLIST;
    }

    @Override
    public String getMessage() {
        if (this.active()) {
            return ChatColor.GOLD + "You are " + (this.temporary() ? "temporarily " : "permanently ") + "banned for " + ChatColor.DARK_RED + this.getReason() + ChatColor.GOLD + "." + ChatColor.YELLOW + "\nrengo.us/appeal";
        }

        return "";
    }

    public static JsonObject serializeBan(Ban ban) {
        JsonObject object = new JsonObject();
        object.addProperty("added-by", ban.getAddedBy().toString());
        object.addProperty("who", ban.getWho().toString());
        object.addProperty("active", ban.isActive());
        object.addProperty("reason", ban.getReason());
        object.addProperty("type", ban.getType().toString());
        object.addProperty("timestamp", ban.getTimeStamp());
        object.addProperty("endstamp", ban.getEndStamp());

        return object;
    }

    public static Ban deserializeBan(JsonObject object) {
        Ban ban = new Ban(
                UUID.fromString(object.get("added-by").getAsString()),
                UUID.fromString(object.get("who").getAsString()),
                BanType.valueOf(object.get("type").getAsString()),
                object.get("timestamp").getAsLong(),
                object.get("endstamp").getAsLong(),
                object.get("reason").getAsString()
        );

        ban.setActive(object.get("active").getAsBoolean());
        return ban;
    }
}
