package me.catxyz.drops;

import com.google.common.base.Preconditions;
import me.catxyz.drops.commands.MultiplierCommand;
import me.catxyz.drops.listeners.AdminCommandsListener;
import me.catxyz.drops.listeners.BlockBreakListener;
import me.catxyz.drops.listeners.TNTExplosionListener;
import me.catxyz.drops.managers.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Drops extends JavaPlugin {

    private static Drops INSTANCE;
    public static String DEFAULT_PERMISSION_NODE = "drops.multiplier";
    private ItemManager itemManager;

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.itemManager = new ItemManager();

        Preconditions.checkNotNull(getCommand("multiplier")).setExecutor(new MultiplierCommand(itemManager));
        getServer().getPluginManager().registerEvents(new BlockBreakListener(itemManager), this);
        getServer().getPluginManager().registerEvents(new TNTExplosionListener(itemManager), this);
        getServer().getPluginManager().registerEvents(new AdminCommandsListener(itemManager), this);

        getLogger().info("Drops successfully enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Drops successfully disabled!");
    }

    public static Drops getInstance() {
        return INSTANCE;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }
}
