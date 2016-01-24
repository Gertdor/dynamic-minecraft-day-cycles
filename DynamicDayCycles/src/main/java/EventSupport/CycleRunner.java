package eventSupport;

import dataHandlers.DayCycle;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

/** Runs the time changing on the day.
 *
 * @author Bergland
 * @version 0.1
 * @since 2016-01-22
 */
public class CycleRunner extends BukkitRunnable {

    private final World world;
    private final DayCycle cycle;
    private final double dayRate;
    private final double nightRate;
    private final double dawnRate;
    private final double duskRate;
    private final int ticksBtwRuns;

    private double timeToSet;

    public CycleRunner(World world, DayCycle cycle, int ticksBtwRuns){
        this.world = world;
        this.cycle = cycle;
        this.ticksBtwRuns = ticksBtwRuns;

        dayRate = 12000 / cycle.getDayLength();
        dawnRate = 1800 / cycle.getDawnLength();
        duskRate = 1800 / cycle.getDuskLength();
        nightRate = 8400 / cycle.getNightLength();
    }

    @Override
    public void run() {
        int wTime = (int) world.getTime();
        if (wTime < 12000){
            timeToSet += ticksBtwRuns * dayRate;
        } else if (wTime < 13800){
            timeToSet += ticksBtwRuns * duskRate;
        } else if (wTime < 22200){
            timeToSet += ticksBtwRuns * dawnRate;
        } else {
            timeToSet += ticksBtwRuns * dawnRate;
        }
        if (timeToSet <= 24000){
            world.setTime((long) timeToSet);
        } else {
            world.setTime((long) timeToSet - 24000);
            this.cancel();
            CycleEndEvent endEvent = new CycleEndEvent("End of day", world);
            Bukkit.getServer().getPluginManager().callEvent(endEvent);
        }
    }
}
