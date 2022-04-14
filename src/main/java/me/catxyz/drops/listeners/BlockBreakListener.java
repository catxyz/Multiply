package me.catxyz.drops.listeners;

import me.catxyz.drops.Drops;
import me.catxyz.drops.managers.ItemManager;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;

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
                try {
                    world.dropItemNaturally(block.getLocation(), new ItemStack(material, multiplier));
                } catch (Exception e) {
                    Drops.getInstance().getLogger().log(
                            Level.SEVERE,
                            "Something went wrong! (message: " + e.getMessage() + ", runtime class: " + e.getClass() + ")"
                    );
                    e.printStackTrace();
                }
            }
        });
    }
}
