package controllers;

import main.DynamicDayCycles;
import org.bukkit.World;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 *
 * @author Bergland
 * @version 0.1
 * @since 2016-01-24
 */
public class CommandController implements CommandExecutor {

    private final DynamicDayCycles plugin;
    private final List<CycleController> cycleControllers;

    public CommandController(DynamicDayCycles plugin, List<CycleController> cycleControllers){
        this.cycleControllers = cycleControllers;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (command.getName().equalsIgnoreCase("ddc")){
            if (! commandSender.hasPermission("ddc.basic")){
                commandSender.sendMessage("You don't have permission to use /ddc commands.");
                return false;
            }
            String subCommand = strings[0].toLowerCase();
            switch (subCommand){
                case "pause":
                    if (!commandSender.hasPermission("ddc.pause")){
                        commandSender.sendMessage("You don't have permission to use /ddc pause");
                        return false;
                    }
                    if (strings.length > 1){
                        commandSender.sendMessage("Too many arguments for /ddc pause. Should be 0, you had "
                                                    + (strings.length - 1) );
                        return false;
                    } else {
                        //TODO: Handle pause
                    }

                case "use":
                    if (!commandSender.hasPermission("")){
                        commandSender.sendMessage("You don't have permission to use /ddc use commands");
                        return false;
                    }
                    if (strings.length < 2){
                        commandSender.sendMessage("Too few arguments for /ddc use mul/set/gen <Time values> [world]. Should be" +
                                "at least 2, you had " +  (strings.length - 1));
                        return false;
                    }
                    return onUseCommand(commandSender, s, strings);
                default:
                    commandSender.sendMessage("That command is unknown for /ddc");
                    commandSender.sendMessage("Please use /ddc help for information about the commands available");
                    return false;
            }
        } else {
          return false;
        }

    }

    private boolean onUseCommand(CommandSender cmdSender, String label, String[] args){
        String type = args[1];
        switch (type){
            case "mul":
                return onUseMul(cmdSender, label, args);
            case "set":
                return true;
            case "gen":
                return true;
            default:
                cmdSender.sendMessage("Couldn't find Cycle type, please use command as /ddc use mul/set/gen " +
                        "<Time values> [world]");
                return false;
        }
    }

    private boolean onUseMul(CommandSender cmdSender, String label, String[] args) {

        if (!cmdSender.hasPermission("ddc.use.mul")) {
            cmdSender.sendMessage("You don't have permission to use /ddc use mul");
            return false;
        }
        if (args.length < 3) {
            cmdSender.sendMessage("Too few arguments for /ddc use mul #multiplier [world]. Should be at least 1, you had 0");
            return false;
        } else if (args.length > 4) {
            cmdSender.sendMessage("Too many arguments for /ddc use mul #multiplier [world]. Should be at most 2, you had "
                    + (args.length - 2));
            return false;
        }

        double value;

        try {
            value = Double.parseDouble(args[2]);
        } catch (NumberFormatException nfe) {
            cmdSender.sendMessage("Faulty argument for /ddc use mul §4#0.01-72.§r" +
                    "Expected a number, found a string.");
            return false;
        }

        if (value < 0){
            cmdSender.sendMessage("Faulty argument for /ddc use mul §4#0.01-72§r. Expected a positive number," +
                    " found a negative number.");
            return false;
        } else if (value < 0.01 || value > 72){
            cmdSender.sendMessage("Faulty argument for /ddc use mul §4#0.01-72§r. Expected number out of range");
            return false;
        }


        if (args.length == 4) {
            String world = args[3];
            for (CycleController controller : cycleControllers) {
                if (controller.controlsWorld(world)) {
                    controller.setStateMultiplier(value);
                    return true;
                }
            }
            cmdSender.sendMessage("Fault arguments for /ddc use mul #multiplier §4world§r. Expected an existing world as" +
                    " world, found no world with that name.");
            return false;
        }
        if (cmdSender instanceof Player) {
            World pWorld = ((Player) cmdSender).getWorld();

            for (CycleController controller : cycleControllers) {
                if (controller.controlsWorld(pWorld)) {
                    controller.setStateMultiplier(value);
                }
            }
        } else {
            cmdSender.sendMessage("Faulty arguments for /ddc use mul #multiplier §4world§r. " +
                    "Expected a world, found none.");
        }

        return false;


    }
}
