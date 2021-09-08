package me.gardendev.spigot.loaders;

import me.gardendev.shared.api.Loader;
import me.gardendev.spigot.SpigotPluginCore;
import me.gardendev.spigot.listeners.PreLoginPlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class ListenersLoader implements Loader {

    private final SpigotPluginCore pluginCore;

    public ListenersLoader(SpigotPluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @Override
    public void load() {
        registerListener(
                new PreLoginPlayerListener(pluginCore)
        );
    }

    private void registerListener(Listener... listeners) {
        for( Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(
                    listener,
                    pluginCore.getPlugin()
            );
        }
    }
}
