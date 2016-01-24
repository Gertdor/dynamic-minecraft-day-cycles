/** A plugin to have a changeable
 *
 *
 * @author Sebastian Norlin
 * @auther Markus Bergland
 * @version 0.1
 * @since 2016-01-22
 */
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DynamicDayCycles extends JavaPlugin{

    @Override
    public void onEnable(){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamerule doLightCycle false");
    }
    @Override
    public void onDisable(){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamerule doLightCycle true");
    }

}
