package us.rengo.cookies.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import us.rengo.cookies.CookiesPlugin;

@Getter
public class Mongo {

    private MongoClient mongoClient;
    private MongoDatabase database;

    public Mongo() {
        FileConfiguration config = CookiesPlugin.getInstance().getConfiguration().getCookies().getConfig();
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(50).build();

        String dir = "database.mongo.";

        MongoCredential credential = MongoCredential.createCredential(
                config.getString(dir + "user"),
                config.getString(dir + "database"),
                config.getString(dir + "pass").toCharArray());

        this.mongoClient = new MongoClient(new ServerAddress(
                config.getString(dir + "host"),
                config.getInt(dir + "port")),
                credential, options);

        this.database = this.mongoClient.getDatabase(config.getString(dir + "database"));
    }
}
