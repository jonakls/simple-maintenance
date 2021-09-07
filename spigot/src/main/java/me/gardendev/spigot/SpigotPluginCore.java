package me.gardendev.spigot;

import me.gardendev.shared.api.Core;
import me.gardendev.shared.api.Loader;
import me.gardendev.spigot.handler.MaintenanceHandler;
import me.gardendev.spigot.loaders.SpigotFilesLoader;

public class SpigotPluginCore implements Core {

    private final SpigotPlugin plugin;
    private SpigotFilesLoader filesLoader;
    private MaintenanceHandler maintenanceHandler;

    public SpigotPluginCore(SpigotPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void init() {
        initLoaders(
                this.filesLoader = new SpigotFilesLoader(this)
        );
        this.maintenanceHandler = new MaintenanceHandler(this);
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
