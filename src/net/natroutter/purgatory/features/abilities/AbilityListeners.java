package net.natroutter.purgatory.features.abilities;

import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.purgatory.features.abilities.ability.Thief;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class AbilityListeners implements Listener {


    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            BasePlayer p = BasePlayer.from(e.getEntity());
            if (Thief.thiefs.getOrDefault(p.getUniqueId(), false)) {
                e.setCancelled(true);
                e.getItem().remove();
            }
        }
    }


}
