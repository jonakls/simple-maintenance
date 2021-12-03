package me.jonakls.bungeecord;

import me.jonakls.bungeecord.handler.MaintenanceHandler;
import me.jonakls.bungeecord.loaders.BungeeFilesLoader;
import me.jonakls.bungeecord.loaders.CommandsLoader;
import me.jonakls.bungeecord.loaders.ListenersLoader;
import me.jonakls.shared.api.Core;
import me.jonakls.shared.api.Loader;

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
