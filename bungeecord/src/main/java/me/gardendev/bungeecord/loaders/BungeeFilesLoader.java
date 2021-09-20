package me.gardendev.bungeecord.loaders;

import me.gardendev.bungeecord.BungeeCordPlugin;
import me.gardendev.bungeecord.BungeePluginCore;
import me.gardendev.bungeecord.managers.BungeeFileManager;
import me.gardendev.shared.api.Loader;

public class BungeeFilesLoader implements Loader {

    private final BungeeCordPlugin plugin;

    private BungeeFileManager config;
    private BungeeFileManager lang;

    public BungeeFilesLoader(BungeePluginCore pluginCore) {
        this.plugin = pluginCore.getPlugin();
    }

    @Override
    public void load() {
        plugin.getLogger().info("Loading files...");
        this.config = new BungeeFileManager(plugin, "config.yml");
        this.lang = new BungeeFileManager(plugin, "lang.yml");
        plugin.getLogger().info("Files loaded!");
    }

    public BungeeFileManager getConfig() {
        return config;
    }

    public BungeeFileManager getLang() {
        return lang;
    }
}
