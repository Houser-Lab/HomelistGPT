package xyz.housers.homelistgpt;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import xyz.housers.homelistgpt.commands.SetHomeCommand;
import xyz.housers.homelistgpt.commands.HomeCommand;
import xyz.housers.homelistgpt.HomeListGUI;

import java.util.HashMap;
import java.util.Map;

public class HomelistGPT extends JavaPlugin {
    private final Map<String, Location> homes = new HashMap<>();
    private final int maxHomes = 5;
    private FileConfiguration config;

    @Override
    public void onEnable() {
        config = getConfig();
        loadHomes();
// FIX THIS cannot cast listener to 'this'
        getServer().getPluginManager().registerEvents((Listener) this, this);

        getCommand("sethome").setExecutor(new SetHomeCommand(this, homes, maxHomes));
        getCommand("home").setExecutor(new HomeCommand(homes));
    }

    @Override
    public void onDisable() {
        saveHomes();
    }

    public void loadHomes() {
        if (config.contains("homes")) {
            for (String homeName : config.getConfigurationSection("homes").getKeys(false)) {
                Location location = (Location) config.get("homes." + homeName);
                homes.put(homeName, location);
            }
        }
    }

    public void saveHomes() {
        config.set("homes", null);
        for (Map.Entry<String, Location> entry : homes.entrySet()) {
            config.set("homes." + entry.getKey(), entry.getValue());
        }
        saveConfig();
    }
}
