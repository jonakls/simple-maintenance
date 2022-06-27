package me.jonakls.spigotmaintenance;

import me.jonakls.spigotmaintenance.managers.IMaintenanceManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static me.jonakls.spigotmaintenance.utils.TextUtil.toLegacyColors;

public class MaintenanceCommand implements CommandExecutor {

    private final SimpleMaintenanceSpigot plugin;

    public MaintenanceCommand(SimpleMaintenanceSpigot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args
    ) {
        FileConfiguration config = plugin.getConfig();
        IMaintenanceManager maintenanceManager = plugin.getMaintenanceManager();

        if (!sender.hasPermission("simplemaintenance.commands")) {
            sender.sendMessage(toLegacyColors(Objects.requireNonNull(config.getString("lang.no-permission"))));
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(toLegacyColors(
                    Objects.requireNonNull(config.getString("lang.unknown-command")).replace("%command%", label)
            ));
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                plugin.reloadConfig();
                sender.sendMessage(toLegacyColors(Objects.requireNonNull(config.getString("lang.reload"))));
                return true;
            case "add":
                if (args.length < 2) {
                    sender.sendMessage(toLegacyColors(Objects.requireNonNull(config.getString("lang.usage.add"))));
                    return true;
                }
                Player target = Bukkit.getPlayerExact(args[1]);

                if (target == null) {
                    sender.sendMessage(toLegacyColors(Objects.requireNonNull(config.getString("lang.player-not-found"))));
                    return true;
                }

                maintenanceManager.addPlayer(target.getUniqueId(), target.getName());
                sender.sendMessage(toLegacyColors(Objects.requireNonNull(config.getString("lang.add-success"))));
                return true;
            case "remove":
                if (args.length < 2) {
                    sender.sendMessage(toLegacyColors(Objects.requireNonNull(config.getString("lang.usage.remove"))));
                    return true;
                }
                target = Bukkit.getPlayerExact(args[1]);
                if (target == null) {
                    sender.sendMessage(toLegacyColors(Objects.requireNonNull(config.getString("lang.player-not-found"))));
                    return true;
                }

                maintenanceManager.removePlayer(target.getUniqueId());
                sender.sendMessage(toLegacyColors(Objects.requireNonNull(config.getString("lang.remove-success"))));
                return true;
            default:
                for (String line : config.getStringList("lang.help")) {
                    sender.sendMessage(toLegacyColors(line.replace("%command%", label)));
                }
                return true;
        }
    }
}
