package net.natroutter.purgatory.features.abilities.settings;

import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.purgatory.features.Spectator.SpectatorHandler;
import org.bukkit.GameMode;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class SettingsHandler {

    private static HashMap<UUID, Boolean> completeVisibility = new HashMap<>();
    private static HashMap<UUID, Boolean> visibility = new HashMap<>();

    public static boolean isCompleteVisibility(BasePlayer p) {
        return completeVisibility.getOrDefault(p.getUniqueId(), true);
    }
    public static boolean isInvisibility(BasePlayer p) {
        return visibility.getOrDefault(p.getUniqueId(), true);
    }

    public static void toggleCompleteVisibility(BasePlayer p) {
        if (completeVisibility.getOrDefault(p.getUniqueId(), true)) {
            completeVisibility.put(p.getUniqueId(), false);
            SpectatorHandler.showToAll(p.getPlayer());
        } else {
            completeVisibility.put(p.getUniqueId(), true);
            SpectatorHandler.hideToAll(p.getPlayer());
        }
        SpectatorHandler.updateHiddenPlayers();
    }

    public static void toggleVisibility(BasePlayer p) {
        if (visibility.getOrDefault(p.getUniqueId(), true)) {
            visibility.put(p.getUniqueId(), false);
            p.getActivePotionEffects().forEach(e->{p.removePotionEffect(e.getType());});
        } else {
            visibility.put(p.getUniqueId(), true);
            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
        }
    }




}
