import DataHandlers.DayCycle;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.yaml.snakeyaml.introspector.PropertyUtils;

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

    /** Initiates the listener, allowing it to
     *
     */
    public CycleEndListener(List<CycleController> controllers){
        this.controllers = controllers;

    }

    @EventHandler
    public void endOfCycle(CycleEndEvent cycleEnd){
        World world = cycleEnd.getWorld();
        String message = cycleEnd.getMessage(); //TODO: Use message
        for (CycleController worldController : controllers){
            if (worldController.controlsWorld(world)){
                BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                BukkitRunnable nextCycle = new CycleRunner(world, worldController.getTomorrow(),
                        Utils.PropertyUtils.getInstance().getTickLength());
                
            }
        }
    }
}
