package me.gardendev.bungeecord.loaders;

import me.gardendev.bungeecord.BungeePluginCore;
import me.gardendev.bungeecord.listeners.ProxyPreLoginListener;
import me.gardendev.shared.api.Loader;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;

public class ListenersLoader implements Loader {

    private final BungeePluginCore pluginCore;

    public ListenersLoader(BungeePluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @Override
    public void load() {
        registerListeners(
                new ProxyPreLoginListener(pluginCore)
        );
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            ProxyServer.getInstance().getPluginManager().registerListener(
                    pluginCore.getPlugin(),
                    listener
            );
        }
    }
}
