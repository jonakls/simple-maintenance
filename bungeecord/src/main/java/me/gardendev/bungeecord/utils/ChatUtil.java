package me.gardendev.bungeecord.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatUtil {

    public static TextComponent toLegacyComponent(String text) {
        return new TextComponent(ChatColor.translateAlternateColorCodes('&', text));
    }
}
