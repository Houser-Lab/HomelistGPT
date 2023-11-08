package xyz.housers.homelistgpt.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.housers.homelistgpt.HomelistGPT;
import xyz.housers.homelistgpt.HomeListGUI;

import java.util.Map;

import static xyz.housers.homelistgpt.HomeListGUI.openHomeList;

public class HomeCommand implements CommandExecutor {
    private final Map<String, Location> homes;

    public HomeCommand(Map<String, Location> homes) {
        this.homes = homes;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the sender is not a player
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true; // Exit the command execution
        }

        // Cast the sender to a Player object
        Player player = (Player) sender;

        // Check if the command has the correct number of arguments
        if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
            // The player has used "/home list" command

            openHomeList(player, xyz.housers.homelistgpt.HomelistGPT.homes);
            player.sendMessage("Opening home list GUI...");

            return true; // Exit the command execution
        }

        // Check if the command doesn't have exactly one argument
        if (args.length != 1) {
            player.sendMessage("Usage: /home <HOME NAME> or /home list");
            return true; // Exit the command execution
        }

        // Get the home name from the command arguments
        String homeName = args[0];

        // Check if the specified home exists in the homes map
        if (!homes.containsKey(homeName)) {
            player.sendMessage("Home '" + homeName + "' does not exist.");
            return true; // Exit the command execution
        }

        // Get the location of the specified home
        Location homeLocation = homes.get(homeName);

        // Teleport the player to the specified home location
        player.teleport(homeLocation);

        // Send a success message to the player
        player.sendMessage("Teleported to home '" + homeName + "'.");

        return true; // Exit the command execution
    }
}