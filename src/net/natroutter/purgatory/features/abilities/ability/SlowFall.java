package net.natroutter.purgatory.features.abilities.ability;


import net.natroutter.purgatory.features.abilities.Ability;
import net.natroutter.purgatory.features.abilities.AbilityItem;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SlowFall extends Ability {


    public SlowFall(AbilityItem item, Integer cooldownSeconds, String permission) {
        super(item, cooldownSeconds, permission);
    }

    @Override
    public void activeAbility(Player p, Player target) {
        target.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20 * 30, 3, false, false));
        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20 * 60, 3, false, false));
    }
}
