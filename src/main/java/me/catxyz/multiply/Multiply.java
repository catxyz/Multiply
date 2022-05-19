package me.catxyz.multiply;

import com.google.common.base.Preconditions;
import me.catxyz.multiply.commands.MultiplierCommand;
import me.catxyz.multiply.components.TextComponents;
import me.catxyz.multiply.listeners.AdminCommandsListener;
import me.catxyz.multiply.listeners.BlockBreakListener;
import me.catxyz.multiply.listeners.TNTExplosionListener;
import me.catxyz.multiply.listeners.fallingblock.FallingBlockListener;
import me.catxyz.multiply.managers.ItemManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Multiply extends JavaPlugin {

    private static Multiply INSTANCE;
    public static String DEFAULT_PERMISSION_NODE = "multiply.multiplier";
    private ItemManager itemManager;

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.itemManager = new ItemManager();

        Preconditions.checkNotNull(getCommand("multiplier")).setExecutor(new MultiplierCommand(
                itemManager,
                new TextComponents()
        ));

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new BlockBreakListener(itemManager), this);
        pluginManager.registerEvents(new TNTExplosionListener(itemManager), this);
        pluginManager.registerEvents(new FallingBlockListener(itemManager), this);
        pluginManager.registerEvents(new AdminCommandsListener(itemManager), this);

        getLogger().info(getName() + " successfully enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info(getName() + " successfully disabled!");
    }

    public static Multiply getInstance() {
        return INSTANCE;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }
}
