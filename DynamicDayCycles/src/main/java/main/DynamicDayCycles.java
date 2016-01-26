package main; /** A plugin to have a changeable
 *
 *
 * @author Sebastian Norlin
 * @auther Markus Bergland
 * @version 0.1
 * @since 2016-01-22
 */
import controllers.CommandController;
import controllers.CycleController;
import listeners.CycleEndListener;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class DynamicDayCycles extends JavaPlugin{

    private final List<CycleController> cycleControllers = new ArrayList<>();

    @Override
    public void onEnable(){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamerule doLightCycle false");
        //TODO: Load default controllers instead of creating new ones.
        for (World w : Bukkit.getServer().getWorlds()){
            cycleControllers.add(new CycleController(w));
        }
        this.getServer().getPluginManager().registerEvents(new CycleEndListener(cycleControllers, this), this);
        this.getCommand("ddc").setExecutor(new CommandController(this, cycleControllers));
    }
    @Override
    public void onDisable(){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamerule doLightCycle true");
        HandlerList.unregisterAll(this);
    }

}
