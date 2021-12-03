package me.jonakls.spigot;

import me.jonakls.shared.api.Core;
import me.jonakls.shared.api.Loader;
import me.jonakls.spigot.handler.MaintenanceHandler;
import me.jonakls.spigot.loaders.CommandLoader;
import me.jonakls.spigot.loaders.ListenersLoader;
import me.jonakls.spigot.loaders.SpigotFilesLoader;

public class SpigotPluginCore implements Core {

    private final SpigotPlugin plugin;
    private SpigotFilesLoader filesLoader;
    private MaintenanceHandler maintenanceHandler;

    public SpigotPluginCore(SpigotPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void init() {
        plugin.getLogger().info("Loading loaders...");
        this.filesLoader = new SpigotFilesLoader(this);
        this.filesLoader.load();
        this.maintenanceHandler = new MaintenanceHandler(this);
        this.maintenanceHandler.loadWhitelist();
        initLoaders(
                new CommandLoader(this),
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

    public SpigotPlugin getPlugin() {
        return plugin;
    }

    public SpigotFilesLoader getFilesLoader() {
        return filesLoader;
    }

    public MaintenanceHandler getMaintenanceHandler() {
        return maintenanceHandler;
    }
}
