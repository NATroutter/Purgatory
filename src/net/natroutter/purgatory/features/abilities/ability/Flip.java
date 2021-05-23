package net.natroutter.purgatory.features.abilities.ability;


import net.natroutter.purgatory.features.abilities.Ability;
import net.natroutter.purgatory.features.abilities.AbilityItem;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Flip extends Ability {


    public Flip(AbilityItem item, Integer cooldownSeconds, String permission) {
        super(item, cooldownSeconds, permission);
    }

    @Override
    public void activeAbility(Player p, Player target) {

        Location loc = target.getLocation();
        loc.setYaw(loc.getYaw() + 180);
        target.teleport(loc);

    }
}
