package us.rengo.cookies;

import co.aikar.commands.ConditionFailedException;
import co.aikar.commands.PaperCommandManager;
import co.aikar.commands.contexts.OnlinePlayer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import us.rengo.cookies.commands.message.MessageCommand;
import us.rengo.cookies.commands.message.ReplyCommand;
import us.rengo.cookies.commands.message.ToggleMessageCommand;
import us.rengo.cookies.commands.other.PingCommand;
import us.rengo.cookies.commands.other.SetSlotsCommand;
import us.rengo.cookies.commands.player.ClearCommand;
import us.rengo.cookies.commands.player.FlyCommand;
import us.rengo.cookies.commands.player.GamemodeCommand;
import us.rengo.cookies.commands.player.TimeCommand;
import us.rengo.cookies.commands.punishment.BanCommand;
import us.rengo.cookies.commands.staff.BroadcastCommand;
import us.rengo.cookies.commands.staff.ClearChatCommand;
import us.rengo.cookies.commands.staff.FilterCommand;
import us.rengo.cookies.commands.staff.MuteChatCommand;
import us.rengo.cookies.config.CoreConfiguration;
import us.rengo.cookies.database.Mongo;
import us.rengo.cookies.listeners.ChatListener;
import us.rengo.cookies.listeners.PearlListener;
import us.rengo.cookies.listeners.PlayerListener;
import us.rengo.cookies.managers.FilterManager;
import us.rengo.cookies.managers.PlayerDataManager;
import us.rengo.cookies.player.PlayerProfile;

import java.util.Arrays;

/**
 * @author Procedures
 */
@Getter
public class CookiesPlugin extends JavaPlugin {

    @Getter private static CookiesPlugin instance;

    @Setter private boolean chatEnabled = true;

    private CoreConfiguration configuration;
    private Mongo mongo;
    private PlayerDataManager playerDataManager;
    private FilterManager filterManager;

    public void onEnable() {
        instance = this;

        this.configuration = new CoreConfiguration();
        this.mongo = new Mongo();
        this.playerDataManager = new PlayerDataManager();
        this.filterManager = new FilterManager();

        PaperCommandManager manager = new PaperCommandManager(this);

        this.registerListeners();
        this.registerCommands(manager);
    }

    private void registerCommands(PaperCommandManager manager) {
        this.registerConditions(manager);

        Arrays.asList(new MessageCommand(), new SetSlotsCommand(), new MuteChatCommand(),
                new TimeCommand(), new BroadcastCommand(), new ClearChatCommand(),
                new ToggleMessageCommand(), new PingCommand(), new ReplyCommand(),
                new FilterCommand(), new ClearCommand(), new FlyCommand(),
                new GamemodeCommand(), new BanCommand())
                .forEach(manager::registerCommand);
    }

    private void registerListeners() {
        Arrays.asList(new PearlListener(), new ChatListener(this), new PlayerListener(this)).forEach(listener -> this.getServer().getPluginManager().registerEvents(listener, this));
    }

    private void registerConditions(PaperCommandManager manager) {
        manager.getCommandConditions().addCondition(OnlinePlayer.class, "messages-enabled", (c, exec, value) -> {
            if (value != null) {
                PlayerProfile profile = this.playerDataManager.getData(value.getPlayer());

                if (!profile.isMessagesEnabled()) {
                    throw new ConditionFailedException(ChatColor.DARK_AQUA + value.getPlayer().getName() + ChatColor.BLUE + " does not have their messages enabled.");
                }
            }
        });
    }
}