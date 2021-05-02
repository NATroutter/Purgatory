package net.natroutter.purgatory.features.Spectator;

import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.bancheck.BanChecker;
import net.natroutter.purgatory.handlers.NpcHandler;
import net.natroutter.purgatory.handlers.database.PlayerDataHandler;
import net.natroutter.purgatory.handlers.database.tables.PlayerData;
import net.natroutter.purgatory.utilities.Items;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
        BasePlayer p = BasePlayer.from(e.getPlayer());
        PlayerData data = PlayerDataHandler.queryForID(p.getUniqueId());
        if (data == null) {return;}

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
            BasePlayer p = BasePlayer.from(e.getWhoClicked());
            if (e.getCurrentItem() == null) { return; }
            if (e.getCursor() == null) { return; }
            BaseItem current = BaseItem.from(e.getCurrentItem());
            BaseItem cursor = BaseItem.from(e.getCursor());
            if (cursor.isSimilar(Items.TrollerHelmet()) || current.isSimilar(Items.TrollerHelmet())) {
                e.setResult(Event.Result.DENY);
                e.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void onSpectatorFoodChange(FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            BasePlayer p = BasePlayer.from(e.getEntity());
            if (SpectatorHandler.isSpectator(p)) {
                e.setCancelled(true);
                e.setFoodLevel(20);
            }
        }
    }

    @EventHandler
    public void onSpectatorDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            BasePlayer p = BasePlayer.from(e.getEntity());
            if (SpectatorHandler.isSpectator(p)) {
                e.setCancelled(true);
                p.sendMessage(lang.prefix + lang.SpectatorNotAllowed);
            }
        }
    }

    @EventHandler
    public void onSpectatorDamageEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            BasePlayer p = BasePlayer.from(e.getDamager());
            if (SpectatorHandler.isSpectator(p)) {
                e.setCancelled(true);
                p.sendMessage(lang.prefix + lang.SpectatorNotAllowed);
            }
        }
    }

    @EventHandler
    public void onSpectatorBreakBlock(BlockBreakEvent e) {
        BasePlayer p = BasePlayer.from(e.getPlayer());
        if (SpectatorHandler.isSpectator(p)) {
            e.setCancelled(true);
            p.sendMessage(lang.prefix + lang.SpectatorNotAllowed);
        }
    }

    @EventHandler
    public void onSpectatorPlaceBlock(BlockPlaceEvent e) {
        BasePlayer p = BasePlayer.from(e.getPlayer());
        if (SpectatorHandler.isSpectator(p)) {
            e.setCancelled(true);
            p.sendMessage(lang.prefix + lang.SpectatorNotAllowed);
        }
    }

    private HashMap<UUID, Long> cool1 = new HashMap<>();
    @EventHandler
    public void onSpectatorPickup(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            BasePlayer p = BasePlayer.from(e.getEntity());
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
        BasePlayer p = BasePlayer.from(e.getPlayer());
        if (SpectatorHandler.isSpectator(p)) {
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void onSpectatorInteractEntity(PlayerInteractEntityEvent e) {
        BasePlayer p = BasePlayer.from(e.getPlayer());
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
