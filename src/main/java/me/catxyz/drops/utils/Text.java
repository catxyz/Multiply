package me.catxyz.drops.utils;

import org.bukkit.ChatColor;

public class Text {

    public static String format(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
