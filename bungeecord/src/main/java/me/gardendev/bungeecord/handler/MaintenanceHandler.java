package me.gardendev.bungeecord.handler;

import me.gardendev.bungeecord.BungeePluginCore;
import me.gardendev.bungeecord.managers.BungeeFileManager;
import me.gardendev.shared.handler.MaintenanceMode;

import java.util.List;

public class MaintenanceHandler implements MaintenanceMode {

    private final BungeeFileManager config;
    private List<String> whitelist;

    public MaintenanceHandler(BungeePluginCore pluginCore) {
        this.config = pluginCore.getFilesLoader().getConfig();
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
        config.getConfiguration().set("whitelist-players", whitelist);
        config.save();
        loadWhitelist();
    }

    @Override
    public void loadWhitelist() {
        this.whitelist = config.getConfiguration().getStringList("whitelist-players");
    }

    @Override
    public void updateWhitelist() {
        for (String player : config.getConfiguration().getStringList("whitelist-players")) {
            if (!this.whitelist.contains(player)) {
                this.whitelist.add(player);
            }
        }
        this.saveWhitelist();
    }

    @Override
    public boolean isWhitelisted(String player) {
        return whitelist.contains(player);
    }

    @Override
    public List<String> getWhitelist() {
        return whitelist;
    }

}
