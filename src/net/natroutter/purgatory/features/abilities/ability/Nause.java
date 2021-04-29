package net.natroutter.purgatory.features.abilities.ability;

import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.purgatory.features.abilities.Ability;
import net.natroutter.purgatory.features.abilities.AbilityItem;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Nause extends Ability {


    public Nause(AbilityItem item, Integer cooldownSeconds, String permission) { super(item, cooldownSeconds, permission); }

    @Override
    public void activeAbility(BasePlayer p, BasePlayer target) {
        target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20 * 10, 10, false, false));
    }
}
