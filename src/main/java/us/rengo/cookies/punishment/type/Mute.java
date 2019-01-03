package us.rengo.cookies.punishment.type;

import us.rengo.cookies.punishment.Punishment;

import java.util.UUID;

public class Mute extends Punishment {

    public Mute(UUID addedBy, UUID who) {
        super(addedBy, who, 49, "FJK");
    }

    @Override
    public boolean active() {
        return false;
    }

    @Override
    public boolean temporary() {
        return false;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
