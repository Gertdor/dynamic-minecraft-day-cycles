package Utils;

import DataHandlers.PluginProperties;
import DataHandlers.Properties;
import DataHandlers.WorldProperties;

/** Singleton class to read and write to different property files used by the plugin.
 *
 * @author Bergland
 * @version 0.1
 * @since 2016-01-22
 */
public class PropertyUtils {

    private static PropertyUtils instance = null;
    private Properties props = null;

    /** Returns the singleton instance, or creates one if there are none.
     * @return The singleton instance.
     */
    public static PropertyUtils getInstance(){
        if (instance == null){
            instance = new PropertyUtils();
        }
        return  instance;
    }

    /** Reloads the property object that utils read from, including both world- and pluginproperties.
     * If the saveAndReload parameter is set to true saves before reloading, otherwise load directly from file.
     * @param saveAndReload Defines whatever or not to save the current property before reloading.
     */
    private void reloadProperties(Boolean saveAndReload){
        if (saveAndReload){

        } else {
            props = new Properties();
        }
    }

    /** Gets the property defined tick length used between updating the time cycle.
     * @return The number of server ticks between time cycle updating.
     */
    public int getTickLength(){
        return props.tickLength();
    }

    public boolean isVerboseEvents(){
        return props.verboseEvents();
    }

}
