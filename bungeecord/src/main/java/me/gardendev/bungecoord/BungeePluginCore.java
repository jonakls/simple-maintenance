package me.gardendev.bungecoord;

import me.gardendev.bungecoord.handler.MaintenanceHandler;
import me.gardendev.bungecoord.loaders.BungeeFilesLoader;
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
        initLoaders(
                this.filesLoader = new BungeeFilesLoader(this)
        );
        this.maintenanceHandler = new MaintenanceHandler(this);
    }

    public void unload() {
        this.maintenanceHandler.saveWhitelist();
    }

    private void initLoaders(Loader... loaders) {
        for(Loader loader : loaders) {
            loader.load();
        }
    }

    public BungeeCordPlugin getPlugin() {
        return plugin;
    }

    public BungeeFilesLoader getFilesLoader() {
        return filesLoader;
    }
}
