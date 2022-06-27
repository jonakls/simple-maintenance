package me.jonakls.spigotmaintenance;

import me.jonakls.spigotmaintenance.listener.PlayerPreCheckListener;
import me.jonakls.spigotmaintenance.managers.IMaintenanceManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class SimpleMaintenanceSpigot extends JavaPlugin {

    private IMaintenanceManager maintenanceManager;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Init maintenance manager system
        maintenanceManager = new IMaintenanceManager();

        // Register listeners
        getServer().getPluginManager().registerEvents(new PlayerPreCheckListener(this), this);

        // Register commands
        Objects.requireNonNull(getCommand("maintenance")).setExecutor(new MaintenanceCommand(this));
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public IMaintenanceManager getMaintenanceManager() {
        return maintenanceManager;
    }
}
