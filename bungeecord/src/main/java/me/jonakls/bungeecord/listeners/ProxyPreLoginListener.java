package me.jonakls.bungeecord.listeners;

import me.jonakls.bungeecord.BungeePluginCore;
import me.jonakls.bungeecord.handler.MaintenanceHandler;
import me.jonakls.bungeecord.managers.BungeeFileManager;
import me.jonakls.bungeecord.utils.ChatUtil;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

public class ProxyPreLoginListener implements Listener {

    private final BungeeFileManager configManager;
    private final MaintenanceHandler maintenanceHandler;

    public ProxyPreLoginListener(BungeePluginCore pluginCore) {
        this.configManager = pluginCore.getFilesLoader().getConfig();
        this.maintenanceHandler = pluginCore.getMaintenanceHandler();
    }

    @EventHandler
    public void onPreLogin(PreLoginEvent event) {
        Configuration config = configManager.getConfiguration();
        if (!config.getBoolean("maintenance.enable")) return;

        if (maintenanceHandler.isWhitelisted(event.getConnection().getName())) {
            event.setCancelled(false);
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String string : config.getStringList("kick-message")) {
            stringBuilder.append(string).append("\n");
        }
        event.setCancelled(true);
        event.setCancelReason(ChatUtil.toLegacyComponent(stringBuilder.toString()));
    }

}
