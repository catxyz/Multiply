package me.catxyz.drops.listeners;

import me.catxyz.drops.managers.ItemManager;
import me.catxyz.drops.utils.Items;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public record TNTExplosionListener(ItemManager itemManager) implements Listener {

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        event.blockList().forEach(block -> {
            if (itemManager.isItemPresent(block.getType())) {
                Items.dropMultipliedItems(itemManager, block);
            }
        });
    }
}
