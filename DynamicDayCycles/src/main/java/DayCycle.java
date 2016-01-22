/** Holds data for how the clock should be handled within the plugin.
 *
 * @Author: Markus Bergland & Sebastian Norlin
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
     */
    public DayCycle(int dayCycleLength){
        this(dayCycleLength * 10/20, dayCycleLength * 7/20, dayCycleLength * 3/40, dayCycleLength * 3/40);
    }

    /**------------------------------------------------------------------------------------------------------**/

    /** Getters and setters **/

    /** Returns this day's day length in seconds.
     * @return The day length in seconds.
     */
    public int getDaySeconds(){
        return dayLength / 20;
    }

    /** Returns this day cycle's night length in seconds.
     * @return The night length in seconds.
     */
    public int getNightSeconds(){
        return nightLength / 20;
    }

    /** Returns this day cycle's dusk length in seconds.
     * @return The dusk length in seconds.
     */
    public int getDuskSeconds(){
        return duskLength / 20;
    }

    /** Returns this day cycle's dawn length in seconds.
     * @return The dawn length in seconds.
     */
    public int getDawnSeconds(){
        return dawnLength / 20;
    }

    /** Returns this day cycle's length in seconds.
     * @return The day cycles length in seconds.
     */
    public int getCycleSeconds(){
        return getDawnSeconds() + getDaySeconds() + getDuskSeconds() + getNightSeconds();
    }

    /** Returns this day cycle's day length in server ticks.
     * @return The day time in serer ticks.
     */
    public int getDayLength() {
        return dayLength;
    }

    /** Returns this day cycle's night length in server ticks.
     * @return The night time in server ticks.
     */
    public int getNightLength() {
        return nightLength;
    }

    /** Returns this day cycle's dusk length in server ticks.
     * @return The day time in server ticks.
     */
    public int getDuskLength() {
        return duskLength;
    }

    /** Returns this day cycle's dawn length in server ticks.
     * @return The day time in server ticks.
     */
    public int getDawnLength() {
        return dawnLength;
    }

    /** Returns this day cycle's length in server ticks.
     * @return The total cycle time in server ticks.
     */
    public int getCycleLength(){
        return getDawnLength() + getNightLength() + getDuskLength() + getDawnLength();
    }
}
