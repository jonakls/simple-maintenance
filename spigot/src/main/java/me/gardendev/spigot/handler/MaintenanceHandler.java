package me.gardendev.spigot.handler;

import me.gardendev.shared.handler.MaintenanceMode;
import me.gardendev.spigot.SpigotPluginCore;
import me.gardendev.spigot.managers.SpigotFileManager;

import java.util.List;

public class MaintenanceHandler implements MaintenanceMode {

    private final SpigotFileManager config;

    private List<String> whitelist;

    public MaintenanceHandler(SpigotPluginCore pluginCore) {
        this.config = pluginCore.getFilesLoader().getConfig();
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
        whitelist.clear();
    }

    @Override
    public void loadWhitelist() {
        this.whitelist = config.getStringList("whitelist-players");
    }

    @Override
    public void updateWhitelist() {
        for(String player : config.getStringList("whitelist-players")) {
            if(!whitelist.contains(player)) {
                whitelist.add(player);
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
