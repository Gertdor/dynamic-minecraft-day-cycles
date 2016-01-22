/** A plugin to have a changeable
 *
 *
 * @author Sebastian Norlin
 * @auther Markus Bergland
 * @version 0.1
 * @since 2016-01-22
 */
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class DynamicDayCycles extends JavaPlugin{

    private List<CycleController> cycleControllers = new ArrayList<>();

    @Override
    public void onEnable(){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamerule doLightCycle false");
        for (World w : Bukkit.getServer().getWorlds()){
            cycleControllers.add(new CycleController(w));
        }
    }
    @Override
    public void onDisable(){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamerule doLightCycle true");

    }
}
