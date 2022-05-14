package me.catxyz.multiply.listeners.fallingblock;

import com.google.common.collect.Lists;
import me.catxyz.multiply.Multiply;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.List;
import java.util.Objects;

public class FallingBlockHandler {

    private final List<Location> persistence;

    public FallingBlockHandler() {
        this.persistence = Lists.newArrayList();
        startPersistenceCleanup(false);
    }

    public void add(Location location) {
        if (!isPresent(location)) {
            persistence.add(location);
        }
    }

    public void drop(Location location) {
        persistence.remove(location);
    }

    public void startPersistenceCleanup(boolean debug) {
        Bukkit.getScheduler().runTaskTimer(Multiply.getInstance(), () -> Lists.newArrayList(persistence).forEach(location -> {
            Block block = Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(location);
            if (block.getType() == Material.AIR) {
                drop(location);
            }
            if (debug) {
                Bukkit.broadcastMessage(location.getBlock().getType() + " - " + location.toVector());
            }
        }), 0L, 20L);
    }

    public boolean isPresent(Location location) {
        return persistence.contains(location);
    }
}
