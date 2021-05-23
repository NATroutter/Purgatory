package net.natroutter.purgatory.features.abilities.ability;


import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.abilities.Ability;
import net.natroutter.purgatory.features.abilities.AbilityItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SuperScrambler extends Ability {


    public SuperScrambler(AbilityItem item, Integer cooldownSeconds, String permission) {
        super(item, cooldownSeconds, permission);
    }

    private HashMap<UUID, Integer> tasks = new HashMap<>();
    private HashMap<UUID, Integer> ticks = new HashMap<>();
    private HashMap<UUID, Integer> secs = new HashMap<>();

    @Override
    public void activeAbility(Player p, Player target) {
        if (tasks.containsKey(p.getUniqueId())) { return; }
        tasks.put(p.getUniqueId(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Purgatory.getInstance(), ()-> {

            if (ticks.getOrDefault(p.getUniqueId(), 0) >= 20) {
                ticks.put(p.getUniqueId(), 0);
                secs.merge(p.getUniqueId(), 1, Integer::sum);
            }
            if (secs.getOrDefault(p.getUniqueId(), 0) >= 5) {
                Integer id = tasks.get(p.getUniqueId());
                if (id != null) { Bukkit.getScheduler().cancelTask(id); }
                secs.remove(p.getUniqueId());
                ticks.remove(p.getUniqueId());
                tasks.remove(p.getUniqueId());
            }

            Scrambler.Scramble(target);
            ticks.merge(p.getUniqueId(), 10, Integer::sum);

        }, 0, 10));
    }
}
