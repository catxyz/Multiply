package me.catxyz.drops.managers;

import com.google.common.collect.Maps;
import me.catxyz.drops.utils.Checks;
import me.catxyz.drops.utils.Format;
import me.catxyz.drops.utils.Text;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.stream.Stream;

public class ItemManager {

    private final Map<Material, Integer> items;

    public ItemManager() {
        this.items = Maps.newHashMap();
    }

    public void addItem(Material material, int multiplier) {
        items.put(material, multiplier);
    }

    public void removeItem(Material material) {
        items.remove(material);
    }

    public void processCommand(Player player, String[] args) {
        String itemId = Format.formatItemNamespace(args);
        if (!Checks.isNumber(args[0])) {
            player.sendMessage(Text.format("&c" + args[0] + " is not a number."));
            return;
        }

        int multiplier = Integer.parseInt(args[0]);
        if (isLimitExceeded(player, multiplier)) {
            return;
        }

        Material material;
        if (!doesItemExist(itemId)) {
            player.sendMessage(Text.format("&cItem " + itemId + " not found."));
            return;
        }
        material = Material.valueOf(itemId);

        if (!material.isBlock()) {
            player.sendMessage(Text.format("&cItem " + itemId + " is not a block."));
            return;
        }
        checkAndAdd(player, material, multiplier);
    }

    public void processAddAll(Player player, int multiplier) {
        Stream.of(Material.values())
                .filter(Material::isBlock)
                .forEach(material -> checkAndAdd(player, material, multiplier));
    }

    private void checkAndAdd(Player player, Material material, int multiplier) {
        if (!isItemPresent(material)) {
            addItem(material, multiplier);
            player.sendMessage(Text.format(
                    "&aAdded &b" + material + " &awith a multiplier of &e" + multiplier
            ));
        } else {
            removeItem(material);
            addItem(material, multiplier);
            player.sendMessage(Text.format(
                    "&aUpdated &b" + material + " &awith a multiplier of &e" + multiplier
            ));
        }
    }

    public boolean isLimitExceeded(Player player, int multiplier) {
        int maxMultiplier = 5120;
        if (multiplier <= 0 || multiplier > maxMultiplier) {
            player.sendMessage(Text.format("&cMultiplier cannot be 0 or less, or greater than " + maxMultiplier + "."));
            return true;
        }
        return false;
    }

    public boolean doesItemExist(String itemId) {
        return Material.getMaterial(itemId) != null;
    }

    public boolean isItemPresent(Material material) {
        return items.containsKey(material);
    }

    public Map<Material, Integer> getItems() {
        return items;
    }
}
