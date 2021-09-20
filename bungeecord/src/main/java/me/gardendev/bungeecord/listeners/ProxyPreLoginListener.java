package me.gardendev.bungeecord.listeners;

import me.gardendev.bungeecord.BungeePluginCore;
import me.gardendev.bungeecord.handler.MaintenanceHandler;
import me.gardendev.bungeecord.utils.ChatUtil;
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

        StringBuilder stringBuilder = new StringBuilder();
        for(String string : config.getStringList("kick-message")) {
            stringBuilder.append(string).append("\n");
        }
        event.setCancelled(true);
        event.setCancelReason(ChatUtil.apply(stringBuilder.toString()));

    }


}
