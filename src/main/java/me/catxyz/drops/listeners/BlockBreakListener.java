package me.catxyz.drops.listeners;

import me.catxyz.drops.managers.ItemManager;
import me.catxyz.drops.utils.Items;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public record BlockBreakListener(ItemManager itemManager) implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        if (itemManager().isItemPresent(block.getType())) {
            Items.dropMultipliedItems(block);
        }
    }
}
