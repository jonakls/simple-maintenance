package me.gardendev.shared.handler;

import java.util.List;

public interface MaintenanceMode {

    void addPlayer(String player);

    void removePlayer(String player);

    void saveWhitelist();

    void loadWhitelist();

    void updateWhitelist();

    boolean isWhitelisted(String player);

    List<String> getWhitelist();

}
