package me.catxyz.multiply.commands;

import me.catxyz.multiply.Multiply;
import me.catxyz.multiply.components.TextComponents;
import me.catxyz.multiply.managers.ItemManager;
import me.catxyz.multiply.utils.Checks;
import me.catxyz.multiply.utils.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public record MultiplierCommand(ItemManager itemManager,
                                TextComponents textComponents) implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Multiply.getInstance().getLogger().info("Command not supported here.");
            return true;
        }

        if (command.getName().equalsIgnoreCase("multiplier")) {
            if (player.hasPermission(Multiply.DEFAULT_PERMISSION_NODE)) {
                if (args.length <= 1) {
                    player.sendMessage(Text.format("&7Usage:"));

                    Player.Spigot spPlayer = player.spigot();
                    spPlayer.sendMessage(textComponents.getMainComponent());
                    spPlayer.sendMessage(textComponents.getClearComponent());
                    spPlayer.sendMessage(textComponents.getListComponent());
                    spPlayer.sendMessage(textComponents.getAllComponent());
                    return true;
                }
                if (Objects.equals(args[0], "all")) {
                    if (!Checks.isNumber(args[1])) {
                        player.sendMessage(Text.format("&c" + args[1] + " is not a number."));
                        return true;
                    }
                    itemManager.processAddAll(player, Integer.parseInt(args[1]));
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
        if (!sender.hasPermission(Multiply.DEFAULT_PERMISSION_NODE)) {
            return List.of();
        }
        return List.of("clear", "list");
    }
}
