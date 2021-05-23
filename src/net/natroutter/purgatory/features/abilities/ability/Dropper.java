package net.natroutter.purgatory.features.abilities.ability;

import net.natroutter.natlibs.utilities.Utilities;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.abilities.Ability;
import net.natroutter.purgatory.features.abilities.AbilityItem;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.Random;

public class Dropper extends Ability {
    public Dropper(AbilityItem item, Integer cooldownSeconds, String permission) {
        super(item, cooldownSeconds, permission);
    }

    Utilities util = Purgatory.getUtilities();

    @Override
    public void activeAbility(Player p, Player target) {
        PlayerInventory inv = target.getInventory();
        ArrayList<Integer> slots = new ArrayList<>();

        for (int i = 0; i < 8 ; i++) {
            if (inv.getItem(i) == null) { continue; }
            if (inv.getItem(i).getType() == Material.AIR) { continue; }
            slots.add(i);
        }
        if (slots.size() > 0) {
            int RandSlot = new Random().nextInt(slots.size());
            ItemStack item = inv.getItem(slots.get(RandSlot));
            ItemStack clone = item.clone();
            item.setAmount(0);
            target.updateInventory();
            Item dropItem = target.getWorld().dropItemNaturally(target.getLocation(), clone);
            dropItem.setPickupDelay(20 * 5);
        }
    }
}
