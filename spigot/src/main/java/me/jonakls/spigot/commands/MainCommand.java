package me.jonakls.spigot.commands;

import me.jonakls.spigot.SpigotPluginCore;
import me.jonakls.spigot.handler.MaintenanceHandler;
import me.jonakls.spigot.managers.SpigotFileManager;
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
            lang.getStringList("lang.help").forEach(string ->
                    sender.sendMessage(string.replace("%command%", label)));
            return true;
        }

        if (!sender.hasPermission("simplemaintenance.commands")) {
            sender.sendMessage(lang.getString("lang.no-permission"));
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "on":
            case "enable":
                config.set("maintenance.enable", true);
                config.save();
                sender.sendMessage(lang.getString("lang.enable"));
                break;
            case "off":
            case "disable":
                config.set("maintenance.enable", false);
                config.save();
                sender.sendMessage(lang.getString("lang.disable"));
                break;
            case "reload":
                config.reload();
                lang.reload();
                sender.sendMessage(lang.getString("lang.reload"));
                break;
            case "add":
                if (args.length != 2) {
                    sender.sendMessage(lang.getString("lang.usage.add").replace("%command%", label));
                    return true;
                }
                if (maintenanceHandler.isWhitelisted(args[1])) {
                    sender.sendMessage(lang.getString("lang.player-exist").replace("%player%", args[1]));
                    return true;
                }
                maintenanceHandler.addPlayer(args[1]);
                sender.sendMessage(lang.getString("lang.player-added").replace("%player%", args[1]));
                break;
            case "remove":
                if (args.length != 2) {
                    sender.sendMessage(lang.getString("lang.usage.remove").replace("%command%", label));
                    return true;
                }
                if (!maintenanceHandler.isWhitelisted(args[1])) {
                    sender.sendMessage(lang.getString("lang.player-exist").replace("%player%", args[1]));
                    return true;
                }
                maintenanceHandler.removePlayer(args[1]);
                sender.sendMessage(lang.getString("lang.player-removed").replace("%player%", args[1]));
                break;
            case "list":
                StringJoiner joiner = new StringJoiner(", ");
                for (String string : maintenanceHandler.getWhitelist()) {
                    joiner.add(string);
                }
                sender.sendMessage("Players: " + joiner.toString());
                break;
            case "save":
                maintenanceHandler.saveWhitelist();
                sender.sendMessage(lang.getString("lang.whitelist-saved"));
                break;
            case "help":
                lang.getStringList("lang.help").forEach(string -> {
                    sender.sendMessage(string.replace("%command%", label));
                });
                break;
            default:
                sender.sendMessage(lang.getString("lang.unknown-command"));
                break;
        }
        return false;
    }
}
