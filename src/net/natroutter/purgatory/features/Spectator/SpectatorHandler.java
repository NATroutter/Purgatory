package net.natroutter.purgatory.features.Spectator;

import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.handlers.database.PlayerDataHandler;
import net.natroutter.purgatory.handlers.database.tables.PlayerData;
import net.natroutter.purgatory.utilities.Items;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;


public class SpectatorHandler {

    private static final Lang lang = Purgatory.getLang();

    private static List<Player> hidden = new ArrayList<>();
    public static boolean isHidden(BasePlayer p) {
        return hidden.contains(p.getPlayer());
    }

    public static boolean isSpectator(BasePlayer p) {
        PlayerData data = PlayerDataHandler.queryForID(p.getUniqueId());
        if (data == null) {return false;}
        return data.IsSpectator();
    }

    public static void clean(BasePlayer p) {
        p.setFoodLevel(20);
        p.setHealth(20);
        p.getInventory().clear();
        p.getActivePotionEffects().forEach(e->{p.removePotionEffect(e.getType());});
    }

    public static void spectatorMode(BasePlayer p, boolean state) {
        if (state) {
            clean(p);
            p.setGameMode(GameMode.ADVENTURE);
            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
            p.getEquipment().setHelmet(Items.TrollerHelmet());
            hideToAll(p.getPlayer());
            p.setAllowFlight(true);
            p.setFlying(true);
            p.getInventory().setItem(0, Items.abilityBox());
            p.getInventory().setItem(1, Items.TrackerCompass());
        } else {
            p.setGameMode(GameMode.SURVIVAL);
            showToAll(p.getPlayer());
            p.setAllowFlight(false);
            p.setFlying(false);
        }
        p.updateInventory();
        updateHiddenPlayers();
    }

    public static void updateHiddenPlayers() {
        Bukkit.getOnlinePlayers().forEach(ps -> {
            hidden.forEach(hidden -> ps.hidePlayer(Purgatory.getInstance(), hidden));
        });
    }

    private static void showToAll(Player target) {
        hidden.remove(target);
        Bukkit.getOnlinePlayers().forEach(allPlayers -> allPlayers.showPlayer(Purgatory.getInstance(), target));
    }

    private static void hideToAll(Player target) {
        hidden.add(target);
        Bukkit.getOnlinePlayers().forEach(allPlayers -> allPlayers.hidePlayer(Purgatory.getInstance(), target));
    }


}
