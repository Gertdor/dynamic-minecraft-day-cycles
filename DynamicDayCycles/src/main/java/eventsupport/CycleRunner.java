package eventsupport;

import datahandlers.DayCycle;
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

        dayRate = cycle.getDayLength() != 0 ? 12000 / cycle.getDayLength() : 0;
        dawnRate = cycle.getDawnLength() != 0 ? 1800 / cycle.getDawnLength(): 0;
        duskRate = cycle.getDuskLength() != 0 ? 1800 / cycle.getDuskLength() : 0;
        nightRate = cycle.getNightLength() != 0 ? 8400 / cycle.getNightLength() : 0;
    }

    @Override
    public void run() {
        int wTime = (int) world.getTime();
        if (wTime < 12000){
            if (dayRate == 0){
                timeToSet = 12000;
            } else {
                timeToSet += ticksBtwRuns * dayRate;
            }
        } else if (wTime < 13800){
            if (duskRate == 0){
                timeToSet = 13800;
            } else {
                timeToSet += ticksBtwRuns * duskRate;
            }
        } else if (wTime < 22200){
            if (nightRate == 0){
                timeToSet = 22200;
            } else {
                timeToSet += ticksBtwRuns * nightRate;
            }
        } else{
            if (dawnRate == 0){
                timeToSet = 24000;
            } else {
                timeToSet += ticksBtwRuns * dawnRate;
            }
        }
        if (timeToSet < 24000){
            world.setTime((long) timeToSet);
        } else {
            world.setTime((long) timeToSet - 24000);
            this.cancel();
            CycleEndEvent endEvent = new CycleEndEvent("End of day", world);
            Bukkit.getServer().getPluginManager().callEvent(endEvent);
        }
    }
}
