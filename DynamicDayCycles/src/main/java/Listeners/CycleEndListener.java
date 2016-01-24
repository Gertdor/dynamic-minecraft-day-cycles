package listeners;

import controllers.CycleController;
import dataHandlers.DayCycle;
import utility.PropertyUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;

/** Listener for custom Bukkit event of DayChange to be used with custom minecraft day cycles. The events are sent
 * through the runner and are then used by the controller
 *
 * @author Markus Bergland
 * @version 0.1
 * @since 2016-01-23
 */
public class CycleEndListener implements Listener {

    private final List<CycleController> controllers;

    private final Plugin plugin;

    /** Initiates the listener, allowing it to
     *
     */
    public CycleEndListener(List<CycleController> controllers, JavaPlugin plugin){
        this.controllers = controllers;
        this.plugin = plugin;

    }

    @EventHandler
    public void endOfCycle(CycleEndEvent cycleEnd){
        World world = cycleEnd.getWorld();
        if (PropertyUtils.getInstance().isVerboseEvents()){
            plugin.getLogger().info(cycleEnd.getMessage());
        }
        for (CycleController worldController : controllers){
            if (worldController.controlsWorld(world)){
                DayCycle tomorrow = worldController.getTomorrow();
                int tickLength = PropertyUtils.getInstance().getTickLength();
                CycleRunner runner = new CycleRunner(world, tomorrow, tickLength);
                BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                scheduler.scheduleSyncRepeatingTask(plugin, runner, 0, tickLength);
                return;
            }
        }
    }
}
