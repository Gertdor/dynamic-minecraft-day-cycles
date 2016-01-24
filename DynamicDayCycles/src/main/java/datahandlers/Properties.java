package datahandlers;

import controllers.CycleController;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/** Data holder for all property data.
 *
 * @author Markus Bergland
 * @version 0.1
 * @since 2016-01-24
 */
public class Properties implements WorldProperties, PluginProperties {

    private int tickLength;

    private boolean verboseEvents;

    private Map<World, CycleController> defaultControllers;

    /** Creates a new property object from local .property files. If no files are found, creates a new .property
     * file for each world and for the plugin itself with default values.
     *
     * @since 0.1
     */
    public Properties(){
        tickLength = 5;
        verboseEvents = true;
        //TODO: Read from .properties file
    }

    @Override
    public int tickLength() {
        return tickLength;
    }

    @Override
    public boolean verboseEvents() {
        return verboseEvents;
    }

    @Override
    public CycleController defaultController(World world) throws NoSuchElementException{
        if (defaultControllers.containsKey(world)){
            return defaultControllers.get(world);
        } else {
            throw new NoSuchElementException("Could not find a default controller for that world.");
        }
    }

    @Override
    public List<CycleController> defaultControllerList() {
        return new ArrayList<>(defaultControllers.values());
    }
}
