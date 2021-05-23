package net.natroutter.purgatory.features.abilities.ability;


import net.natroutter.purgatory.features.abilities.Ability;
import net.natroutter.purgatory.features.abilities.AbilityItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Scrambler extends Ability {
    public Scrambler(AbilityItem item, Integer cooldownSeconds, String permission) {
        super(item, cooldownSeconds, permission);
    }

    @Override
    public void activeAbility(Player p, Player target) {
        Scramble(target);
    }

    public static void Scramble(Player target) {
        PlayerInventory inv = target.getInventory();
        List<ItemStack> items = new ArrayList<>();
        for (ItemStack stack : inv.getStorageContents()) {
            ItemStack clone = (stack != null) ? stack.clone() : null;
            items.add(clone);
        }
        Collections.shuffle(items);
        for (int i = 0; i < items.size() ; i++) {
            ItemStack iItem = inv.getItem(i);
            ItemStack randomItem = items.get(i);
            if (iItem != null) {
                iItem.setAmount(0);
            }
            target.getInventory().setItem(i, randomItem);
        }
    }
}
