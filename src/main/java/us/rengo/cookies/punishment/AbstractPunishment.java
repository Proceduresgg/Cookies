package us.rengo.cookies.punishment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public abstract class AbstractPunishment {

    private final UUID addedBy, who;

    private long timeStamp;

    private boolean active = true;

    private String reason;

    public AbstractPunishment(UUID addedBy, UUID who, long timeStamp, String reason) {
        this.reason = reason;
        this.addedBy = addedBy;
        this.who = who;
        this.timeStamp = timeStamp;
    }

    public AbstractPunishment(UUID addedBy, UUID who, String reason) {
        this(addedBy, who, System.currentTimeMillis(), reason);
    }

    public abstract boolean active();

    public abstract boolean temporary();

    public abstract String getMessage();
}
