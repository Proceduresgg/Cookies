package us.rengo.cookies;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import us.rengo.cookies.commands.IpCommand;
import us.rengo.cookies.commands.MessageCommand;
import us.rengo.cookies.commands.ToggleMessageCommand;
import us.rengo.cookies.listeners.PearlFix;
import us.rengo.cookies.managers.ManagerHandler;

import java.util.Arrays;

/**
 * @author Procedures
 */
@Getter
public class Cookies extends JavaPlugin {

    private ManagerHandler managerHandler;

    private PaperCommandManager commandManager;

    public void onEnable() {
        this.managerHandler = new ManagerHandler();
        this.registerCommands();
        this.registerListeners();
    }

    private void registerCommands() {
        this.commandManager = new PaperCommandManager(this);

        this.commandManager.registerDependency(ManagerHandler.class, this.managerHandler);

        Arrays.asList(new MessageCommand(), new IpCommand(), new ToggleMessageCommand()).forEach(command -> this.commandManager.registerCommand(command));
    }

    private void registerListeners() {
        Arrays.asList(new PearlFix()).forEach(listener -> this.getServer().getPluginManager().registerEvents(listener, this));
    }
}