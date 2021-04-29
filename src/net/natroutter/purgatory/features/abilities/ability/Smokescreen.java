package net.natroutter.purgatory.features.abilities.ability;

import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.objects.ParticleSettings;
import net.natroutter.natlibs.utilities.Utilities;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.abilities.Ability;
import net.natroutter.purgatory.features.abilities.AbilityItem;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;

import java.util.HashMap;
import java.util.UUID;

public class Smokescreen extends Ability {

    private static final Lang lang = Purgatory.getLang();
    private static final Utilities util = Purgatory.getUtilities();

    public Smokescreen(AbilityItem item, Integer cooldownSeconds, String permission) { super(item, cooldownSeconds, permission); }

    private ParticleSettings getSettings(Location loc) {
        return new ParticleSettings(Particle.EXPLOSION_HUGE, loc, 100, 2, 2, 2, 0.1);
    }

    private HashMap<UUID, Integer> tasks = new HashMap<>();
    private HashMap<UUID, Integer> ticks = new HashMap<>();
    private HashMap<UUID, Integer> secs = new HashMap<>();


    @Override
    public void activeAbility(BasePlayer p, BasePlayer target) {
       tasks.put(p.getUniqueId(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Purgatory.getInstance(), ()-> {

            if (ticks.getOrDefault(p.getUniqueId(), 0) >= 20) {
                ticks.put(p.getUniqueId(), 0);
                secs.merge(p.getUniqueId(), 1, Integer::sum);
            }
            if (secs.getOrDefault(p.getUniqueId(), 0) >= 5) {
                Bukkit.getScheduler().cancelTask(tasks.get(p.getUniqueId()));
                secs.remove(p.getUniqueId());
                ticks.remove(p.getUniqueId());
                tasks.remove(p.getUniqueId());
            }

            util.spawnParticleInRadius(getSettings(target.getLocation()), 20);
            ticks.merge(p.getUniqueId(), 10, Integer::sum);

        }, 0, 10));
    }
}
