package net.natroutter.purgatory.utilities;

import net.natroutter.natlibs.handlers.gui.GUIWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lang {

    public String prefix = "§4§lPurgatory §8§l» ";
    public String ingameOnly = "§7This command can only be run ingame!";
    public String noPerm = "§7You do not have permission to do this";
    public String InvalidArgs = "§7Invalid command arguments";
    public String ToomanyArgs = "§7Too many command arguments";
    public String bannedOnly = "§7You can't use this because you are not banned";
    public String notBannedOnly = "§7You can't use this because you are banned";
    public String NotEnoughItems = "§7You don't have enough items in your inventory!";
    public String YouSold = "§7You sold §c{amount}§7x §c{item} §7for §c{price}";
    public String NotEnoughMoney = "§7You don't have enough money!";
    public String BanShorten = "§7Your ban has been shortened!";
    public String Unbanned = "§7Your have been unbanned!";
    public String InvalidPlayer = "§7Invalid player";
    public String InvalidAmount = "§7Invalid amount";
    public String maximumBalance = "§7Maximum balance is {max}";
    public String NoNegativeBalance = "§7Balance can't be negative";
    public String BalanceAdded = "§7Added §c{balance} §7to §c{name}'s §7balance";
    public String BalanceRemoved = "§7Removed §c{balance} §7from §c{name}'s §7balance";
    public String BalanceSet = "§c{name}'s §7balance set to §c{balance}";
    public String BalanceReset = "§c{name}'s §7balance rested!";
    public String FailedToAlterBalance = "§7Failed to alter balance!";
    public String ShopOpenCooldown = "§7I'm busy right now...";
    public String NotBanned = "§7You are not banned!";
    public String SpectatorNotAllowed = "§7You can't do that while spectating!";
    public String SpectateSwitchCooldown = "§7You can't switch spectator mode so quickly wait {cooldown}!";
    public String SpectatorModeToggled = "§7Spectator mode: §c{status}";
    public String AbilityIsOnCooldown = "§7This ability is not cooldown wait §c{cooldown}";
    public String AlreadyHaveThisItem = "§7You already have this ability item in your inventory!";
    public String AbilityActivated = "§7Activated ability §c{ability} §7on player §c{player}";
    public String NoAbilityOnSpectator = "§7Abilities can't be activated to other spectators";
    public String NoPermToAbility = "§7You don't have permissions to this ability";
    public String AbilitySafeCooldown = "§c{player} §7is on safe cooldown for {cooldown}";
    public String AbilityProtectedArea = "§7You can't use abilities in protected areas!";
    public String SpawnSet = "§7Spawn has been set to your location!";
    public String TeleportedToSpawn = "§7You have been teleported to spawn!";
    public String SpawnNotSet = "§7Spawn is not set";
    public String ShopSet = "§7Shop has been set to your location!";
    public String NoPermToChat = "§7Only not banned players can use chat!";


    public statues statues = new statues();
    public static class statues {
        public String enable = "Enabled";
        public String disable = "Disabled";
    }

    public List<String> NotBanned_lore = new ArrayList<String>() {{
        add("§7You are not banned!");
    }};

    public List<String> Permaban_kickmessage = new ArrayList<String>() {{
        add("                    §4§lPurgatory               ");
        add("         §7You are permanently banned           ");
        add("§7so you don't have permissions to use purgatory");
    }};

    public List<String> configFail_kickmessage = new ArrayList<String>() {{
        add("                    §4§lPurgatory               ");
        add("              §7Configuration failure           ");
    }};


    public time time = new time();
    public static class time {
        public String seconds = "seconds";
        public String minutes = "minutes";
        public String hours = "hours";
        public String days = "days";
        public String text_color = "§7";
        public String highlight_color = "§c";
    }

    public abilities abilities = new abilities();
    public static class abilities {

        public String gui_title = "§4§lAbilities";
        public String ability_cooldown = "§7Cooldown: §c{cooldown}";

        public String nause_name = "§4§lNause";
        public String nause_permission = "purgatory.abilities.nause";
        public Integer nause_cooldown = 120;
        public List<String> nause_lore = new ArrayList<String>() {{
            add("§7Target goes blurrrr");
        }};

        public String flip_name = "§4§lFlip";
        public String flip_permission = "purgatory.abilities.flip";
        public Integer flip_cooldown = 60;
        public List<String> flip_lore = new ArrayList<String>() {{
            add("§7Flips target 180");
        }};

        public String web_name = "§4§lWeb";
        public String web_permission = "purgatory.abilities.web";
        public Integer web_cooldown = 60;
        public List<String> web_lore = new ArrayList<String>() {{
            add("§7Spawns cobweb on target feet");
        }};

        public String smokescreen_name = "§4§lSmoke Screen";
        public String smokescreen_permission = "purgatory.abilities.smkokescreen";
        public Integer smokescreen_cooldown = 60;
        public List<String> smkokescreen_lore = new ArrayList<String>() {{
            add("§7Blinds target with thicc smoke");
        }};

        public String slow_name = "§4§lSlow";
        public String slow_permission = "purgatory.abilities.slow";
        public Integer slow_cooldown = 60;
        public List<String> slow_lore = new ArrayList<String>() {{
            add("§7Slows target for 5 seconds");
        }};

        public String paralysis_name = "§4§lParalysis";
        public String paralysis_permission = "purgatory.abilities.paralysis";
        public Integer paralysis_cooldown = 90;
        public List<String> paralysis_lore = new ArrayList<String>() {{
            add("§7Paralyse target");
        }};

        public String speed_name = "§4§lSpeed";
        public String sleep_permission = "purgatory.abilities.sleep";
        public Integer sleep_cooldown = 60;
        public List<String> speed_lore = new ArrayList<String>() {{
            add("§7Target goes FAAAAAAAASSSSSSTTTT!!!");
        }};

        public String jumpboost_name = "§4§lJump boost";
        public String jumpboost_permission = "purgatory.abilities.jumpboost";
        public Integer jumpboost_cooldown = 60;
        public List<String> jumpboost_lore = new ArrayList<String>() {{
            add("§7Target goes high");
        }};

        public String lagstick_name = "§4§lLag Stick";
        public String lagstick_permission = "purgatory.abilities.lagstick";
        public Integer lagstick_cooldown = 60;
        public List<String> lagstick_lore = new ArrayList<String>() {{
            add("§7Target warps backwards every seconds for 3 seconds");
        }};

        public String scrambler_name = "§4§lScrambler";
        public String scrambler_permission = "purgatory.abilities.scrambler";
        public Integer scrambler_cooldown = 120;
        public List<String> scrambler_lore = new ArrayList<String>() {{
            add("§7Scramble target's inventory");
        }};

        public String superScrambler_name = "§4§lSuper Scrambler";
        public String superScrambler_permission = "purgatory.abilities.superscrambler";
        public Integer superScrambler_cooldown = 300;
        public List<String> superScrambler_lore = new ArrayList<String>() {{
            add("§7Scramble target's inventory every 0.5 seconds for 5 seconds");
        }};

        public String dropper_name = "§4§lDropper";
        public String dropper_permission = "purgatory.abilities.dropper";
        public Integer dropper_cooldown = 300;
        public List<String> dropper_lore = new ArrayList<String>() {{
            add("§7Drops random item from target's hotbar");
        }};

        public String freesholves_name = "§4§lFree shovel";
        public String freesholves_permission = "purgatory.abilities.freesholves";
        public Integer freesholves_cooldown = 120;
        public List<String> freesholves_lore = new ArrayList<String>() {{
            add("§7Fills target's inventory with wooden shovels");
        }};

        public String thief_name = "§4§lThief";
        public String thief_permission = "purgatory.abilities.thief";
        public Integer thief_cooldown = 300;
        public List<String> thief_lore = new ArrayList<String>() {{
            add("§7Removes items when target pickup something for 5 seconds");
        }};

        public String oreswap_name = "§4§lOre Swap";
        public String oreswap_permission = "purgatory.abilities.oreswap";
        public Integer oreswap_cooldown = 300;
        public List<String> oreswap_lore = new ArrayList<String>() {{
            add("§7Replaces all ore in 25x25x25 area to stone");
        }};

        public String slowfall_name = "§4§lSlow Fall";
        public String slowfall_permission = "purgatory.abilities.slowfall";
        public Integer slowfall_cooldown = 300;
        public List<String> slowfall_lore = new ArrayList<String>() {{
            add("§7Target goes up with levitation and goes down slow falling");
        }};


    }

    public items items = new items();
    public static class items {

        public String TrollerHelmet_name = "§4§lTroller Helmet";
        public List<String> TrollerHelmet_lore = new ArrayList<String>() {{
            add("§7U are pumpkin ;)");
        }};

        public String abilities_name = "§4§lAbilities";
        public List<String> abilities_lore = new ArrayList<String>() {{
            add("§7here you can find all kind of abilities");
            add("§7what you can use to troll banned players");
        }};

        public String playerinfo_name = "§4§l{name}";
        public List<String> playerinfo_lore = new ArrayList<String>() {{
            add("§7Balance: §c{balance}");
        }};

        public String baninfo_name = "§4§lBan information";
        public List<String> baninfo_lore = new ArrayList<String>() {{
            add("§7Reason: §c{reason}");
            add("§7Banned by: §c{bannedby}");
            add("§7Length: §c{length}");
        }};

        public String buyunban_name = "§4§lShorten ban";
        public List<String> buyunban_lore = new ArrayList<String>() {{
            add("§7When you have enough money");
            add("§7you can shorten your ban");
            add(" ");
            add("§7One day price: §c{price}");
        }};
    }

    public shop shop = new shop();
    public static class shop {
        public String title = "§4§lPurgatory Shop";
        public String npc_name = "§4§lPurgatory Shop";

        public String stone_name = "§c§lStone";
        public List<String> stone_lore = new ArrayList<String>() {{
            add("§8Left-Click §7to sell 1 stone");
            add("§8Right-Click §7to sell 64 stones");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String andesite_name = "§4§lAndesite";
        public List<String> andesite_lore = new ArrayList<String>() {{
            add("§8Left-Click §7to sell 1 andesite");
            add("§8Right-Click §7to sell 64 andesites");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String granite_name = "§4§lGranite";
        public List<String> granite_lore = new ArrayList<String>() {{
            add("§8Left-Click §7to sell 1 granite");
            add("§8Right-Click §7to sell 64 granites");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String diorite_name = "§4§lDiorite";
        public List<String> diorite_lore = new ArrayList<String>() {{
            add("§8Left-Click §7to sell 1 diorite");
            add("§8Right-Click §7to sell diorites");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String cobblestone_name = "§4§lCobblestone";
        public List<String> cobblestone_lore = new ArrayList<String>() {{
            add("§8Left-Click §7to sell 1 cobblestone");
            add("§8Right-Click §7to sell 64 cobblestones");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String dirt_name = "§4§lDirt";
        public List<String> dirt_lore = new ArrayList<String>() {{
            add("§8Left-Click §7to sell 1 dirt");
            add("§8Right-Click §7to sell 64 dirts");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String grassblock_name = "§4§lGrass Block";
        public List<String> grassblock_lore = new ArrayList<String>() {{
            add("§8Left-Click §7to sell 1 grass block");
            add("§8Right-Click §7to sell 64 grass blocks");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String emerald_name = "§4§lEmerald";
        public List<String> emerald_lore = new ArrayList<String>() {{
            add("§8Left-Click §7to sell 1 emerald");
            add("§8Right-Click §7to sell 64 emeralds");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String diamond_name = "§4§lDiamond";
        public List<String> diamond_lore = new ArrayList<String>() {{
            add("§8Left-Click §7to sell 1 diamond");
            add("§8Right-Click §7to sell 64 diamonds");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String netherite_name = "§4§lNetherite";
        public List<String> netherite_lore = new ArrayList<String>() {{
            add("§8Left-Click §7to sell 1 netherite");
            add("§8Right-Click §7to sell 64 netherites");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String gold_ingot_name = "§4§lGold ingot";
        public List<String> gold_ingot_lore = new ArrayList<String>() {{
            add("§8Left-Click §7to sell 1 gold ingot");
            add("§8Right-Click §7to sell 64 gold ingots");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String iron_ingot_name = "§4§lIron ingot";
        public List<String> iron_ingot_lore = new ArrayList<String>() {{
            add("§8Left-Click §7to sell 1 iron ingot");
            add("§8Right-Click §7to sell 64 iron ingots");
            add(" ");
            add("§7Price: §c{price}");
        }};

    }

}
