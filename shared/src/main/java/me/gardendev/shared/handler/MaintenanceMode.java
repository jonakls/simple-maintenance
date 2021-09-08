package me.gardendev.shared.handler;

public interface MaintenanceMode {

    void addPlayer(String player);

    void removePlayer(String player);

    void saveWhitelist();

    void loadWhitelist();

    boolean isWhitelisted(String player);

}
