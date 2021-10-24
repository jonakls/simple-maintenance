package me.gardendev.spigot.commands;

import me.gardendev.spigot.SpigotPluginCore;
import me.gardendev.spigot.handler.MaintenanceHandler;
import me.gardendev.spigot.managers.SpigotFileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.StringJoiner;

@SuppressWarnings("all")
public class MainCommand implements CommandExecutor {

    private final SpigotFileManager config;
    private final SpigotFileManager lang;
    private final MaintenanceHandler maintenanceHandler;

    public MainCommand(SpigotPluginCore pluginCore) {
        this.config = pluginCore.getFilesLoader().getConfig();
        this.lang = pluginCore.getFilesLoader().getLang();
        this.maintenanceHandler = pluginCore.getMaintenanceHandler();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(args.length > 0)) {
            sender.sendMessage(lang.getString("lang.unknown-command"));
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "on":
            case "enable":
                config.set("maintenance.enable", true);
                sender.sendMessage(lang.getString("lang.enable"));
                break;
            case "off":
            case "disable":
                config.set("maintenance.enable", false);
                sender.sendMessage(lang.getString("lang.disable"));
                break;
            case "reload":
                config.reload();
                lang.reload();
                sender.sendMessage(lang.getString("lang.reload"));
                break;
            case "add":
                if (maintenanceHandler.isWhitelisted(args[1])) {
                    sender.sendMessage(lang.getString("lang.player-exist").replace("%player%", args[1]));
                    return true;
                }
                maintenanceHandler.addPlayer(args[1]);
                sender.sendMessage(lang.getString("lang.player-added").replace("%player%", args[1]));
                break;
            case "remove":
                if (!maintenanceHandler.isWhitelisted(args[1])) {
                    sender.sendMessage(lang.getString("lang.player-exist").replace("%player%", args[1]));
                    return true;
                }
                maintenanceHandler.removePlayer(args[1]);
                sender.sendMessage(lang.getString("lang.player-removed").replace("%player%", args[1]));
                break;
            case "list":
                StringJoiner joiner = new StringJoiner(",");
                for(String string : maintenanceHandler.getWhitelist()) {
                    joiner.add(string);
                }
                sender.sendMessage("Players: " + joiner.toString());
                break;
            case "save":
                maintenanceHandler.saveWhitelist();
                sender.sendMessage(lang.getString("lang.whitelist-saved"));
                break;
            default:
                sender.sendMessage(lang.getString("lang.unknown-command"));
                break;
        }
        return false;
    }
}
