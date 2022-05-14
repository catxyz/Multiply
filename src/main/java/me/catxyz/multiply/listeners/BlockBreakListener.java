package me.catxyz.multiply.listeners;

import me.catxyz.multiply.managers.ItemManager;
import me.catxyz.multiply.utils.ItemMultiplier;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public record BlockBreakListener(ItemManager itemManager) implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        if (itemManager().isItemPresent(block.getType())) {
            ItemMultiplier.dropItemsAt(itemManager, block.getLocation());
        }
    }
}
