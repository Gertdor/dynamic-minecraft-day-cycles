package dataHandlers;

/** Interface for the properties of the main plugin, such as user information, default messages, and so on.
 * Work in progress!
 *
 * @author Markus Bergland
 * @version 0.1
 * @since 2016-01-24
 */
public interface PluginProperties {

    /** Gets the number of server ticks that is to be used in between updating the day cycle.
     * @return The number of server ticks.
     *
     * @since 0.1
     */
    int tickLength();

    /** Checks whatever or not the server is using a verbose event call, toggling if they write out messages in the
     * console or not.
     * @return True if verbose events are on, false otherwise.
     *
     * @since 0.1
     */
    boolean verboseEvents();

}
