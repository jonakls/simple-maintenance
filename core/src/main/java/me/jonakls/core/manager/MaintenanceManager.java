package me.jonakls.core.manager;


import java.util.Map;
import java.util.UUID;

public class MaintenanceManager {

    private Map<UUID, String> whitelist;

    public void addPlayer(UUID uuid, String player) {
        if (!whitelist.containsKey(uuid)) {
            whitelist.put(uuid, player);
        }
    }

    public void removePlayer(UUID uuid) {
        whitelist.remove(uuid);
    }

    public void saveWhitelist() {
        //TODO: Save whitelist to file
    }

    public void loadWhitelist() {
        //TODO: Load whitelist from file
    }

    public void updateWhitelist() {
        whitelist.clear();
        this.loadWhitelist();
    }

    public boolean isWhitelisted(String player) {
        return false;
    }

    public String getWhitelist() {
        return null;
    }
}
