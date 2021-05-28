package net.natroutter.purgatory.features.abilities;

import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.abilities.ability.*;
import net.natroutter.purgatory.utilities.Config;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.Material;

import java.util.ArrayList;

public class AbilityHandler {

    private static final Lang lang = Purgatory.getLang();
    private static final Config config = Purgatory.getCfg();
    private static final Lang.abilities ab = lang.abilities;
    private static final Config.abilities cab = config.abilities;

    public static ArrayList<Ability> abilities = new ArrayList<>();

    //Ability items
    private static final AbilityItem nause_item = new AbilityItem("Nause", Material.SPIDER_EYE, ab.nause_name, ab.nause_lore, cab.nause_needToUnlock);
    private static final AbilityItem flip_item = new AbilityItem("Flip", Material.BRICK, ab.flip_name, ab.flip_lore, cab.flip_needToUnlock);
    private static final AbilityItem web_item = new AbilityItem("Web", Material.COBWEB, ab.web_name, ab.web_lore, cab.web_needToUnlock);
    private static final AbilityItem smokescreen_item = new AbilityItem("SmokeScreen", Material.FLINT_AND_STEEL, ab.smokescreen_name, ab.smkokescreen_lore, cab.smokescreen_needToUnlock);
    private static final AbilityItem slow_item = new AbilityItem("Slow", Material.FERMENTED_SPIDER_EYE, ab.slow_name, ab.slow_lore, cab.slow_needToUnlock);
    private static final AbilityItem paralysis_item = new AbilityItem("Paralysis", Material.STONE_AXE, ab.paralysis_name, ab.paralysis_lore, cab.paralysis_needToUnlock);
    private static final AbilityItem speed_item = new AbilityItem("Speed", Material.SUGAR, ab.speed_name, ab.speed_lore, cab.speed_needToUnlock);
    private static final AbilityItem jumpboost_item = new AbilityItem("JumpBoost", Material.BEACON, ab.jumpboost_name, ab.jumpboost_lore, cab.jumpboost_needToUnlock);
    private static final AbilityItem lagstick_item = new AbilityItem("LagStick", Material.STICK, ab.lagstick_name, ab.lagstick_lore, cab.lagstick_needToUnlock);
    private static final AbilityItem zombieinvasion_item = new AbilityItem("ZombieInvasion", Material.ZOMBIE_SPAWN_EGG, ab.zombieinvasion_name, ab.zombieinvasion_lore, cab.zombieinvasion_needToUnlock);
    private static final AbilityItem scrambler_item = new AbilityItem("Scrambler", Material.BLAZE_ROD, ab.scrambler_name, ab.scrambler_lore, cab.scrambler_needToUnlock);
    private static final AbilityItem dropper_item = new AbilityItem("Dropper", Material.DROPPER, ab.dropper_name, ab.dropper_lore, cab.dropper_needToUnlock);
    private static final AbilityItem freeshovels_item = new AbilityItem("Freeshovels", Material.WOODEN_SHOVEL, ab.freeshovels_name, ab.freesholves_lore, cab.freeshovels_needToUnlock);
    private static final AbilityItem superscrabler_item = new AbilityItem("SuperScrambler", Material.BLAZE_POWDER, ab.superScrambler_name, ab.superScrambler_lore, cab.SuperScrambler_needToUnlock);
    private static final AbilityItem thief_item = new AbilityItem("Thief", Material.GOLD_NUGGET, ab.thief_name, ab.thief_lore, cab.thief_needToUnlock);
    private static final AbilityItem oreswap_item = new AbilityItem("OreSwap", Material.IRON_ORE, ab.oreswap_name, ab.oreswap_lore, cab.oreswap_needToUnlock);
    private static final AbilityItem slowfall_item = new AbilityItem("SlowFall", Material.FEATHER, ab.slowfall_name, ab.slowfall_lore, cab.slowfall_needToUnlock);

    //register abilities
    public static void RegisterAbilities() {
        abilities.add(new Nause(nause_item, cab.nause_cooldown, cab.nause_permission));
        abilities.add(new Flip(flip_item, cab.flip_cooldown, cab.flip_permission));
        abilities.add(new Web(web_item, cab.web_cooldown, cab.web_permission));
        abilities.add(new Smokescreen(smokescreen_item, cab.smokescreen_cooldown, cab.smokescreen_permission));
        abilities.add(new Slow(slow_item, cab.slow_cooldown, cab.slow_permission));
        abilities.add(new Paralysis(paralysis_item, cab.paralysis_cooldown, cab.paralysis_permission));
        abilities.add(new Speed(speed_item, cab.sleep_cooldown, cab.sleep_permission));
        abilities.add(new JumpBoost(jumpboost_item, cab.jumpboost_cooldown, cab.jumpboost_permission));
        abilities.add(new LagStick(lagstick_item, cab.lagstick_cooldown, cab.lagstick_permission));
        abilities.add(new ZombieInvasion(zombieinvasion_item, cab.zombieinvasion_cooldown, cab.zombieinvasion_permission));
        abilities.add(new Scrambler(scrambler_item, cab.scrambler_cooldown, cab.scrambler_permission));
        abilities.add(new Dropper(dropper_item, cab.dropper_cooldown, cab.dropper_permission));
        abilities.add(new FreeShovels(freeshovels_item, cab.freeshovels_cooldown, cab.freeshovels_permission));
        abilities.add(new SuperScrambler(superscrabler_item, cab.scrambler_cooldown, cab.superScrambler_permission));
        abilities.add(new Thief(thief_item, cab.thief_cooldown, cab.thief_permission));
        abilities.add(new OreSwap(oreswap_item, cab.oreswap_cooldown, cab.oreswap_permission));
        abilities.add(new SlowFall(slowfall_item, cab.slowfall_cooldown, cab.slowfall_permission));
    }
}
