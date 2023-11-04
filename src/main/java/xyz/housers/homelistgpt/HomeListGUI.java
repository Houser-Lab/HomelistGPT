package xyz.housers.homelistgpt;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class HomeListGUI {
    public static void openHomeList(Player player, Map<String, Location> homes) {
        Inventory gui = Bukkit.createInventory(null, 27, "Home List");

        for (Map.Entry<String, Location> entry : homes.entrySet()) {
            String homeName = entry.getKey();
            Location homeLocation = entry.getValue();

            ItemStack homeItem = new ItemStack(Material.COMPASS);
            ItemMeta itemMeta = homeItem.getItemMeta();
            itemMeta.setDisplayName(homeName);
            itemMeta.setLore(List.of("X: " + homeLocation.getBlockX(), "Y: " + homeLocation.getBlockY(), "Z: " + homeLocation.getBlockZ()));
            homeItem.setItemMeta(itemMeta);

            gui.addItem(homeItem);
        }

        ItemStack customizeItem = new ItemStack(Material.ANVIL);
        ItemMeta customizeMeta = customizeItem.getItemMeta();
        customizeMeta.setDisplayName("Customize");
        customizeItem.setItemMeta(customizeMeta);
        gui.setItem(26, customizeItem);

        player.openInventory(gui);
    }
}