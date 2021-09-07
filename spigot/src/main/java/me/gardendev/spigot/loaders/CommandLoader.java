package me.gardendev.spigot.loaders;

import me.gardendev.shared.api.Loader;
import me.gardendev.spigot.SpigotPluginCore;
import me.gardendev.spigot.builder.CommandBuilder;
import me.gardendev.spigot.commands.MainCommand;
import org.bukkit.Bukkit;

public class CommandLoader implements Loader {

    private final SpigotPluginCore pluginCore;

    public CommandLoader(SpigotPluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @Override
    public void load() {
        registerCommand(
                new CommandBuilder("simplemaintenance", new MainCommand(pluginCore))
        );

    }

    private void registerCommand(CommandBuilder... commandBuilders) {
        for(CommandBuilder commandBuilder : commandBuilders) {
            Bukkit.getPluginCommand(commandBuilder.getCommand()).setExecutor(commandBuilder.getCommandExecutor());
        }
    }
}
