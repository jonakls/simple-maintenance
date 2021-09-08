package me.gardendev.spigot.utils;

import org.bukkit.ChatColor;

public class ChatUtil {

    public static String apply(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
