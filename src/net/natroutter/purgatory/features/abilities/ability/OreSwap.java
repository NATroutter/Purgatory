package net.natroutter.purgatory.features.abilities.ability;


import net.natroutter.natlibs.utilities.Utilities;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.abilities.Ability;
import net.natroutter.purgatory.features.abilities.AbilityItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;


public class OreSwap extends Ability {
    public OreSwap(AbilityItem item, Integer cooldownSeconds, String permission) {
        super(item, cooldownSeconds, permission);
    }

    Utilities util = Purgatory.getUtilities();


    @Override
    public void activeAbility(Player p, Player target) {
        World.Environment env = target.getWorld().getEnvironment();
        for (Block b : util.getBlocks(target.getLocation(), 25)) {
            if (b.getType().name().endsWith("_ORE")) {
                if(env.equals(World.Environment.NETHER)) {
                    b.setType(Material.NETHERRACK);
                } else {
                    b.setType(Material.STONE);
                }
            }
        }
    }
}
