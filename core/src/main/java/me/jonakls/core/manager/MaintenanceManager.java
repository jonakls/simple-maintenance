package me.jonakls.core.manager;

import java.util.List;

public interface MaintenanceManager {
    void addPlayer(String player);

    void removePlayer(String player);

    void saveWhitelist();

    void loadWhitelist();

    void updateWhitelist();

    boolean isWhitelisted(String player);

    List<String> getWhitelist();
}
