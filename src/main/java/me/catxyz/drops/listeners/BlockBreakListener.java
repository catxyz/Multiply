package me.catxyz.drops.listeners;

import me.catxyz.drops.managers.ItemManager;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public record BlockBreakListener(ItemManager itemManager) implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        if (itemManager().isItemPresent(block.getType())) {
            dropMultipliedItem(block);
        }
    }

    private void dropMultipliedItem(Block block) {
        World world = block.getWorld();
        itemManager.getItems().forEach((material, multiplier) -> {
            if (material == block.getType()) {
                if (multiplier > 64) {
                    for (int i = 0; i < multiplier; i++) {
                        world.dropItemNaturally(block.getLocation(), new ItemStack(material));
                    }
                } else {
                    world.dropItemNaturally(block.getLocation(), new ItemStack(material, multiplier));
                }
            }
        });
    }
}
