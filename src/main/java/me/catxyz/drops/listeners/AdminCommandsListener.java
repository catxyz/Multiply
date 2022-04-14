package me.catxyz.drops.listeners;

import com.google.common.collect.Maps;
import me.catxyz.drops.managers.ItemManager;
import me.catxyz.drops.utils.Text;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Map;

public record AdminCommandsListener(ItemManager itemManager) implements Listener {

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        String command = "/multiplier";
        if (message.startsWith(command)) {
            if (player.hasPermission("drops.multiplier")) {
                if (message.startsWith(command + " clear")) {
                    Map<Material, Integer> items = Maps.newHashMap(itemManager.getItems());
                    items.forEach((material, multiplier) -> {
                        player.sendMessage(
                                Text.format("&cCleared multiplier for &6" + material)
                        );
                        itemManager.removeItem(material);
                    });
                    event.setCancelled(true);
                }
                if (message.startsWith(command + " list")) {
                    if (!itemManager.getItems().isEmpty()) {
                        player.sendMessage(Text.format("&eItem List:"));
                        itemManager.getItems().forEach((material, multiplier) -> player.sendMessage(
                                Text.format(" &8• &6" + material + " &8- &a" + multiplier + "&bx")
                        ));
                    } else {
                        player.sendMessage(Text.format("&cItem list is empty."));
                    }
                    event.setCancelled(true);
                }
            } else {
                player.sendMessage(Text.format("&cCould not modify this."));
            }
        }
    }
}
