package net.natroutter.purgatory.features.abilities.ability;

import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.abilities.Ability;
import net.natroutter.purgatory.features.abilities.AbilityItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import java.util.HashMap;
import java.util.UUID;

public class Thief extends Ability {
    public Thief(AbilityItem item, Integer cooldownSeconds, String permission) {
        super(item, cooldownSeconds, permission);
    }

    public static HashMap<UUID, Boolean> thiefs = new HashMap<>();

    @Override
    public void activeAbility(BasePlayer p, BasePlayer target) {
        if (thiefs.containsKey(target.getUniqueId())) {
            return;
        }
        thiefs.put(target.getUniqueId(), true);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Purgatory.getInstance(), ()->{
            thiefs.remove(target.getUniqueId());
        }, 20 * 5);
    }
}
