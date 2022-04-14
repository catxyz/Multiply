package me.catxyz.drops.commands;

import me.catxyz.drops.Drops;
import me.catxyz.drops.managers.ItemManager;
import me.catxyz.drops.utils.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public record MultiplierCommand(ItemManager itemManager) implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Drops.getInstance().getLogger().info("Command not supported here.");
            return true;
        }

        if (command.getName().equalsIgnoreCase("multiplier")) {
            if (player.hasPermission("drops.multiplier")) {
                if (args.length <= 1) {
                    player.sendMessage(Text.format("&cUsage:"));
                    player.sendMessage(Text.format("&c/" + command.getName() + " <number> <item_id>"));
                    player.sendMessage(Text.format("&c/" + command.getName() + " clear"));
                    player.sendMessage(Text.format("&c/" + command.getName() + " list"));
                    return true;
                }
                itemManager.processCommand(player, args);
            } else {
                player.sendMessage(Text.format("&cNo permission."));
                return true;
            }
        }
        return true;
    }

    @Override
    public @NotNull List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return Arrays.asList("clear", "list");
    }
}
