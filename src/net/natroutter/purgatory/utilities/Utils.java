package net.natroutter.purgatory.utilities;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import net.natroutter.purgatory.Purgatory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Utils {

    private final static Lang lang = Purgatory.getLang();

    public static ArrayList<String> playerNameList() {
        ArrayList<String> list = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            list.add(p.getName());
        }
        return list;
    }

    public static String CurrencyFormat(double balance) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.ENGLISH);
        DecimalFormatSymbols sym = formatter.getDecimalFormatSymbols();
        sym.setCurrencySymbol("");
        sym.setDecimalSeparator(',');
        sym.setGroupingSeparator('.');
        formatter.setDecimalFormatSymbols(sym);
        return formatter.format(balance);
    }

    public static Integer TryParseInt(String val) {
        try {
            return Integer.parseInt(val);
        } catch (Exception ignored) {}
        return null;
    }

    public static boolean inRegion(Location target, String region) {
        if (target.getWorld() != null) {
            WorldGuard wg = WorldGuard.getInstance();
            RegionContainer cont = wg.getPlatform().getRegionContainer();
            RegionManager rg = cont.get(BukkitAdapter.adapt(target.getWorld()));
            if (rg != null) {
                BlockVector3 pos = BlockVector3.at(target.getX(), target.getY(), target.getZ());
                ApplicableRegionSet set = rg.getApplicableRegions(pos);
                for (ProtectedRegion r : set.getRegions()) {
                    if (r.getId().equals(region)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static OfflinePlayer getOfflinePlayer(String name) {
        OfflinePlayer target = Bukkit.getOfflinePlayer(name);
        if (target != null) {
            if (target.hasPlayedBefore()) {
                return target;
            }
        }
        return null;
    }

    public static String timeLeft(long timeoutSeconds) {
        int days = (int) (timeoutSeconds / 86400);
        int hours = (int) (timeoutSeconds / 3600) % 24;
        int minutes = (int) (timeoutSeconds / 60) % 60;
        long seconds = timeoutSeconds % 60;

        String hi = lang.time.highlight_color;
        String te = lang.time.text_color;
        String sec_T = lang.time.seconds;
        String min_T = lang.time.minutes;
        String hour_T = lang.time.hours;
        String day_T = lang.time.days;

        String left;
        if (days < 1) {
            if (hours < 1) {
                if (minutes < 1) {
                    left = hi + seconds + " " + te + sec_T;
                } else {
                    left = hi + minutes + " " + te + min_T + hi + " " + seconds + " " + te + sec_T;
                }
            } else {
                left = hi + hours + " " + te + hour_T + hi + " " + minutes + " " + te + min_T + hi + " " + seconds + " " + te + sec_T;
            }
        } else {
            left = hi + days + " " + te + day_T + hi + " " + hours + " " + te + hour_T + hi + " " + minutes + " " + te + min_T + hi + " " + seconds + " " + te + sec_T;
        }
        return left;
    }



}
