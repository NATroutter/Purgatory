package net.natroutter.purgatory.features.Spectator;

import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.abilities.AbilityGUI;
import net.natroutter.purgatory.features.abilities.settings.SettingsGUI;
import net.natroutter.purgatory.features.bancheck.BanChecker;
import net.natroutter.purgatory.handlers.AdminHandler;
import net.natroutter.purgatory.handlers.NpcHandler;
import net.natroutter.purgatory.handlers.database.PlayerDataHandler;
import net.natroutter.purgatory.handlers.database.tables.PlayerData;
import net.natroutter.purgatory.utilities.Items;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

public class SpectatorEvents implements Listener {

    private static final Lang lang = Purgatory.getLang();

    @EventHandler
    public void onSpectatorJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        PlayerData data = PlayerDataHandler.queryForID(p.getUniqueId());
        if (data == null) {return;}
        if (data.getAdminMode()) {return;}

        if (!BanChecker.isBanned(p)) {
            SpectatorHandler.spectatorMode(p, data.IsSpectator());
        } else {
            SpectatorHandler.spectatorMode(p, false);
            data.setSpectator(false);
            data.setSpectateCooldown(0);
            PlayerDataHandler.updateForID(data);
        }
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        SpectatorHandler.updateHiddenPlayers();
    }

    @EventHandler
    public void onSpectatorInvClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player)e.getWhoClicked();
            if (AdminHandler.isAdmin(p)) {return;}
            if (SpectatorHandler.isSpectator(p)) {
                //if clicked slot is helmet slot
                if (e.getSlot() == 39) {
                    e.setResult(Event.Result.DENY);
                    e.setCancelled(true);
                }
            }
        }
    }


    @EventHandler
    public void onSpectatorFoodChange(FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player)e.getEntity();
            if (AdminHandler.isAdmin(p)) {return;}
            if (SpectatorHandler.isSpectator(p)) {
                e.setCancelled(true);
                e.setFoodLevel(20);
            }
        }
    }

    @EventHandler
    public void onSpectatorDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player)e.getEntity();
            if (AdminHandler.isAdmin(p)) {return;}
            if (SpectatorHandler.isSpectator(p)) {
                e.setCancelled(true);
                p.sendMessage(lang.prefix + lang.SpectatorNotAllowed);
            }
        }
    }

    @EventHandler
    public void onSpectatorDamageEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player)e.getDamager();
            if (AdminHandler.isAdmin(p)) {return;}
            if (SpectatorHandler.isSpectator(p)) {
                e.setCancelled(true);
                p.sendMessage(lang.prefix + lang.SpectatorNotAllowed);
            }
        }
    }

    @EventHandler
    public void onSpectatorBreakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (AdminHandler.isAdmin(p)) {return;}
        if (SpectatorHandler.isSpectator(p)) {
            e.setCancelled(true);
            p.sendMessage(lang.prefix + lang.SpectatorNotAllowed);
        }
    }

    @EventHandler
    public void onSpectatorPlaceBlock(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (AdminHandler.isAdmin(p)) {return;}
        if (SpectatorHandler.isSpectator(p)) {
            e.setCancelled(true);
            p.sendMessage(lang.prefix + lang.SpectatorNotAllowed);
        }
    }

    private HashMap<UUID, Long> cool1 = new HashMap<>();
    @EventHandler
    public void onSpectatorPickup(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player)e.getEntity();
            if (AdminHandler.isAdmin(p)) {return;}
            if (SpectatorHandler.isSpectator(p)) {
                e.setCancelled(true);

                long cl = ((cool1.getOrDefault(p.getUniqueId(), 0L) /1000)+1) - (System.currentTimeMillis()/1000);
                if (cl > 0) { return; }
                cool1.put(p.getUniqueId(), System.currentTimeMillis());

                p.sendMessage(lang.prefix + lang.SpectatorNotAllowed);
            }
        }
    }


    @EventHandler
    public void onSpectatorInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (AdminHandler.isAdmin(p)) {return;}
        if (SpectatorHandler.isSpectator(p)) {
            e.setCancelled(true);

            if (!e.hasItem()) {return;}
            Action act = e.getAction();
            BaseItem item = BaseItem.from(e.getItem());

            if (act.equals(Action.RIGHT_CLICK_BLOCK) || act.equals(Action.RIGHT_CLICK_AIR)) {
                if (item.isSimilar(Items.abilityBox())) {
                    e.setCancelled(true);
                    AbilityGUI.show(p);
                } else if (item.isSimilar(Items.Settings())) {
                    e.setCancelled(true);
                    SettingsGUI.show(p);
                }
            }

        }
    }


    @EventHandler
    public void onSpectatorInteractEntity(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        if (AdminHandler.isAdmin(p)) {return;}
        if (SpectatorHandler.isSpectator(p)) {
            e.setCancelled(true);
            if (!NpcHandler.isNpc(e.getRightClicked().getUniqueId())) {
                if (e.getRightClicked() instanceof Player) {
                    return;
                }
                p.sendMessage(lang.prefix + lang.SpectatorNotAllowed);
            }
        }
    }

}
