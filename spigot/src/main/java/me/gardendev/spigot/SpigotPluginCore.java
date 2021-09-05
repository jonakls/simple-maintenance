package me.gardendev.spigot;

import me.gardendev.shared.api.Core;
import me.gardendev.shared.api.Loader;
import me.gardendev.spigot.loaders.SpigotFilesLoader;

public class SpigotPluginCore implements Core {

    private final SpigotPlugin plugin;
    private SpigotFilesLoader filesLoader;

    public SpigotPluginCore(SpigotPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void init() {
        initLoaders(
                this.filesLoader = new SpigotFilesLoader(this)
        );

    }

    private void initLoaders(Loader... loaders) {
        for (Loader loader : loaders) {
            loader.load();
        }
    }

    public SpigotPlugin getPlugin() {
        return plugin;
    }
}
