package net.natroutter.purgatory.features.abilities.ability;

import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.purgatory.features.abilities.Ability;
import net.natroutter.purgatory.features.abilities.AbilityItem;
import org.bukkit.Material;

public class Web extends Ability {
    public Web(AbilityItem item, Integer cooldownSeconds, String permission) {
        super(item, cooldownSeconds, permission);
    }

    @Override
    public void activeAbility(BasePlayer p, BasePlayer target) {
        target.getLocation().getBlock().setType(Material.COBWEB);
    }
}
