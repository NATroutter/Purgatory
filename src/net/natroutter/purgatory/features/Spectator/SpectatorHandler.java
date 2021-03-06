package net.natroutter.purgatory.features.Spectator;

import net.natroutter.natlibs.objects.BaseItem;
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


    public static BaseItem getHelmet(Player p) {
        return helmet.getOrDefault(p.getUniqueId(), new BaseItem(Material.AIR));
    }

    public enum HelmetStatus {REMOVED,MODIFIED,FAILED,ALREADY,NOTWEARING}
    public static HelmetStatus setHelmet(Player p, BaseItem head) {
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

    public static boolean isHidden(Player p) {
        return hidden.contains(p);
    }

    public static boolean isSpectator(Player p) {
        PlayerData data = PlayerDataHandler.queryForID(p.getUniqueId());
        if (data == null) {return false;}
        return data.IsSpectator();
    }

    public static void clean(Player p) {
        p.setFoodLevel(20);
        p.setHealth(20);
        p.getInventory().clear();
        p.getActivePotionEffects().forEach(e->{p.removePotionEffect(e.getType());});
    }

    public static void safeClean(Player p){
        p.getActivePotionEffects().forEach(e->{p.removePotionEffect(e.getType());});
        for (ItemStack item : p.getInventory().getContents()) {
            if (item == null) {continue;}
            if (item.getType().equals(Material.AIR)) {continue;}
            if (item.getItemMeta() == null) {continue;}
            String name = item.getItemMeta().getDisplayName();

            if (name.equalsIgnoreCase(Items.abilityBox().getDisplayName())) {
                item.setAmount(0);
            } else if (name.equalsIgnoreCase(Items.Settings().getDisplayName())) {
                item.setAmount(0);
            } else if (name.equalsIgnoreCase(Items.TrackerCompass().getDisplayName())) {
                item.setAmount(0);
            }
        }
    }

    public static void spectatorMode(Player p, boolean state) {
        PlayerData data = PlayerDataHandler.queryForID(p.getUniqueId());
        if (data != null) {
            data.setSpectator(state);

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
                p.getInventory().setItem(1, Items.Settings());
                p.getInventory().setItem(2, Items.TrackerCompass());
            } else {
                p.setGameMode(GameMode.SURVIVAL);
                showToAll(p.getPlayer());
                p.setAllowFlight(false);
                p.setFlying(false);
            }
            p.updateInventory();
            updateHiddenPlayers();
            PlayerDataHandler.updateForID(data);

        }
    }

    public static void updateHiddenPlayers() {
        for (Player ps : Bukkit.getOnlinePlayers()) {
            for (Player target : hidden) {
                if (AdminHandler.isAdmin(ps)) { continue; }
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
        if (AdminHandler.isAdmin(target)) { return; }
        hidden.add(target);
        for (Player allPlayers : Bukkit.getOnlinePlayers()) {
            allPlayers.hidePlayer(Purgatory.getInstance(), target);
        }
    }


}
