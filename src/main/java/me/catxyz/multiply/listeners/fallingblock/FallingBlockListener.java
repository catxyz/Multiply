package me.catxyz.multiply.listeners.fallingblock;

import me.catxyz.multiply.managers.ItemManager;
import me.catxyz.multiply.utils.ItemMultiplier;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class FallingBlockListener implements Listener {

    private final ItemManager itemManager;
    private final FallingBlockHandler handler;

    public FallingBlockListener(ItemManager itemManager) {
        this.itemManager = itemManager;
        this.handler = new FallingBlockHandler();
    }

    @EventHandler
    public void onBlockFall(EntityChangeBlockEvent event) {
        if (event.getEntityType() == EntityType.FALLING_BLOCK) {
            FallingBlock fallingBlock = ((FallingBlock) event.getEntity());
            if (itemManager.isItemPresent(fallingBlock.getBlockData().getMaterial())) {
                if (!handler.isPresent(fallingBlock.getLocation())) {
                    ItemMultiplier.dropItemsAt(itemManager, fallingBlock.getLocation());
                }
                handler.add(fallingBlock.getLocation());
            }
        }
    }
}
