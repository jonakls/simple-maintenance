package me.gardendev.bungecoord.loaders;

import me.gardendev.bungecoord.BungeePluginCore;
import me.gardendev.bungecoord.builder.CommandBuilder;
import me.gardendev.bungecoord.commands.BungeeMainCommand;
import me.gardendev.shared.api.Loader;
import net.md_5.bungee.api.ProxyServer;

public class CommandsLoader implements Loader {

    private final BungeePluginCore pluginCore;

    public CommandsLoader(BungeePluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @Override
    public void load() {
        registerCommands(
                new CommandBuilder(new BungeeMainCommand(pluginCore))
        );

    }

    public void registerCommands(CommandBuilder... commandBuilders) {
        for (CommandBuilder commandBuilder : commandBuilders) {
            ProxyServer.getInstance().getPluginManager().registerCommand(
                    pluginCore.getPlugin(),
                    commandBuilder.getCommand()
            );
        }
    }
}
