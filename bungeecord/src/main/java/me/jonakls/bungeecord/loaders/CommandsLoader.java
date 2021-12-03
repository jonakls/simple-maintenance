package me.jonakls.bungeecord.loaders;

import me.jonakls.bungeecord.BungeePluginCore;
import me.jonakls.bungeecord.builder.CommandBuilder;
import me.jonakls.bungeecord.commands.BungeeMainCommand;
import me.jonakls.shared.api.Loader;
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
