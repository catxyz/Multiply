package me.catxyz.multiply.utils;

import me.catxyz.multiply.managers.ItemManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ItemMultiplier {

    public static void dropItemsAt(ItemManager itemManager, Location location) {
        World world = location.getWorld();
        Objects.requireNonNull(world);

        itemManager.getItems().forEach((material, multiplier) -> {
            if (material == location.getBlock().getType()) {
                if (multiplier > 127) {
                    for (int i = 0; i < multiplier; i++) {
                        world.dropItemNaturally(location, new ItemStack(material));
                    }
                } else {
                    world.dropItemNaturally(location, new ItemStack(material, multiplier));
                }
            }
        });
    }
}
