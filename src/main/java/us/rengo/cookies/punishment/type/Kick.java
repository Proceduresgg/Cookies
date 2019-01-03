package us.rengo.cookies.punishment.type;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import us.rengo.cookies.punishment.Punishment;

import java.util.UUID;

@Getter
public class Kick extends Punishment {

    private long endStamp;

    public Kick(UUID addedBy, UUID who, String reason, long endStamp) {
        super(addedBy, who, reason);

        this.endStamp = endStamp;
    }

    @Override
    public boolean active() {
        return this.endStamp > System.currentTimeMillis();
    }

    @Override
    public boolean temporary() {
        return this.endStamp != Long.MAX_VALUE;
    }

    @Override
    public String getMessage() {
        if (this.active()) {
            return ChatColor.RED + "You are " + (this.temporary() ? "temporarily " : "permanently ") + "muted for " + this.getReason() + ". \nAppeal at rengo.us/appeal.";
        }

        return "";
    }
}
