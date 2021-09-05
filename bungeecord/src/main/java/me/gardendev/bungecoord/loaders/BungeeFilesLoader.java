package me.gardendev.bungecoord.loaders;

import me.gardendev.bungecoord.BungeeCordPlugin;
import me.gardendev.bungecoord.BungeePluginCore;
import me.gardendev.bungecoord.managers.BungeeFileManager;
import me.gardendev.shared.api.Loader;
import net.md_5.bungee.config.Configuration;

public class BungeeFilesLoader implements Loader {

    private final BungeeCordPlugin plugin;

    private BungeeFileManager config;
    private BungeeFileManager lang;

    public BungeeFilesLoader(BungeePluginCore pluginCore) {
        this.plugin = pluginCore.getPlugin();
    }

    @Override
    public void load() {
        this.config = new BungeeFileManager(plugin, "config.yml");
        this.lang = new BungeeFileManager(plugin, "lang.yml");
    }

    public Configuration getConfig() {
        return config.getConfiguration();
    }

    public Configuration getLang() {
        return lang.getConfiguration();
    }
}
