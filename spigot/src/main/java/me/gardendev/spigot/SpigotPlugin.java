package me.gardendev.spigot;

import org.bukkit.plugin.java.JavaPlugin;

public final class SpigotPlugin extends JavaPlugin {

    private final SpigotPluginCore pluginCore = new SpigotPluginCore(this);

    @Override
    public void onEnable() {
        getLogger().info("Loading... please wait...");
        this.pluginCore.init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
