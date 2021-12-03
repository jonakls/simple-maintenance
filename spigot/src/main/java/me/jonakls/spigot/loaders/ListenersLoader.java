package me.jonakls.spigot.loaders;

import me.jonakls.shared.api.Loader;
import me.jonakls.spigot.SpigotPluginCore;
import me.jonakls.spigot.listeners.PreLoginPlayerListener;
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
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(
                    listener,
                    pluginCore.getPlugin()
            );
        }
    }
}
