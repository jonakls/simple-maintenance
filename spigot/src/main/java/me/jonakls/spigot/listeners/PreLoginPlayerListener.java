package me.jonakls.spigot.listeners;

import me.jonakls.spigot.SpigotPluginCore;
import me.jonakls.spigot.handler.MaintenanceHandler;
import me.jonakls.spigot.managers.SpigotFileManager;
import me.jonakls.spigot.utils.ChatUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PreLoginPlayerListener implements Listener {

    private final SpigotFileManager config;

    private final MaintenanceHandler maintenanceHandler;

    public PreLoginPlayerListener(SpigotPluginCore pluginCore) {
        this.config = pluginCore.getFilesLoader().getConfig();
        this.maintenanceHandler = pluginCore.getMaintenanceHandler();
    }

    @EventHandler
    private void preOnLogin(AsyncPlayerPreLoginEvent event) {
        if (!config.getBoolean("maintenance.enable")) return;

        if(maintenanceHandler.isWhitelisted(event.getName())) {
            event.allow();
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(String string : config.getStringList("kick-message")) {
            stringBuilder.append("\n").append(string);
        }
        event.setKickMessage(stringBuilder.toString());
        event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST, ChatUtil.apply(event.getKickMessage()));
    }

}
