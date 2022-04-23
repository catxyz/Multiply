package me.catxyz.drops.utils;

import me.catxyz.drops.Drops;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class Items {

    public static void dropMultipliedItems(Block block) {
        World world = block.getWorld();
        Drops.getInstance().getItemManager().getItems().forEach((material, multiplier) -> {
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
