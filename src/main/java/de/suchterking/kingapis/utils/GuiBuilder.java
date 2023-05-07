package de.suchterking.kingapis.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GuiBuilder {

    private Inventory inv;

    public GuiBuilder(int size, String title) {
        this.inv = Bukkit.createInventory(null, size, title);
    }

    public GuiBuilder setItem(int slot, ItemStack item) {
        inv.setItem(slot, item);
        return this;
    }

    public GuiBuilder openInc(Player p) {
        p.openInventory(inv);
        return this;
    }
}
