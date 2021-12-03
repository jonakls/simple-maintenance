package me.jonakls.bungeecord.loaders;

import me.jonakls.bungeecord.BungeeCordPlugin;
import me.jonakls.bungeecord.BungeePluginCore;
import me.jonakls.bungeecord.managers.BungeeFileManager;
import me.jonakls.shared.api.Loader;

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
        this.config = new BungeeFileManager(plugin, "bungeeconfig.yml");
        this.lang = new BungeeFileManager(plugin, "bungeelang.yml");
        plugin.getLogger().info("Files loaded!");
    }

    public BungeeFileManager getConfig() {
        return config;
    }

    public BungeeFileManager getLang() {
        return lang;
    }
}
