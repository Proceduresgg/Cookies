package us.rengo.cinventory;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Procedures
 */
public class CInventory implements InventoryHolder {

    @Getter private Map<Integer, Button> buttonMap = new HashMap<>();

    private Inventory inventory;

    public CInventory(int slots, String title) {
        this.inventory = Bukkit.createInventory(this, slots, title);
    }

    public Inventory getInventory() {
        this.inventory.clear();

        for (int slot : this.buttonMap.keySet()) {
            Button button = this.buttonMap.get(slot);

            this.inventory.setItem(slot, button.getPlaceholder());
        }

        return this.inventory;
    }

    public boolean isInInventory(Player player) {
        if (player.getOpenInventory() == null) {
            return false;

        } else return player.getOpenInventory().getTopInventory().getTitle().equals(this.inventory.getTitle());
    }
}
