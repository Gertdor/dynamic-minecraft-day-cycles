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

    private double cycleMultiplier;

    private int dayTime;
    private int nightTime;
    private int duskTime;
    private int dawnTime;

    private double longitude;
    private int day;

    /** Creates a new cycle controller object with a specified server to run on.
     * Defaults the state to a multiplier state with a time multiplier of 1.
     * @param server
     */
    public CycleController(Server server){
        this.server = server;
        setStateMultiplier(1);
    }

    /** Sets the current controller state to a time multiplier state with a specified multiplier.
     *
     * @param cycleMultiplier The time multiplier to be used.
     *
     * @since 0.1
     */
    public void setStateMultiplier(double cycleMultiplier){
        state = CycleState.MULTIPLIER;
        this.cycleMultiplier = cycleMultiplier;
    }

    /** Sets the current cycle state to a specified day state with all parts individually defined.
     *
     * @param dayTime The day time in seconds.
     * @param nightTime The night time in seconds.
     * @param duskTime The dusk time in seconds.
     * @param dawnTime The dawn time in seconds.
     *
     * @since 0.1
     */
    public void setStateDefined(int dayTime, int nightTime, int duskTime, int dawnTime){
        state = CycleState.DEFINED;
        this.dayTime = dayTime;
        this.nightTime = nightTime;
        this.duskTime = duskTime;
        this.dawnTime = dawnTime;
    }

    /** Sets the current cycle state to a procedurally generated cycle based on a specific longitude and a day.
     * This also takes in a cycle multiplier to multiply the length of the day with.
     *
     * @param longitude The latitude to base our cycles on.
     * @param day The start day of our generated cycles.
     * @param cycleMultiplier The time multiplier to be used.
     *
     * @since 0.1
     */
    public void setStateGenerated(double longitude, int day, int cycleMultiplier){
        state = CycleState.GENERATED;
        this.longitude = longitude;
        this.day = day;
        this.cycleMultiplier = cycleMultiplier;
    }


    private enum CycleState{
        MULTIPLIER, DEFINED, GENERATED;
    }
}
