package me.catxyz.multiply.listeners;

import me.catxyz.multiply.Multiply;
import me.catxyz.multiply.managers.ItemManager;
import me.catxyz.multiply.utils.Format;
import me.catxyz.multiply.utils.Text;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Map;

public record AdminCommandsListener(ItemManager itemManager) implements Listener {

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (message.startsWith("/multiplier ")) {
            if (player.hasPermission(Multiply.DEFAULT_PERMISSION_NODE)) {
                processCommands(player, message, event);
            } else {
                player.sendMessage(Text.format("&cCould not modify this."));
            }
        }
    }

    private void processCommands(Player player, String command, Cancellable cancellable) {
        switch (command) {
            case "/multiplier clear" -> {
                Map<Material, Integer> items = Map.copyOf(itemManager.getItems());
                if (!items.isEmpty()) {
                    items.forEach((material, multiplier) -> {
                        player.sendMessage(
                                Text.format("&cCleared multiplier for &e" + material)
                        );
                        itemManager.removeItem(material);
                    });
                } else {
                    player.sendMessage(Text.format("&cThere is nothing to clear."));
                }
                cancellable.setCancelled(true);
            }
            case "/multiplier list" -> {
                if (!itemManager.getItems().isEmpty()) {
                    player.sendMessage(Text.format("&eBlock List:"));
                    itemManager.getItems().forEach((material, multiplier) -> player.sendMessage(
                            Text.format(" &8• &6" + material + " &7- &a" + Format.formatNumber(multiplier) + "&bx")
                    ));
                } else {
                    player.sendMessage(Text.format("&cBlock list is empty."));
                }
                cancellable.setCancelled(true);
            }
        }
    }
}
