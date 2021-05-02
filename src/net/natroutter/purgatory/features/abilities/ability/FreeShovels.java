package net.natroutter.purgatory.features.abilities.ability;

import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.purgatory.features.abilities.Ability;
import net.natroutter.purgatory.features.abilities.AbilityItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class FreeShovels extends Ability {
    public FreeShovels(AbilityItem item, Integer cooldownSeconds, String permission) {
        super(item, cooldownSeconds, permission);
    }

    public BaseItem shovel() {
        BaseItem item = new BaseItem(Material.WOODEN_SHOVEL);
        item.setDurability((short)58);
        return item;
    }

    @Override
    public void activeAbility(BasePlayer p, BasePlayer target) {
        PlayerInventory inv = target.getInventory();
        for (ItemStack item : inv.getStorageContents()) {
            if (item == null) {
                inv.addItem(shovel());
            } else if (item.getType().equals(Material.AIR)) {
                inv.addItem(shovel());
            }
        }
    }
}
