package me.gardendev.bungecoord.commands;

import me.gardendev.bungecoord.BungeePluginCore;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

public class BungeeMainCommand extends Command {

    private Configuration lang;

    public BungeeMainCommand(BungeePluginCore pluginCore) {
        super(
                "simplemaintenance",
                "simplemaintenance.command",
                "sm", "smn", "maintenance"
        );
        this.lang = pluginCore.getFilesLoader().getLang();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        

    }
}
