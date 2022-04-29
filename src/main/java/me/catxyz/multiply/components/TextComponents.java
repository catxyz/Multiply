package me.catxyz.multiply.components;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class TextComponents {

    protected static final String HOVER_INDICATOR = " [Hover over me]";

    public BaseComponent getMainComponent() {
        String materialArg = "<block_id>";
        TextComponent command = new TextComponent("/multiplier <number> " + materialArg);
        command.setColor(ChatColor.YELLOW);

        TextComponent indicator = new TextComponent(HOVER_INDICATOR);
        indicator.setColor(ChatColor.GRAY);
        indicator.setHoverEvent(new HoverEvent(
                        HoverEvent.Action.SHOW_TEXT,
                        new Text(ChatColor.GRAY + "Main command; use this to add multipliers for specific blocks!\n" +
                                "<number> - multiplier number\n" +
                                materialArg + " - block id (e.g. stone, grass_block etc.)")
                )
        );
        command.addExtra(indicator);

        return command;
    }

    public BaseComponent getClearComponent() {
        TextComponent command = new TextComponent("/multiplier clear");
        command.setColor(ChatColor.YELLOW);

        TextComponent indicator = new TextComponent(HOVER_INDICATOR);
        indicator.setColor(ChatColor.GRAY);
        indicator.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new Text(ChatColor.GRAY + "Clears the multiplier list."))
        );
        command.addExtra(indicator);

        return command;
    }

    public BaseComponent getListComponent() {
        TextComponent command = new TextComponent("/multiplier list");
        command.setColor(ChatColor.YELLOW);

        TextComponent indicator = new TextComponent(HOVER_INDICATOR);
        indicator.setColor(ChatColor.GRAY);
        indicator.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new Text(ChatColor.GRAY + "Shows a list of blocks in the multiplier list."))
        );
        command.addExtra(indicator);

        return command;
    }

    public BaseComponent getAllComponent() {
        TextComponent command = new TextComponent("/multiplier all <number>");
        command.setColor(ChatColor.RED);

        TextComponent indicator = new TextComponent(HOVER_INDICATOR);
        indicator.setColor(ChatColor.GRAY);
        indicator.setHoverEvent(new HoverEvent(
                        HoverEvent.Action.SHOW_TEXT,
                        new Text(ChatColor.RED + "" + ChatColor.UNDERLINE + "- Admin command -\n" +
                                "\n" +
                                ChatColor.RED + "" + ChatColor.BOLD + "WARNING!\n" +
                                ChatColor.RED + "Dangerous stuff ahead. " + ChatColor.GRAY + "Using '" + ChatColor.YELLOW + "all" + ChatColor.GRAY + "' adds all blocks\n" +
                                ChatColor.GRAY + "to the multiplier list with the given multiplier.")
                )
        );
        command.addExtra(indicator);

        return command;
    }
}
