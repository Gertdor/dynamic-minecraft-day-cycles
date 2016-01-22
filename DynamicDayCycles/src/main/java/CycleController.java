import org.bukkit.Server;

/** Controls the time flow of a Minecraft day through using one of three methods:
 * <ul>
 * <li> A static multiplier of the day cycle's length
 * <li> Defining the  time of a day cycle, and possibly any of its parts
 * <li> By following the actual day and night cycle over the seasons.
 * </ul>
 * <p>
 * When using a static multiplier of a day cycle the day will grow longer with a larger multiplier and shorter
 * with a smaller one, down to a multiplier of 0.01 when a full day-night cycle passes 240 server ticks, or 12 seconds
 * on a server with a tick rate of 20 ticks per second.
 * <p>
 * The user can also specify the exact time of any and all parts of a day cycle, fully removing some parts. The shortest
 * total time a day is allowed to have is limited to 200 server ticks, or 10 seconds on a server with a tick rate of 20
 * ticks per second. Faster than this would not allow the graphical representation to behave in a satisfying way.
 * <p>
 * Lastly the user is allowed to define a place and date of which they wish to base their cycle length. This will then
 * procedurally generate day and night cycle throughout a 365 day period with days and nights growing in size depending
 * on both location and on day of the year with the latter changing.
 * <p>
 *
 * @author Bergland
 * @version 0.1
 * @since 2016-01-22
 */
public class CycleController {

    private final Server server;
    private DayCycle today;
    private DayCycle tomorrow;
    private CycleState state;
    private int currentTime;

    private double cycleMultiplier = 1;

    public CycleController(Server server){
        this.server = server;
        state = CycleState.MULTIPLIER;
    }

    public void setStateMultiplier(double cycleMultiplier){
        state = CycleState.MULTIPLIER;
        this.cycleMultiplier = cycleMultiplier;
    }

    public void setStateDefined(int dayTime, int nightTime, int duskTime, int dawnTime){
        state = CycleState.DEFINED;
    }

    public void setStateGenerated(double longitude, int day){
        state = CycleState.GENERATED;
    }


    private enum CycleState{
        MULTIPLIER, DEFINED, GENERATED;
    }
}
