package me.jonakls.spigotmaintenance.listener;

import me.jonakls.spigotmaintenance.SimpleMaintenanceSpigot;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.StringJoiner;

import static me.jonakls.spigotmaintenance.utils.TextUtil.toLegacyColors;

public class PlayerPreCheckListener implements Listener {

    private final SimpleMaintenanceSpigot plugin;

    public PlayerPreCheckListener(SimpleMaintenanceSpigot plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void playerPreJoin(AsyncPlayerPreLoginEvent event) {
        FileConfiguration config = plugin.getConfig();

        if (!config.getBoolean("maintenance.enable", false)) return;
        if (plugin.getMaintenanceManager().isWhitelisted(event.getName())) return;

        StringJoiner kickMessage = new StringJoiner("\n");
        for (String line : config.getStringList("maintenance.kick-message")) {
            kickMessage.add(line);
        }

        event.disallow(
                AsyncPlayerPreLoginEvent.Result.KICK_OTHER, toLegacyColors(kickMessage.toString())
        );
    }
}
