package me.catxyz.drops.utils;

import me.catxyz.drops.managers.ItemManager;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class Items {

    public static void dropMultipliedItems(ItemManager itemManager, Block block) {
        World world = block.getWorld();
        itemManager.getItems().forEach((material, multiplier) -> {
            if (material == block.getType()) {
                if (multiplier > 127) {
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
