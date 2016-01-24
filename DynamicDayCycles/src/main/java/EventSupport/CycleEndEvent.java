package eventSupport;

import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/** Event notifying that the day of a world has ended, along with a possible message from it.
 *
 * @author Markus Bergland
 * @version 0.1
 * @since  2016-01-24
 */
public class CycleEndEvent extends Event {

    private static final HandlerList handlers  = new HandlerList();
    private final String message;
    private final World world;

    public CycleEndEvent(String message, World world){
        this.message = message;
        this.world = world;
    }

    public String getMessage() { return message; }

    public World getWorld(){ return world; }

    @Override
    public HandlerList getHandlers() { return handlers; }

    public static HandlerList getHandlerList(){ return handlers; }
}
