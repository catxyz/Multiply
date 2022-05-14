package me.catxyz.multiply.listeners;

import me.catxyz.multiply.managers.ItemManager;
import me.catxyz.multiply.utils.ItemMultiplier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public record TNTExplosionListener(ItemManager itemManager) implements Listener {

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        event.blockList().forEach(block -> {
            if (itemManager.isItemPresent(block.getType())) {
                ItemMultiplier.dropItemsAt(itemManager, block.getLocation());
            }
        });
    }
}
