package me.gardendev.bungecoord.handler;

import me.gardendev.bungecoord.BungeePluginCore;
import me.gardendev.shared.handler.MaintenanceMode;
import net.md_5.bungee.config.Configuration;

import java.util.List;

public class MaintenanceHandler implements MaintenanceMode {

    private final Configuration config;

    private List<String> whitelist;

    public MaintenanceHandler(BungeePluginCore pluginCore) {
        this.config = pluginCore.getFilesLoader().getConfig().getConfiguration();
        loadWhitelist();
    }

    @Override
    public void addPlayer(String player) {
        whitelist.add(player);
    }

    @Override
    public void removePlayer(String player) {
        whitelist.remove(player);
    }

    @Override
    public void saveWhitelist() {
        config.set("whitelist-players", whitelist);
    }

    @Override
    public void loadWhitelist() {
        this.whitelist = config.getStringList("whitelist-players");
    }

    @Override
    public boolean isWhitelisted(String player) {
        return whitelist.contains(player);
    }

}
