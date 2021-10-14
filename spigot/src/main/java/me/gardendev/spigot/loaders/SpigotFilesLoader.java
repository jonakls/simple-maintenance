package me.gardendev.spigot.loaders;

import me.gardendev.shared.api.Loader;
import me.gardendev.spigot.SpigotPlugin;
import me.gardendev.spigot.SpigotPluginCore;
import me.gardendev.spigot.managers.SpigotFileManager;

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
