package net.natroutter.purgatory.features.Spectator;

import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.BanChecker;
import net.natroutter.purgatory.handlers.database.PlayerDataHandler;
import net.natroutter.purgatory.handlers.database.tables.PlayerData;
import net.natroutter.purgatory.objects.BanData;
import net.natroutter.purgatory.utilities.Lang;
import net.natroutter.purgatory.utilities.Utils;
import org.bukkit.GameMode;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class SpectatorHandler {

    private static final Lang lang = Purgatory.getLang();

    public static boolean isSpectator(BasePlayer p) {
        PlayerData data = PlayerDataHandler.queryForID(p.getUniqueId());
        if (data == null) {return false;}
        return data.IsSpectator();
    }

    public static void spectatorMode(BasePlayer p, boolean state) {
        p.setFoodLevel(20);
        p.setHealth(20);
        if (state) {
            p.setGameMode(GameMode.ADVENTURE);
            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, false, false));
            p.setAllowFlight(true);
            p.setFlying(true);
        } else {
            p.setGameMode(GameMode.SURVIVAL);
            p.removePotionEffect(PotionEffectType.INVISIBILITY);
            p.setAllowFlight(false);
            p.setFlying(false);
        }
    }


}
