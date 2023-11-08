package xyz.housers.homelistgpt.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import xyz.housers.homelistgpt.HomelistGPT;

import java.util.Map;

import static xyz.housers.homelistgpt.HomeListGUI.changeBlock;

public class ClickHandler implements Listener {

    private boolean customizeMode = false;
    Map<String, Location> homes = xyz.housers.homelistgpt.HomelistGPT.homes;

    public ClickHandler(HomelistGPT plugin) {

        Bukkit.getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (event.getClickedInventory() != null && event.getClickedInventory().getHolder() == null) {
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() == Material.ANVIL) {
                // Handle the "Customize" button click
                event.setCancelled(true);

                if(customizeMode){
                    changeBlock(event.getClickedInventory(), Material.RED_STAINED_GLASS_PANE, Material.GREEN_STAINED_GLASS_PANE);
                    player.sendMessage("Exiting Customization");
                    customizeMode = false;
                } else {
                    changeBlock(event.getClickedInventory(), Material.GREEN_STAINED_GLASS_PANE, Material.RED_STAINED_GLASS_PANE);
                    player.sendMessage("Enter Customization");
                    customizeMode = true;
                }


                 // You should implement the customization logic here
            } else if (clickedItem != null && clickedItem.getType() == Material.COMPASS) {

                String homeName = clickedItem.getItemMeta().getDisplayName();

                Location homeLocation = homes.get(homeName);

                event.setCancelled(true);

                player.teleport(homeLocation);

                player.sendMessage("Teleporting to " + homeName);
            } else if (clickedItem != null){
                event.setCancelled(true);
            }
        }
    }
}