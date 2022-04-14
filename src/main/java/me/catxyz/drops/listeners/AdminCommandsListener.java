package me.catxyz.drops.listeners;

import com.google.common.collect.Maps;
import me.catxyz.drops.managers.ItemManager;
import me.catxyz.drops.utils.Text;
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
            if (player.hasPermission("drops.multiplier")) {
                processCommands(player, message, event);
            } else {
                player.sendMessage(Text.format("&cCould not modify this."));
            }
        }
    }

    private void processCommands(Player player, String command, Cancellable cancellable) {
        switch (command) {
            case "/multiplier clear" -> {
                Map<Material, Integer> items = Maps.newHashMap(itemManager.getItems());
                items.forEach((material, multiplier) -> {
                    player.sendMessage(
                            Text.format("&cCleared multiplier for &6" + material)
                    );
                    itemManager.removeItem(material);
                });
                cancellable.setCancelled(true);
            }
            case "/multiplier list" -> {
                if (!itemManager.getItems().isEmpty()) {
                    player.sendMessage(Text.format("&eItem List:"));
                    itemManager.getItems().forEach((material, multiplier) -> player.sendMessage(
                            Text.format(" &8• &6" + material + " &8- &a" + multiplier + "&bx")
                    ));
                } else {
                    player.sendMessage(Text.format("&cItem list is empty."));
                }
                cancellable.setCancelled(true);
            }
        }
    }
}
