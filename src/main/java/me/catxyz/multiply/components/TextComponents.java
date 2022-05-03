package me.catxyz.multiply.components;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class TextComponents {

    public static final ChatColor COMMAND_COLOR_SCHEME = ChatColor.GREEN;

    public BaseComponent getMainComponent() {
        String blockNamespace = "<block_id>";

        TextComponent command = new TextComponent("/multiplier <number> " + blockNamespace);
        command.setColor(COMMAND_COLOR_SCHEME);
        command.setHoverEvent(new HoverEvent(
                        HoverEvent.Action.SHOW_TEXT,
                        new Text(ChatColor.GRAY + "Main command; use this to add multipliers for specific blocks!\n" +
                                "<number> - multiplier number\n" +
                                blockNamespace + " - block id (e.g. stone, grass_block etc.)")
                )
        );

        return command;
    }

    public BaseComponent getClearComponent() {
        TextComponent command = new TextComponent("/multiplier clear");
        command.setColor(COMMAND_COLOR_SCHEME);
        command.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new Text(ChatColor.GRAY + "Clears the multiplier list."))
        );

        return command;
    }

    public BaseComponent getListComponent() {
        TextComponent command = new TextComponent("/multiplier list");
        command.setColor(COMMAND_COLOR_SCHEME);
        command.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new Text(ChatColor.GRAY + "Shows a list of blocks in the multiplier list."))
        );

        return command;
    }

    public BaseComponent getAllComponent() {
        TextComponent command = new TextComponent("/multiplier all <number>");
        command.setColor(COMMAND_COLOR_SCHEME);
        command.setHoverEvent(new HoverEvent(
                        HoverEvent.Action.SHOW_TEXT,
                        new Text(ChatColor.RED + "" + ChatColor.UNDERLINE + "A dangerous command.\n" +
                                "\n" +
                                ChatColor.RED + "" + ChatColor.BOLD + "WARNING!\n" +
                                ChatColor.RED + "Dangerous stuff ahead. " + ChatColor.GRAY + "Using '" + ChatColor.YELLOW + "all" + ChatColor.GRAY + "' adds all blocks\n" +
                                ChatColor.GRAY + "to the multiplier list with the specified multiplier number.")
                )
        );

        return command;
    }
}
