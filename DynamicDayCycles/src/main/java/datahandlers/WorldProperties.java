package datahandlers;

import controllers.CycleController;
import org.bukkit.World;

import java.util.List;
import java.util.NoSuchElementException;

/** Properties for a specific world, including its default cycle and other settings
 *
 * @author Markus Bergland
 * @version 0.1
 * @since 2016-01-24
 */
public interface WorldProperties {


    /** Returns the default controller for a world given its reference.
     *
     * @param world The world that we want to know the default controller of.
     * @return The default controller of the world queried for.
     * @throws NoSuchElementException if no default controller is associated with that world.
     *
     * @since 0.1
     */
    CycleController defaultController(World world) throws NoSuchElementException;


    /** Returns all default controllers.
     * Note that this does not guarantee that each world has a controller, it merely
     * guarantees that the controller's that are returned are all the default controllers know.
     *
     * @return All default controllers being kept track of.
     *
     * @since 0.1
     */
    List<CycleController> defaultControllerList();

}
