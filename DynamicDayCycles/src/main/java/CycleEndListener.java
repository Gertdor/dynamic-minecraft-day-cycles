import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/** Listener for custom Bukkit event of DayChange to be used with custom minecraft day cycles. The events are sent
 * through the runner and are then used by the controller
 *
 * @author Markus Bergland
 * @version 0.1
 * @since 2016-01-23
 */
public class CycleEndListener implements Listener {

    /** Initiates the listener, allowing it to
     *
     */
    public CycleEndListener(){

    }

    @EventHandler
    public void endOfCycle(CycleEndEvent cycleEnd){

    }
}
