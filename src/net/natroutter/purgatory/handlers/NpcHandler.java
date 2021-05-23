package net.natroutter.purgatory.handlers;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.event.NPCSpawnEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.trait.LookClose;
import net.natroutter.natlibs.handlers.Database.YamlDatabase;

import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.bancheck.BanChecker;
import net.natroutter.purgatory.features.Spectator.SpectatorHandler;
import net.natroutter.purgatory.features.shop.ShopGUI;
import net.natroutter.purgatory.features.bancheck.BanData;
import net.natroutter.purgatory.utilities.Config;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
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

    int cooldown = 3;
    public static HashMap<UUID, Long> cooldowns = new HashMap<>();

    public static NPC getShopNPC() {
        return CitizensAPI.getNPCRegistry().getById(config.ShopNpcID);
    };

    public static boolean isNpc(UUID id) {
        NPC shop = getShopNPC();
        if (shop != null) {
            if (shop.getEntity().getUniqueId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onInteract(NPCRightClickEvent e) {
        Player p = e.getClicker();
        if (AdminHandler.isAdmin(p)) {
            p.sendMessage(lang.prefix + lang.CantInAdminMode);
            return;
        }
        if (SpectatorHandler.isSpectator(p)) {
            p.sendMessage(lang.prefix + lang.bannedOnly);
            return;
        }

        NPC shop = getShopNPC();
        if (shop != null) {

            if (cooldowns.containsKey(p.getUniqueId())) {
                long seconds = ((cooldowns.get(p.getUniqueId())/1000)+cooldown) - (System.currentTimeMillis()/1000);
                if (seconds > 0) {
                    p.sendMessage(lang.prefix + lang.ShopOpenCooldown);
                    return;
                }
            }
            cooldowns.put(p.getUniqueId(), System.currentTimeMillis());
            openShop(p);

        }
    }

    private void openShop(Player p) {
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

}
