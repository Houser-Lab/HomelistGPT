package xyz.housers.homelistgpt.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.housers.homelistgpt.HomelistGPT;

import java.util.Map;

public class SetHomeCommand implements CommandExecutor {
    private final Map<String, Location> homes;
    private final int maxHomes;
    private HomelistGPT plugin;

    public SetHomeCommand(HomelistGPT plugin, Map<String, Location> homes, int maxHomes) {
        this.plugin = plugin;
        this.homes = homes;
        this.maxHomes = maxHomes;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        // Cast the sender to a player
        Player player = (Player) sender;

        // Check if the command has the correct number of arguments
        if (args.length != 1) {
            player.sendMessage("Usage: /sethome <HOME NAME>");
            return true;
        }

        // Get the home name from the command arguments
        String homeName = args[0];

        // Check if the player has reached the maximum number of homes
        if (homes.size() >= maxHomes) {
            player.sendMessage("You've reached the maximum number of homes (" + maxHomes + ").");
            return true;
        }

        // Get the player's current location as the home location
        Location playerLocation = player.getLocation();

        // Store the home location in the homes map with the specified home name
        homes.put(homeName, playerLocation);

        // Send a success message to the player
        player.sendMessage("Home '" + homeName + "' set successfully.");

        // Save the homes to the configuration file (not shown in this code, should be handled in the plugin)

        return true;
    }
}