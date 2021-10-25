package me.gardendev.bungeecord;

import me.gardendev.bungeecord.handler.MaintenanceHandler;
import me.gardendev.bungeecord.loaders.BungeeFilesLoader;
import me.gardendev.bungeecord.loaders.CommandsLoader;
import me.gardendev.bungeecord.loaders.ListenersLoader;
import me.gardendev.shared.api.Core;
import me.gardendev.shared.api.Loader;

public class BungeePluginCore implements Core {

    private final BungeeCordPlugin plugin;
    private BungeeFilesLoader filesLoader;
    private MaintenanceHandler maintenanceHandler;

    public BungeePluginCore(BungeeCordPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void init() {
        plugin.getLogger().info("Loading loaders...");
        this.filesLoader = new BungeeFilesLoader(this);
        this.filesLoader.load();
        this.maintenanceHandler = new MaintenanceHandler(this);
        this.maintenanceHandler.loadWhitelist();
        initLoaders(
                new CommandsLoader(this),
                new ListenersLoader(this)
        );
    }

    public void unload() {
        this.maintenanceHandler.saveWhitelist();
    }

    private void initLoaders(Loader... loaders) {
        for (Loader loader : loaders) {
            loader.load();
        }
    }

    public BungeeCordPlugin getPlugin() {
        return plugin;
    }

    public BungeeFilesLoader getFilesLoader() {
        return filesLoader;
    }

    public MaintenanceHandler getMaintenanceHandler() {
        return maintenanceHandler;
    }
}
