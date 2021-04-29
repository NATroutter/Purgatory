package net.natroutter.purgatory.handlers.abilities;

import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.Spectator.SpectatorHandler;
import net.natroutter.purgatory.handlers.NpcHandler;
import net.natroutter.purgatory.handlers.abilities.ability.Smokescreen;
import net.natroutter.purgatory.utilities.Items;
import net.natroutter.purgatory.utilities.Lang;
import net.natroutter.purgatory.utilities.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class AbilityHandler implements Listener {

    private static final Lang lang = Purgatory.getLang();
    private static final Lang.abilities ab = lang.abilities;

    private HashMap<String, Long> cooldowns = new HashMap<>();
    private HashMap<UUID, Long> interactCooldowns = new HashMap<>();
    public static ArrayList<Ability> abilities = new ArrayList<>();

    private static AbilityItem SmokeScreen_item = new AbilityItem("SmokeScreen", Material.FLINT_AND_STEEL, ab.smkokescreen_name, ab.smkokescreen_lore);

    public static void RegisterAbilities() {
        abilities.add(new Smokescreen(SmokeScreen_item, 60));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDrop(PlayerDropItemEvent e) {
        BasePlayer p = BasePlayer.from(e.getPlayer());
        if (SpectatorHandler.isSpectator(p)) {
            for (Ability ab : abilities) {
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
    public void onInteract(PlayerInteractEvent e) {
        if (!e.hasItem()) {return;}
        BasePlayer p = BasePlayer.from(e.getPlayer());
        Action act = e.getAction();
        BaseItem item = BaseItem.from(e.getItem());

        if (act.equals(Action.RIGHT_CLICK_BLOCK) || act.equals(Action.RIGHT_CLICK_AIR)) {
            if (item.isSimilar(Items.abilityBox())) {
                e.setCancelled(true);
                AbilityGui.show(p);
            }
        }

    }

    @EventHandler
    public void onInteractEntity(PlayerInteractAtEntityEvent e) {
        BasePlayer p = BasePlayer.from(e.getPlayer());

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

            for (Ability ab : abilities) {

                if (ab.getAbilityItem().getItem().isSimilar(hand)) {
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
                    ab.activeAbility(p, target);
                    break;
                }
            }
        }
    }
}
