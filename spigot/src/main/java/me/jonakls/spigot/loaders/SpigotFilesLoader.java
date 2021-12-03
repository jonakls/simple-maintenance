package me.jonakls.spigot.loaders;

import me.jonakls.shared.api.Loader;
import me.jonakls.spigot.SpigotPlugin;
import me.jonakls.spigot.SpigotPluginCore;
import me.jonakls.spigot.managers.SpigotFileManager;

public class SpigotFilesLoader implements Loader {

    private final SpigotPlugin plugin;

    private SpigotFileManager config;
    private SpigotFileManager lang;

    public SpigotFilesLoader(SpigotPluginCore pluginCore) {
        this.plugin = pluginCore.getPlugin();
    }

    @Override
    public void load() {
        this.config = new SpigotFileManager(plugin, "spigotconfig.yml");
        this.lang = new SpigotFileManager(plugin, "spigotlang.yml");
    }

    public SpigotFileManager getConfig() {
        return config;
    }

    public SpigotFileManager getLang() {
        return lang;
    }
}
