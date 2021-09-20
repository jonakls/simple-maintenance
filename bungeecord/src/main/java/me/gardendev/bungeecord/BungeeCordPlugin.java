package me.gardendev.bungeecord;

import net.md_5.bungee.api.plugin.Plugin;

public final class BungeeCordPlugin extends Plugin {

    private final BungeePluginCore pluginCore = new BungeePluginCore(this);

    @Override
    public void onEnable() {
        getLogger().info("Loading... please wait...");
        this.pluginCore.init();
    }

    @Override
    public void onDisable() {
        this.pluginCore.unload();
    }
}
