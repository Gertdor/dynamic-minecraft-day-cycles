/** Holds the length of a specific day, split into four parts. This is used to model a Minecraft 24-hour day and
 * to be able to define how long one part of the day can be in an easy way. Once a day cycle is created it can't
 * be altered to prevent unforeseen errors, such as defining the day as shorter than the time period that has already
 * passed.
 *<p>
 * The first part the cycle is when the sun is present and is referred to as the day.
 * The second part of the cycle is when the sun sets and is referred to as the dusk.
 * The third part of the cycle is when the sun isn't present and is referred to as the night.
 * The fourth part of the cycle is when the sun is rising and is reffered to as the dawn.
 *<p>
 * The standard unit of time that is used to model the day is Minecraft server ticks, but time can also be gotten
 * in seconds. This relies on the server running at 20 ticks per second. Any other tickrate causes the time to be
 * faulty when converted from ticks to seconds.
 *
 * @author: Bergland
 * @version 1.0
 * @since 2016-01-22
 */
public class DayCycle {


    private final int dayLength;
    private final int nightLength;
    private final int duskLength;
    private final int dawnLength;


    /** Constructs a day cycle object with all four day parts with a defined time in seconds.
     *
     * @param dayLength The new time of the day (When the sun is up) in real life seconds.
     * @param nightLength The new time of the night (When the sun is down) in real life seconds.
     * @param duskLength The new time of the dusk (When the sun is setting) in real life seconds.
     * @param dawnLength The new time of the dawn (When the sun is rising) in real life seconds.
     *
     * @since 1.0
     */
    public DayCycle(int dayLength, int nightLength, int duskLength, int dawnLength){
            this.dayLength = dayLength * 20;
            this.nightLength = nightLength * 20;
            this.duskLength = duskLength * 20;
            this.dawnLength = dawnLength * 20;
        }

    /** Constructs a day cycle object with a defined total time in seconds, using the minecraft standard ratio
     * between the four cycle parts. That is the day is 10/20 of the cycle, night 7/20, and dusk and dawn 3/40
     * respectively.
     * @param dayCycleLength The total time of the day in real life seconds.
     *
     * @since 1.0
     */
    public DayCycle(int dayCycleLength){
        this(dayCycleLength * 10/20, dayCycleLength * 7/20, dayCycleLength * 3/40, dayCycleLength * 3/40);
    }

    /**------------------------------------------------------------------------------------------------------**/

    /** Getters and setters **/

    /** Returns this day's day length in seconds.
     * @return The day length in seconds.
     *
     * @warning The time calculated isn't correct if the servers run at any tickrate other than 20 ticks/s
     * @since 1.0
     */
    public int getDaySeconds(){
        return dayLength / 20;
    }

    /** Returns this day cycle's night length in seconds.
     * @return The night length in seconds.
     *
     * @warning The time calculated isn't correct if the servers run at any tickrate other than 20 ticks/s
     * @since 1.0
     */
    public int getNightSeconds(){
        return nightLength / 20;
    }

    /** Returns this day cycle's dusk length in seconds.
     * @return The dusk length in seconds.
     *
     * @warning The time calculated isn't correct if the servers run at any tickrate other than 20 ticks/s
     * @since 1.0
     */
    public int getDuskSeconds(){
        return duskLength / 20;
    }

    /** Returns this day cycle's dawn length in seconds.
     * @return The dawn length in seconds.
     *
     * @warning The time calculated isn't correct if the servers run at any tickrate other than 20 ticks/s
     * @since 1.0
     */
    public int getDawnSeconds(){
        return dawnLength / 20;
    }

    /** Returns this day cycle's length in seconds.
     * @return The day cycles length in seconds.
     *
     * @warning The time calculated isn't correct if the servers run at any tickrate other than 20 ticks/s
     * @since 1.0
     */
    public int getCycleSeconds(){
        return getDawnSeconds() + getDaySeconds() + getDuskSeconds() + getNightSeconds();
    }

    /** Returns this day cycle's day length in server ticks.
     * @return The day time in serer ticks.
     *
     * @since 1.0
     */
    public int getDayLength() {
        return dayLength;
    }

    /** Returns this day cycle's night length in server ticks.
     * @return The night time in server ticks.
     *
     * @since 1.0
     */
    public int getNightLength() {
        return nightLength;
    }

    /** Returns this day cycle's dusk length in server ticks.
     * @return The day time in server ticks.
     *
     * @since 1.0
     */
    public int getDuskLength() {
        return duskLength;
    }

    /** Returns this day cycle's dawn length in server ticks.
     * @return The day time in server ticks.
     *
     * @since 1.0
     */
    public int getDawnLength() {
        return dawnLength;
    }

    /** Returns this day cycle's length in server ticks.
     * @return The total cycle time in server ticks.
     *
     * @since 1.0
     */
    public int getCycleLength(){
        return getDayLength() + getNightLength() + getDuskLength() + getDawnLength();
    }
}
