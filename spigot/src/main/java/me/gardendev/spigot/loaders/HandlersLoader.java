package me.gardendev.spigot.loaders;

import me.gardendev.shared.api.Loader;
import me.gardendev.spigot.SpigotPluginCore;
import me.gardendev.spigot.handler.MaintenanceHandler;

public class HandlersLoader implements Loader {

    private final SpigotPluginCore pluginCore;

    private MaintenanceHandler maintenanceHandler;


    public HandlersLoader(SpigotPluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @Override
    public void load() {
        this.maintenanceHandler = new MaintenanceHandler(pluginCore);
        this.maintenanceHandler.loadWhitelist();
    }

    public MaintenanceHandler getMaintenanceHandler() {
        return maintenanceHandler;
    }
}
