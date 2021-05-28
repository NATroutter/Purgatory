package net.natroutter.purgatory.features.abilities.ability;

import net.natroutter.purgatory.features.abilities.Ability;
import net.natroutter.purgatory.features.abilities.AbilityItem;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class ZombieInvasion extends Ability {


    public ZombieInvasion(AbilityItem item, Integer cooldownSeconds, String permission) {
        super(item, cooldownSeconds, permission);
    }

    @Override
    public void activeAbility(Player p, Player target) {
        for (int i = 0; i < 5; i++) {
            target.getWorld().spawnEntity(target.getLocation(), EntityType.ZOMBIE);
        }
    }
}
