package me.jonakls.spigotmaintenance.utils;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public class TextUtil {

    public static String toLegacyColors(@NotNull String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
