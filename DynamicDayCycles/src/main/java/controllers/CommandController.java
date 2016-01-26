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

    /** Reads the /ddc use command, and then calls the appropriate function for it.
     *
     * @param cmdSender The sender of the command.
     * @param label The label of the command.
     * @param args The arguments of the command.
     * @return True if the command is executed, false otherwise.
     *
     * @since 0.1
     */
    private boolean onUseCommand(CommandSender cmdSender, String label, String[] args){
        String type = args[1];
        switch (type){
            case "mul":
                return onUseMul(cmdSender, label, args);
            case "set":
                return onUseSet(cmdSender, label, args);
            case "gen":
                return true;
            default:
                cmdSender.sendMessage("Couldn't find Cycle type, please use command as /ddc use mul/set/gen " +
                        "<Time values> [world]");
                return false;
        }
    }

    /** Reads and executes the /ddc use mul command, including handling too many and too few arguments, as well
     * as who the sender is and their permissions.
     * @param cmdSender The sender of the command.
     * @param label The label of the command.
     * @param args The arguments of the command.
     * @return True if the command is executed, false otherwise.
     *
     * @since 0.1
     */
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
            cmdSender.sendMessage("Faulty argument for /ddc use mul §4#multiplier§r. Expected a positive number," +
                    " found a negative number.");
            return false;
        } else if (value < 0.01 || value > 72){
            cmdSender.sendMessage("Faulty argument for /ddc use mul §4#miltiplier§r. Expected number in range 0.01" +
                    " to 72, found " + value);
            return false;
        }


        if (args.length == 4) {
            String world = args[3];
            for (CycleController controller : cycleControllers) {
                if (controller.controlsWorld(world)) {
                    controller.setStateMultiplier(value);
                    cmdSender.sendMessage("Successfully set time cycle in " +  world + " to use a multiplied time. " +
                            "Multiplier is now " + value + ", and total day length is now " + value * 1200 +
                            "seconds long.");
                    cmdSender.sendMessage("Use /ddc tomorrow to fast forward time to new cycle.");
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
                    cmdSender.sendMessage("Successfully set time cycle in " +  pWorld.getName() + " to use a " +
                            "multiplied time. Multiplier is now " + value + ", and total day length is now " +
                            value * 1200 + "seconds long.");
                    cmdSender.sendMessage("Use /ddc tomorrow to fast forward time to new cycle.");
                    return true;
                }
            }
        } else {
            cmdSender.sendMessage("Faulty arguments for /ddc use mul #multiplier §4world§r. " +
                    "Expected a world, found none.");
            return false;
        }

        return false;


    }

    /** Reads and executes the /ddc use set command, including handling too many and too few arguments, as well
     * as who the sender is and their permissions.
     * @param cmdSender The sender of the command.
     * @param label The label of the command.
     * @param args The arguments of the command.
     * @return True if the command is executed, false otherwise.
     *
     * @since 0.1
     */
    private boolean onUseSet(CommandSender cmdSender, String label, String[] args){
        if (!cmdSender.hasPermission("ddc.use.set")){
            cmdSender.sendMessage("You don't have permission to use /ddc use set");
            return false;
        }
        if (args.length < 6){
            cmdSender.sendMessage("Too few arguments for /ddc use set #day #dusk #night #dawn [world]. Should be at " +
                    "least 6, you had " + (args.length - 2));
            return false;
        } else if (args.length > 7){
            cmdSender.sendMessage("Too many arguments for /ddc use set #day #dusk #night #dawn [world]. Should be at " +
                    "most 7, you had " + (args.length - 2));
        }

        int dayLength;
        int duskLength;
        int nightLength;
        int dawnLength;

        try {
            dayLength = Integer.parseInt(args[3]);
            duskLength = Integer.parseInt(args[4]);
            nightLength = Integer.parseInt(args[5]);
            dawnLength = Integer.parseInt(args[6]);
        } catch (NumberFormatException nfe) {
            cmdSender.sendMessage("Faulty argument(s) for /ddc use set §4#day #dusk #night #dawn§r [world]. " +
                    "Expected numbers, found at least 1 non-number.");
            return false;
        }

        if (dayLength < 0 || duskLength < 0 || nightLength < 0 || dawnLength < 0){
            cmdSender.sendMessage("Faulty argument(s) for /ddc use set §4#day #dusk #night #dawn§r [world]. " +
                    "Expected positive numbers, found at least 1 negative number.");
            return false;
        } else if (dayLength > Integer.MAX_VALUE || duskLength > Integer.MAX_VALUE || nightLength > Integer.MAX_VALUE
                || dawnLength > Integer.MAX_VALUE){
            cmdSender.sendMessage("Faulty argument(s) for /ddc use set §4#day #dusk #night #dawn§r [world]. " +
                    "Expected numbers below " + Integer.MAX_VALUE + ". Found at least 1 number above.");
            return false;
        }

        if (args.length == 7) {
            String world = args[6];
            for (CycleController controller : cycleControllers) {
                if (controller.controlsWorld(world)) {
                    controller.setStateDefined(dayLength, nightLength, duskLength, dawnLength);
                    cmdSender.sendMessage("Successfully set the day cycle in " +  world + " to use a" +
                            "set time. Length is now " + dayLength + " (day length), " + duskLength + " (dusk length), "
                            + nightLength + " (night length), " + dawnLength + " (dawn length). Total day cycle is" +
                            "now " + (dayLength + duskLength + nightLength + dawnLength) + " seconds long.");
                    cmdSender.sendMessage("Use /ddc tomorrow to fast forward time to new cycle.");
                    return true;
                }
            }
            cmdSender.sendMessage("Faulty argument(s) for /ddc use set #day #dusk #night #dawn §4world§r. " +
                    "Expected an existing world as world, found no world with that name.");
            return false;
        }
        if (cmdSender instanceof Player) {
            World pWorld = ((Player) cmdSender).getWorld();

            for (CycleController controller : cycleControllers) {
                if (controller.controlsWorld(pWorld)) {
                    controller.setStateDefined(dayLength, nightLength, duskLength, dawnLength);
                    cmdSender.sendMessage("Successfully set the day cycle in " +  pWorld.getName() + " to use a" +
                            "set time. Length is now " + dayLength + " (day length), " + duskLength + " (dusk length), "
                            + nightLength + " (night length), " + dawnLength + " (dawn length). Total day cycle is" +
                            "now " + (dayLength + duskLength + nightLength + dawnLength) + " seconds long.");
                    cmdSender.sendMessage("Use /ddc tomorrow to fast forward time to new cycle.");
                    return true;
                }
            }
        } else {
            cmdSender.sendMessage("Faulty arguments for /ddc use set #day #dusk #night #dawn §4world§r. " +
                    "Expected a world, found none.");
            return false;
        }

        return false;
    }
}
