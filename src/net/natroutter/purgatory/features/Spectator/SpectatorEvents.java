package net.natroutter.purgatory.features.Spectator;

import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.BanChecker;
import net.natroutter.purgatory.handlers.NpcHandler;
import net.natroutter.purgatory.handlers.database.PlayerDataHandler;
import net.natroutter.purgatory.handlers.database.tables.PlayerData;
import net.natroutter.purgatory.objects.BanData;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

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

    @EventHandler
    public void onSpectatorPickup(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            BasePlayer p = BasePlayer.from(e.getEntity());
            if (SpectatorHandler.isSpectator(p)) {
                e.setCancelled(true);
                p.sendMessage(lang.prefix + lang.SpectatorNotAllowed);
            }
        }
    }

    @EventHandler
    public void onSpectatorDrop(EntityDropItemEvent e) {
        if (e.getEntity() instanceof Player) {
            BasePlayer p = BasePlayer.from(e.getEntity());
            if (SpectatorHandler.isSpectator(p)) {
                e.setCancelled(true);
                p.sendMessage(lang.prefix + lang.SpectatorNotAllowed);
            }
        }
    }

    @EventHandler
    public void onSpectatorInteract(PlayerInteractEvent e) {
        BasePlayer p = BasePlayer.from(e.getPlayer());
        if (SpectatorHandler.isSpectator(p)) {
            e.setCancelled(true);
            Action act = e.getAction();
            if (act.equals(Action.LEFT_CLICK_AIR) || act.equals(Action.RIGHT_CLICK_AIR)) { return; }
            p.sendMessage(lang.prefix + lang.SpectatorNotAllowed);
        }
    }

    @EventHandler
    public void onSpectatorInteractAtEntity(PlayerInteractAtEntityEvent e) {
        BasePlayer p = BasePlayer.from(e.getPlayer());
        if (SpectatorHandler.isSpectator(p)) {
            e.setCancelled(true);
            if (!NpcHandler.isNpc(e.getRightClicked().getUniqueId())) {
                p.sendMessage(lang.prefix + lang.SpectatorNotAllowed);
            }
        }
    }

    @EventHandler
    public void onSpectatorInteractEntity(PlayerInteractEntityEvent e) {
        BasePlayer p = BasePlayer.from(e.getPlayer());
        if (SpectatorHandler.isSpectator(p)) {
            e.setCancelled(true);
            if (!NpcHandler.isNpc(e.getRightClicked().getUniqueId())) {
                p.sendMessage(lang.prefix + lang.SpectatorNotAllowed);
            }
        }
    }

}
