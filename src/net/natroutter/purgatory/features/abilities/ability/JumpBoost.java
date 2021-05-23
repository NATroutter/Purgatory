package net.natroutter.purgatory.features.abilities.ability;


import net.natroutter.purgatory.features.abilities.Ability;
import net.natroutter.purgatory.features.abilities.AbilityItem;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JumpBoost extends Ability {


    public JumpBoost(AbilityItem item, Integer cooldownSeconds, String permission) {
        super(item, cooldownSeconds, permission);
    }

    @Override
    public void activeAbility(Player p, Player target) {
        target.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 10, 10, false, false));
    }
}
