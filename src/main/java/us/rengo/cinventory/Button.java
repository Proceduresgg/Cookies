package us.rengo.cinventory;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

@Getter
public abstract class Button {

    private ItemStack placeholder;

    public Button(String title, Material material, String lore) {

    }

    public void setPlaceHolder(String title, Material material, String lore) {
        this.placeholder = new ItemStack(material);

        ItemMeta itemMeta = this.placeholder.getItemMeta();
        itemMeta.setDisplayName(title);
        itemMeta.setLore(Collections.singletonList(lore));

        this.placeholder.setItemMeta(itemMeta);
    }

    public abstract void execute();
}
