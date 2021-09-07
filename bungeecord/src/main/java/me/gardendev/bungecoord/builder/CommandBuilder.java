package me.gardendev.bungecoord.builder;

import net.md_5.bungee.api.plugin.Command;

public class CommandBuilder {

    private final Command command;

    public CommandBuilder(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
