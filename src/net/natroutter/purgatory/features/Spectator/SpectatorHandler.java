package net.natroutter.purgatory.features.Spectator;

import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.abilities.settings.SettingsHandler;
import net.natroutter.purgatory.handlers.AdminHandler;
import net.natroutter.purgatory.handlers.database.PlayerDataHandler;
import net.natroutter.purgatory.handlers.database.tables.PlayerData;
import net.natroutter.purgatory.utilities.Items;
import net.natroutter.purgatory.utilities.Lang;
import net.natroutter.purgatory.utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;


public class SpectatorHandler {

    private static final Lang lang = Purgatory.getLang();

    private static List<Player> hidden = new ArrayList<>();
    private static HashMap<UUID, BaseItem> helmet = new HashMap<>();


    public static BaseItem getHelmet(BasePlayer p) {
        return helmet.getOrDefault(p.getUniqueId(), new BaseItem(Material.AIR));
    }

    public enum HelmetStatus {REMOVED,MODIFIED,FAILED,ALREADY,NOTWEARING}
    public static HelmetStatus setHelmet(BasePlayer p, BaseItem head) {
        if (head == null) {
            if (p.getEquipment() == null || p.getEquipment().getHelmet() == null || p.getEquipment().getHelmet().getType().equals(Material.AIR)) {
                return HelmetStatus.NOTWEARING;
            }

            helmet.remove(p.getUniqueId());
            p.getEquipment().setHelmet(new ItemStack(Material.AIR));
            return HelmetStatus.REMOVED;
        }

        if (p.getEquipment() != null) {
            ItemStack h = p.getEquipment().getHelmet();
            if (h != null) {
                if (h.getItemMeta() != null) {
                    if (h.getItemMeta().getDisplayName().equals(head.getDisplayName())) {
                        return HelmetStatus.ALREADY;
                    }
                }
            }
        }

        helmet.put(p.getUniqueId(), head);
        p.getEquipment().setHelmet(head);
        return HelmetStatus.MODIFIED;
    }

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

    public static void spectatorMode(BasePlayer p, boolean state) {spectatorMode(p, state, true);}
    public static void spectatorMode(BasePlayer p, boolean state, boolean DoAdminCheck) {
        if (DoAdminCheck) {
            if (AdminHandler.isAdmin(p)) {
                p.sendMessage("failed! - " + state);
                return;
            }
        }

        if (state) {
            clean(p);
            p.setGameMode(GameMode.ADVENTURE);

            p.getEquipment().setHelmet(getHelmet(p));

            if (SettingsHandler.isCompleteVisibility(p)) {
                hideToAll(p.getPlayer());
            }
            if (SettingsHandler.isInvisibility(p)) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
            }
            p.setAllowFlight(true);
            p.setFlying(true);
            p.getInventory().setItem(0, Items.abilityBox());
            p.getInventory().setItem(1, Items.TrackerCompass());
            p.getInventory().setItem(1, Items.Settings());
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
        for (Player ps : Bukkit.getOnlinePlayers()) {
            for (Player target : hidden) {
                if (AdminHandler.isAdmin(BasePlayer.from(ps))) { continue; }
                ps.hidePlayer(Purgatory.getInstance(), target);
            }
        }
    }

    public static void showToAll(Player target) {
        hidden.remove(target);
        for (Player allPlayers : Bukkit.getOnlinePlayers()) {
            allPlayers.showPlayer(Purgatory.getInstance(), target);
        }
    }

    public static void hideToAll(Player target) {
        if (AdminHandler.isAdmin(BasePlayer.from(target))) { return; }
        hidden.add(target);
        for (Player allPlayers : Bukkit.getOnlinePlayers()) {
            allPlayers.hidePlayer(Purgatory.getInstance(), target);
        }
    }


}
