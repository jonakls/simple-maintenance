package me.gardendev.spigot.builder;

import org.bukkit.command.CommandExecutor;

public class CommandBuilder {

    private final String command;
    private final CommandExecutor commandExecutor;

    public CommandBuilder(String command, CommandExecutor commandExecutor) {
        this.command = command;
        this.commandExecutor = commandExecutor;
    }

    public String getCommand() {
        return command;
    }

    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }
}
