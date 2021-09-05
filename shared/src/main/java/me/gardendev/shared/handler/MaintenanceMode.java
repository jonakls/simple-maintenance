package me.gardendev.shared.handler;

import java.util.List;

public interface MaintenanceMode {

    void addPlayer(String player);

    void removePlayer(String player);

    List<String> getWhitelisted();

    boolean isWhitelisted(String player);

}
