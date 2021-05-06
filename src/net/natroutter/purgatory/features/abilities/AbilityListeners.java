package net.natroutter.purgatory.features.abilities;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.Spectator.SpectatorHandler;
import net.natroutter.purgatory.features.abilities.ability.Thief;
import net.natroutter.purgatory.features.abilities.settings.SettingsGUI;
import net.natroutter.purgatory.handlers.AdminHandler;
import net.natroutter.purgatory.handlers.Hooks;
import net.natroutter.purgatory.handlers.NpcHandler;
import net.natroutter.purgatory.utilities.Config;
import net.natroutter.purgatory.utilities.Items;
import net.natroutter.purgatory.utilities.Lang;
import net.natroutter.purgatory.utilities.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class AbilityListeners implements Listener {

    private static final Config config = Purgatory.getCfg();
    private static final Lang lang = Purgatory.getLang();
    private static final Hooks hooks = Purgatory.getHooks();

    private HashMap<String, Long> cooldowns = new HashMap<>();
    private HashMap<UUID, Long> interactCooldowns = new HashMap<>();
    private HashMap<UUID, Long> safeCooldowns = new HashMap<>();

    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            BasePlayer p = BasePlayer.from(e.getEntity());
            if (Thief.thiefs.getOrDefault(p.getUniqueId(), false)) {
                e.setCancelled(true);
                e.getItem().remove();
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDrop(PlayerDropItemEvent e) {
        BasePlayer p = BasePlayer.from(e.getPlayer());
        if (AdminHandler.isAdmin(p)) {return;}
        if (SpectatorHandler.isSpectator(p)) {
            for (Ability ab : AbilityHandler.abilities) {
                if (ab.getAbilityItem().getItem().isSimilar(e.getItemDrop().getItemStack())) {
                    e.getItemDrop().remove();
                    return;
                }
            }

            e.setCancelled(true);
            p.sendMessage(lang.prefix + lang.SpectatorNotAllowed);
        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractAtEntityEvent e) {
        BasePlayer p = BasePlayer.from(e.getPlayer());
        if (AdminHandler.isAdmin(p)) {return;}

        if (SpectatorHandler.isSpectator(p)) {
            e.setCancelled(true);

            long cl = ((interactCooldowns.getOrDefault(p.getUniqueId(), 0L) /1000)+1) - (System.currentTimeMillis()/1000);
            if (cl > 0) { return; }
            interactCooldowns.put(p.getUniqueId(), System.currentTimeMillis());
            if (!(e.getRightClicked() instanceof Player)) {
                if (!NpcHandler.isNpc(e.getRightClicked().getUniqueId())) {
                    p.sendMessage(lang.prefix + lang.SpectatorNotAllowed);
                }
                return;
            }
            BasePlayer target = BasePlayer.from(e.getRightClicked());

            ItemStack hand = p.getInventory().getItemInMainHand();
            if (hand == null || hand.getType().equals(Material.AIR)) {hand = p.getInventory().getItemInOffHand();}
            if (hand == null || hand.getType().equals(Material.AIR)) { return; }

            for (Ability ab : AbilityHandler.abilities) {

                if (ab.getAbilityItem().getItem().isSimilar(hand)) {

                    if (hooks.worldguard.isHooked()) {
                        Location tl = target.getLocation();
                        if (tl.getWorld() != null) {
                            WorldGuard wg = WorldGuard.getInstance();
                            RegionContainer cont = wg.getPlatform().getRegionContainer();
                            RegionManager rg = cont.get(BukkitAdapter.adapt(tl.getWorld()));
                            if (rg != null) {
                                BlockVector3 pos = BlockVector3.at(tl.getX(), tl.getY(), tl.getZ());
                                ApplicableRegionSet set = rg.getApplicableRegions(pos);
                                for (ProtectedRegion r : set.getRegions()) {
                                    if (r.getId().equals(config.SpawnRegionName)) {
                                        p.sendMessage(lang.prefix + lang.AbilityProtectedArea);
                                        return;
                                    }
                                }
                            }
                        }
                    }

                    if (SpectatorHandler.isSpectator(target)) {
                        p.sendMessage(lang.prefix + lang.NoAbilityOnSpectator);
                        return;
                    }

                    if (!p.hasPermission(ab.getPermission())) {
                        p.sendMessage(lang.prefix + lang.NoPermToAbility);
                        return;
                    }

                    String key = p.getUniqueId() + ":" + ab.getAbilityItem().getId();
                    long cooldownLeft = ((cooldowns.getOrDefault(key, 0L) /1000)+ab.getCooldownSeconds()) - (System.currentTimeMillis()/1000);
                    if (cooldownLeft > 0) {
                        StringHandler message = new StringHandler(lang.AbilityIsOnCooldown);
                        message.setPrefix(lang.prefix);
                        message.replaceAll("{cooldown}", Utils.timeLeft(cooldownLeft));
                        message.send(p);
                        return;
                    }
                    cooldowns.put(key, System.currentTimeMillis());

                    long safeCooldownLeft = ((safeCooldowns.getOrDefault(p.getUniqueId(), 0L) /1000) + config.SafeTimeSeconds) - (System.currentTimeMillis()/1000);
                    if (safeCooldownLeft > 0) {
                        StringHandler message = new StringHandler(lang.AbilitySafeCooldown);
                        message.setPrefix(lang.prefix);
                        message.replaceAll("{player}", target.getName());
                        message.replaceAll("{cooldown}", Utils.timeLeft(safeCooldownLeft));
                        message.send(p);
                        return;
                    }
                    safeCooldowns.put(p.getUniqueId(), System.currentTimeMillis());

                    StringHandler message = new StringHandler(lang.AbilityActivated).setPrefix(lang.prefix);
                    message.replaceAll("{ability}", ChatColor.stripColor(ab.getAbilityItem().getName()));
                    message.replaceAll("{player}", target.getName());
                    message.send(p);

                    ab.activeAbility(p, target);
                    break;
                }
            }
        }
    }


}
