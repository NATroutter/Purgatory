package net.natroutter.purgatory.features.abilities;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.natlibs.utilities.Utilities;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.Spectator.SpectatorHandler;
import net.natroutter.purgatory.features.abilities.ability.*;
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
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class AbilityHandler implements Listener {

    private static final Config config = Purgatory.getCfg();
    private static final YamlDatabase database = Purgatory.getYamlDatabase();
    private static final Utilities utils = Purgatory.getUtilities();
    private static final Lang lang = Purgatory.getLang();
    private static final Lang.abilities ab = lang.abilities;

    private HashMap<String, Long> cooldowns = new HashMap<>();
    private HashMap<UUID, Long> interactCooldowns = new HashMap<>();
    private HashMap<UUID, Long> safeCooldowns = new HashMap<>();
    public static ArrayList<Ability> abilities = new ArrayList<>();




    //Ability items
    private static final AbilityItem nause_item = new AbilityItem("Nause", Material.SPIDER_EYE, ab.nause_name, ab.nause_lore, ab.nause_needToUnlock);
    private static final AbilityItem flip_item = new AbilityItem("Flip", Material.BRICK, ab.flip_name, ab.flip_lore, ab.flip_needToUnlock);
    private static final AbilityItem web_item = new AbilityItem("Web", Material.COBWEB, ab.web_name, ab.web_lore, ab.web_needToUnlock);
    private static final AbilityItem smokescreen_item = new AbilityItem("SmokeScreen", Material.FLINT_AND_STEEL, ab.smokescreen_name, ab.smkokescreen_lore, ab.smokescreen_needToUnlock);
    private static final AbilityItem slow_item = new AbilityItem("Slow", Material.FERMENTED_SPIDER_EYE, ab.slow_name, ab.slow_lore, ab.slow_needToUnlock);
    private static final AbilityItem paralysis_item = new AbilityItem("Paralysis", Material.STONE_AXE, ab.paralysis_name, ab.paralysis_lore, ab.paralysis_needToUnlock);
    private static final AbilityItem speed_item = new AbilityItem("Speed", Material.SUGAR, ab.speed_name, ab.speed_lore, ab.speed_needToUnlock);
    private static final AbilityItem jumpboost_item = new AbilityItem("JumpBoost", Material.BEACON, ab.jumpboost_name, ab.jumpboost_lore, ab.jumpboost_needToUnlock);
    private static final AbilityItem lagstick_item = new AbilityItem("LagStick", Material.STICK, ab.lagstick_name, ab.lagstick_lore, ab.lagstick_needToUnlock);
    private static final AbilityItem scrambler_item = new AbilityItem("Scrambler", Material.BLAZE_ROD, ab.scrambler_name, ab.scrambler_lore, ab.scrambler_needToUnlock);
    private static final AbilityItem dropper_item = new AbilityItem("Dropper", Material.DROPPER, ab.dropper_name, ab.dropper_lore, ab.dropper_needToUnlock);
    private static final AbilityItem freeshovels_item = new AbilityItem("Freeshovels", Material.WOODEN_SHOVEL, ab.freeshovels_name, ab.freesholves_lore, ab.freeshovels_needToUnlock);
    private static final AbilityItem superscrabler_item = new AbilityItem("SuperScrambler", Material.BLAZE_POWDER, ab.superScrambler_name, ab.superScrambler_lore, ab.SuperScrambler_needToUnlock);
    private static final AbilityItem thief_item = new AbilityItem("Thief", Material.GOLD_NUGGET, ab.thief_name, ab.thief_lore, ab.thief_needToUnlock);
    private static final AbilityItem oreswap_item = new AbilityItem("OreSwap", Material.IRON_ORE, ab.oreswap_name, ab.oreswap_lore, ab.oreswap_needToUnlock);
    private static final AbilityItem slowfall_item = new AbilityItem("SlowFall", Material.FEATHER, ab.slowfall_name, ab.slowfall_lore, ab.slowfall_needToUnlock);

    //register abilities
    public static void RegisterAbilities() {
        abilities.add(new Nause(nause_item, ab.nause_cooldown, ab.nause_permission));
        abilities.add(new Flip(flip_item, ab.flip_cooldown, ab.flip_permission));
        abilities.add(new Web(web_item, ab.web_cooldown, ab.web_permission));
        abilities.add(new Smokescreen(smokescreen_item, ab.smokescreen_cooldown, ab.smokescreen_permission));
        abilities.add(new Slow(slow_item, ab.slow_cooldown, ab.slow_permission));
        abilities.add(new Paralysis(paralysis_item, ab.paralysis_cooldown, ab.paralysis_permission));
        abilities.add(new Speed(speed_item, ab.sleep_cooldown, ab.sleep_permission));
        abilities.add(new JumpBoost(jumpboost_item, ab.jumpboost_cooldown, ab.jumpboost_permission));
        abilities.add(new LagStick(lagstick_item, ab.lagstick_cooldown, ab.lagstick_permission));
        abilities.add(new Scrambler(scrambler_item, ab.scrambler_cooldown, ab.scrambler_permission));
        abilities.add(new Dropper(dropper_item, ab.dropper_cooldown, ab.dropper_permission));
        abilities.add(new FreeShovels(freeshovels_item, ab.freeshovels_cooldown, ab.freeshovels_permission));
        abilities.add(new SuperScrambler(superscrabler_item, ab.scrambler_cooldown, ab.superScrambler_permission));
        abilities.add(new Thief(thief_item, ab.thief_cooldown, ab.thief_permission));
        abilities.add(new OreSwap(oreswap_item, ab.oreswap_cooldown, ab.oreswap_permission));
        abilities.add(new SlowFall(slowfall_item, ab.slowfall_cooldown, ab.slowfall_permission));
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

                    Location loc = database.getLocation("General", "Spawn");
                    if (loc != null) {
                        Integer radius = config.SafeSpawnRadius;
                        Location corn1 = new Location(loc.getWorld(), loc.getX() - radius, 0, loc.getZ() - radius, loc.getYaw(), loc.getPitch());
                        Location corn2 = new Location(loc.getWorld(), loc.getX() + radius, 256, loc.getZ() + radius, loc.getYaw(), loc.getPitch());

                        if (utils.inRegion(target.getLocation(), corn1, corn2)) {
                            p.sendMessage(lang.prefix + lang.AbilityProtectedArea);
                            return;
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
