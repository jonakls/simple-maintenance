package me.jonakls.spigot.handler;

import me.jonakls.shared.handler.MaintenanceMode;
import me.jonakls.spigot.SpigotPluginCore;
import me.jonakls.spigot.managers.SpigotFileManager;

import java.util.List;

public class MaintenanceHandler implements MaintenanceMode {

    private final SpigotFileManager config;
    private List<String> whitelist;

    public MaintenanceHandler(SpigotPluginCore pluginCore) {
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
        config.set("whitelist-players", whitelist);
        config.save();
        loadWhitelist();
    }

    @Override
    public void loadWhitelist() {
        this.whitelist = config.getStringList("whitelist-players");
    }

    @Override
    public void updateWhitelist() {
        for (String player : config.getStringList("whitelist-players")) {
            if (!whitelist.contains(player)) {
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
