package net.natroutter.purgatory.handlers;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.bancheck.BanChecker;
import net.natroutter.purgatory.features.Spectator.SpectatorHandler;
import net.natroutter.purgatory.features.shop.ShopGUI;
import net.natroutter.purgatory.features.bancheck.BanData;
import net.natroutter.purgatory.utilities.Config;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.*;

public class NpcHandler implements Listener {

    private final static Config config = Purgatory.getCfg();
    private final static Lang lang = Purgatory.getLang();
    private final static LitebansHandler lbh = Purgatory.getLitebans();
    private final static YamlDatabase database = Purgatory.getYamlDatabase();

    private static HashMap<UUID, Villager> npcs = new HashMap<>();

    int cooldown = 3;
    public static HashMap<UUID, Long> cooldowns = new HashMap<>();

    private static Villager shop;


    public static void spawnAll() {
        Location shopLoc = database.getLocation("General", "ShopLoc");
        if (shopLoc == null) {return;}
        shop = spawn(shopLoc, lang.shop.npc_name, Villager.Type.SAVANNA, Villager.Profession.WEAPONSMITH);
    }

    public static void despawnAll() {
        Location shopLoc = database.getLocation("General", "ShopLoc");
        if (shopLoc == null) {return;}

        for (Map.Entry<UUID, Villager> ents : npcs.entrySet()) {
            despawn(ents.getKey());
        }

        if (shopLoc.getWorld() == null) {return;}
        for (Entity e : shopLoc.getWorld().getNearbyEntities(shopLoc, 3, 3, 3)) {

            if (!e.getType().equals(EntityType.VILLAGER)) { continue; }
            if (!e.isCustomNameVisible()) { continue; }

            e.remove();
        }
    }

    public static void refresh() {
        despawnAll();spawnAll();
    }

    public static boolean isNpc(UUID id) {
        return npcs.containsKey(id);
    }

    public static Villager spawn(Location loc, String name, Villager.Type type, Villager.Profession prof) {
        if (loc == null) {return null;}
        if (loc.getWorld() == null) {return null;}
        Villager ent = (Villager)loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        ent.setVillagerLevel(5);
        ent.setVillagerType(type);
        ent.setProfession(prof);
        ent.setGravity(false);
        ent.setAI(false);
        ent.setSilent(true);
        ent.setCustomNameVisible(true);
        ent.setCustomName(name);
        npcs.put(ent.getUniqueId(), ent);
        return ent;
    }

    public static void despawn(UUID id) {
        Villager ent = npcs.getOrDefault(id, null);
        if (ent != null) {
            if (ent.isValid()) {
                ent.remove();
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e) {
        Entity ent = e.getRightClicked();
        BasePlayer p = BasePlayer.from(e.getPlayer());
        if (AdminHandler.isAdmin(p)) {return;}
        if (ent instanceof Villager) {
            if (isNpc(ent.getUniqueId())) {
                e.setCancelled(true);

                if (cooldowns.containsKey(p.getUniqueId())) {
                    long seconds = ((cooldowns.get(p.getUniqueId())/1000)+cooldown) - (System.currentTimeMillis()/1000);
                    if (seconds > 0) {
                        p.sendMessage(lang.prefix + lang.ShopOpenCooldown);
                        return;
                    }
                }
                cooldowns.put(p.getUniqueId(), System.currentTimeMillis());

                if (ent.getUniqueId().equals(shop.getUniqueId())) {
                    openShop(p);
                }
            }
        }
    }

    private void openShop(BasePlayer p) {
        if (!SpectatorHandler.isSpectator(p)) {
            BanChecker.update(p);
            BanData data = BanChecker.check(p);
            if (data != null) {
                if (data.getUntil() < System.currentTimeMillis()) {
                    lbh.unBan(p.getUniqueId());
                    p.closeInventory();
                    return;
                }
                ShopGUI.show(p);
            } else {
                p.sendMessage(lang.prefix + lang.bannedOnly);
            }
        } else {
            p.sendMessage(lang.prefix + lang.SpectatorNotAllowed);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        BasePlayer p = BasePlayer.from(e.getPlayer());
        if (AdminHandler.isAdmin(p)) {return;}
        if (SpectatorHandler.isSpectator(p)) { return; }

        for (Entity ent : p.getNearbyEntities(5, 5, 5)) {

            if (!ent.getType().equals(EntityType.VILLAGER)) { continue; }
            if (!ent.isCustomNameVisible()) { continue; }
            if (!isNpc(ent.getUniqueId())) { continue; }

            ent.teleport(ent.getLocation().setDirection(p.getLocation().subtract(ent.getLocation()).toVector()));
        }
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Villager) {
            if (npcs.containsKey(e.getEntity().getUniqueId())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Villager) {
            if (npcs.containsKey(e.getEntity().getUniqueId())) {
                e.setCancelled(true);
            }
        }
    }

}
