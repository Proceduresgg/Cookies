package us.rengo.cookies.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bson.Document;
import org.bukkit.ChatColor;
import us.rengo.cookies.CookiesPlugin;
import us.rengo.cookies.punishment.AbstractPunishment;
import us.rengo.cookies.punishment.type.Ban;
import us.rengo.cookies.punishment.type.Mute;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@RequiredArgsConstructor
public class PlayerProfile {

    private final UUID identifier;

    private UUID recent;

    private final List<AbstractPunishment> punishments = new ArrayList<>();

    private ChatColor messageColor = ChatColor.LIGHT_PURPLE;

    private boolean messagesEnabled = true;

    public boolean isBanned() {
        for (AbstractPunishment punishment : this.punishments) {
            if (punishment instanceof Ban) {
                Ban ban = (Ban) punishment;

                if (ban.isActive())
                    return true;
            }
        }
        return false;
    }

    public boolean isMuted() {
        for (AbstractPunishment punishment : this.punishments) {
            if (punishment instanceof Mute) {
                Mute mute = (Mute) punishment;

                if (mute.isActive())
                    return true;
            }
        }
        return false;
    }

    public void save() {
        Document document = new Document("uuid", this.identifier.toString());

        JsonArray bans = new JsonArray();
        JsonArray mutes = new JsonArray();
        JsonArray kicks = new JsonArray();

        this.punishments.forEach(punishment -> {
            if (punishment instanceof Ban) {
                bans.add(Ban.serializeBan((Ban) punishment));
            }
        });

        document.append("bans", bans.toString());

        CookiesPlugin.getInstance().getMongo().getDatabase().getCollection("profiles").replaceOne(Filters.eq("uuid", this.identifier.toString()), document, new ReplaceOptions().upsert(true));
    }

    public boolean load() {
        try {
            MongoCollection<Document> collection = CookiesPlugin.getInstance().getMongo().getDatabase().getCollection("profiles");
            Document document = collection.find(Filters.eq("uuid", this.identifier.toString())).first();

            if (document != null) {
                JsonArray bans = new JsonParser().parse(document.getString("bans")).getAsJsonArray();

                bans.forEach(banData -> this.punishments.add(Ban.deserializeBan(banData.getAsJsonObject())));
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Ban getActiveBan() {
        for (AbstractPunishment punishment : this.punishments) {
            if (punishment instanceof Ban) {
                if (punishment.isActive()) {
                    return (Ban) punishment;
                }
            }
        }
        return null;
    }
}
