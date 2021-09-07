package me.gardendev.bungecoord.listeners;

import me.gardendev.bungecoord.BungeePluginCore;
import me.gardendev.bungecoord.handler.MaintenanceHandler;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

public class ProxyPreLoginListener implements Listener {

    private final Configuration config;
    private final MaintenanceHandler maintenanceHandler;

    public ProxyPreLoginListener(BungeePluginCore pluginCore) {
        this.config = pluginCore.getFilesLoader().getConfig().getConfiguration();
        this.maintenanceHandler = pluginCore.getMaintenanceHandler();
    }

    @EventHandler
    public void onPreLogin(PreLoginEvent event) {
        if (!config.getBoolean("maintenance.enable")) return;

        if (maintenanceHandler.isWhitelisted(event.getConnection().getName())) {
            event.setCancelled(false);
            return;
        }


        for(String line : config.getStringList("maintenance.kick-message")) {

        }

        event.setCancelled(true);
        event.setCancelReason();

    }


}
