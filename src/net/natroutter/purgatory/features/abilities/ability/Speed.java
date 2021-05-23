package net.natroutter.purgatory.features.abilities.ability;


import net.natroutter.purgatory.features.abilities.Ability;
import net.natroutter.purgatory.features.abilities.AbilityItem;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Speed extends Ability {


    public Speed(AbilityItem item, Integer cooldownSeconds, String permission) {
        super(item, cooldownSeconds, permission);
    }

    @Override
    public void activeAbility(Player p, Player target) {
        target.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 5, 99, false, false));
    }
}
