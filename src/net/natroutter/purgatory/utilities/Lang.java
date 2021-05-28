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
    public String NoPermToChat = "§7Only not banned players can use chat!";
    public String UnlockNeeds = "§7Unlocking: §c{need}";
    public String Track_title = "§4§l{target}";
    public String Track_subtitle = "§c{distance}§7m";
    public String WearingHead = "§7You are now wearing §c{head}";
    public String NotWearing = "§7You are not wearing any heads right now";
    public String HeadRemoved = "§7You are not wearing any heads right now";
    public String AlreadyWearing = "§7You are already wearing this head";
    public String AdminModeToggled = "§7Adminmode: §c{status}";
    public String CantInAdminMode = "§7You cant do this while you are in adminmode!";
    public String YourBalance = "§7Your balance is §c{balance}";
    public String OtherBalance = "§c{player}§7's balance is §c{balance}";

    public statues statues = new statues();
    public static class statues {
        public String enable = "Enabled";
        public String disable = "Disabled";
    }

    public List<String> NotBanned_lore = new ArrayList<>() {{
        add("§7You are not banned!");
    }};

    public List<String> Permaban_kickmessage = new ArrayList<>() {{
        add("                    §4§lPurgatory               ");
        add("         §7You are permanently banned           ");
        add("§7so you don't have permissions to use purgatory");
    }};

    public List<String> configFail_kickmessage = new ArrayList<>() {{
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

    public settings settings = new settings();
    public static class settings {
        public String title = "§4§lSettings";
        public String item = "§4§lSettings";
        public List<String> item_lore = new ArrayList<>() {{
            add("§7Change your settings!");
        }};

        public VisibilityStatues VisibilityStatues = new VisibilityStatues();
        public static class VisibilityStatues {
            public String Hidden = "§c§lPlayers does not see you!";
            public String Shown = "§c§lPlayers can see you";
        }

        public String toSpawn = "§4§lTeleport to spawn";
        public List<String> toSpawn_lore = new ArrayList<>() {{
            add("§7Click here and you will be teleported to spawn");
        }};

        public String Complete_Visibility = "§4§lComplete visibility";
        public List<String> Complete_Visibility_lore = new ArrayList<>() {{
            add("§7Here you can toggle do other players see you at all");
            add("§7If toggled on no one can see you in game not even in playerlist");
            add(" ");
            add("§7Current status: §c{status}");
        }};

        public String Visibility = "§4§lVisibility";
        public List<String> Visibility_lore = new ArrayList<>() {{
            add("§7Here you can toggle your visibility");
            add("§7do you have invisibility potion or not?");
            add(" ");
            add("§7Current status: §c{status}");
        }};

        public String removehead_name = "§4§lRemove Helmet";
        public List<String> removehead_lore = new ArrayList<>() {{
            add("§7Remove current head");
        }};

        public String head1_name = "§4§lDragon";
        public List<String> head1_lore = new ArrayList<>() {{
            add("§7I will eat you!");
        }};

        public String head2_name = "§4§lMagma Monster";
        public List<String> head2_lore = new ArrayList<>() {{
            add("§7Be carefully i'm hot");
        }};

        public String head3_name = "§4§lGhost";
        public List<String> head3_lore = new ArrayList<>() {{
            add("§7Are you brave enough");
        }};

        public String head4_name = "§4§lSatan";
        public List<String> head4_lore = new ArrayList<>() {{
            add("§7Satan him self");
        }};

        public String head5_name = "§4§lCreepy Head";
        public List<String> head5_lore = new ArrayList<>() {{
            add("§7This is scary!");
        }};

    }

    public abilities abilities = new abilities();
    public static class abilities {

        public String gui_title = "§4§lAbilities";
        public String ability_cooldown = "§7Cooldown: §c{cooldown}";

        public String nause_name = "§4§lNause";
        public List<String> nause_lore = new ArrayList<>() {{
            add("§7Target goes blurrrr");
        }};

        public String flip_name = "§4§lFlip";
        public List<String> flip_lore = new ArrayList<>() {{
            add("§7Flips target 180");
        }};

        public String web_name = "§4§lWeb";
        public List<String> web_lore = new ArrayList<>() {{
            add("§7Spawns cobweb on target feet");
        }};

        public String smokescreen_name = "§4§lSmoke Screen";
        public List<String> smkokescreen_lore = new ArrayList<>() {{
            add("§7Blinds target with thicc smoke");
        }};

        public String slow_name = "§4§lSlow";
        public List<String> slow_lore = new ArrayList<>() {{
            add("§7Slows target for 5 seconds");
        }};

        public String paralysis_name = "§4§lParalysis";
        public List<String> paralysis_lore = new ArrayList<>() {{
            add("§7Paralyse target");
        }};

        public String speed_name = "§4§lSpeed";
        public List<String> speed_lore = new ArrayList<>() {{
            add("§7Target goes FAAAAAAAASSSSSSTTTT!!!");
        }};

        public String jumpboost_name = "§4§lJump boost";
        public List<String> jumpboost_lore = new ArrayList<>() {{
            add("§7Target goes high");
        }};

        public String lagstick_name = "§4§lLag Stick";
        public List<String> lagstick_lore = new ArrayList<>() {{
            add("§7Target warps backwards every seconds for 3 seconds");
        }};

        public String zombieinvasion_name = "§4§lZombie Invasion";
        public List<String> zombieinvasion_lore = new ArrayList<>() {{
            add("§7Spawns 5 zombies to target");
        }};

        public String scrambler_name = "§4§lScrambler";
        public List<String> scrambler_lore = new ArrayList<>() {{
            add("§7Scramble target's inventory");
        }};

        public String dropper_name = "§4§lDropper";
        public List<String> dropper_lore = new ArrayList<>() {{
            add("§7Drops random item from target's hotbar");
        }};

        public String freeshovels_name = "§4§lFree shovel";
        public List<String> freesholves_lore = new ArrayList<>() {{
            add("§7Fills target's inventory with wooden shovels");
        }};

        public String superScrambler_name = "§4§lSuper Scrambler";
        public List<String> superScrambler_lore = new ArrayList<>() {{
            add("§7Scramble target's inventory every 0.5 seconds for 5 seconds");
        }};

        public String thief_name = "§4§lThief";
        public List<String> thief_lore = new ArrayList<>() {{
            add("§7Removes items when target pickup something for 5 seconds");
        }};

        public String oreswap_name = "§4§lOre Swap";
        public List<String> oreswap_lore = new ArrayList<>() {{
            add("§7Replaces all ore in 25x25x25 area to stone");
        }};

        public String slowfall_name = "§4§lSlow Fall";
        public List<String> slowfall_lore = new ArrayList<>() {{
            add("§7Target goes up with levitation and goes down slow falling");
        }};


    }

    public items items = new items();
    public static class items {

        public String TrackerCompass_name = "§4§lTracker";
        public List<String> TrackerCompass_lore = new ArrayList<>() {{
            add("§7Tracks banned players in 500 blocks radius");
        }};

        public String abilities_name = "§4§lAbilities";
        public List<String> abilities_lore = new ArrayList<>() {{
            add("§7here you can find all kind of abilities");
            add("§7what you can use to troll banned players");
        }};

        public String playerinfo_name = "§4§l{name}";
        public List<String> playerinfo_lore = new ArrayList<>() {{
            add("§7Balance: §c{balance}");
        }};

        public String baninfo_name = "§4§lBan information";
        public List<String> baninfo_lore = new ArrayList<>() {{
            add("§7Reason: §c{reason}");
            add("§7Banned by: §c{bannedby}");
            add("§7Length: §c{length}");
        }};

        public String buyunban_name = "§4§lShorten ban";
        public List<String> buyunban_lore = new ArrayList<>() {{
            add("§7When you have enough money");
            add("§7you can shorten your ban");
            add(" ");
            add("§7One day price: §c{price}");
        }};
    }

    public shop shop = new shop();
    public static class shop {
        public String title = "§4§lPurgatory Shop";

        public String stone_name = "§c§lStone";
        public List<String> stone_lore = new ArrayList<>() {{
            add("§8Left-Click §7to sell 1 stone");
            add("§8Right-Click §7to sell 64 stones");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String andesite_name = "§4§lAndesite";
        public List<String> andesite_lore = new ArrayList<>() {{
            add("§8Left-Click §7to sell 1 andesite");
            add("§8Right-Click §7to sell 64 andesites");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String granite_name = "§4§lGranite";
        public List<String> granite_lore = new ArrayList<>() {{
            add("§8Left-Click §7to sell 1 granite");
            add("§8Right-Click §7to sell 64 granites");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String diorite_name = "§4§lDiorite";
        public List<String> diorite_lore = new ArrayList<>() {{
            add("§8Left-Click §7to sell 1 diorite");
            add("§8Right-Click §7to sell diorites");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String cobblestone_name = "§4§lCobblestone";
        public List<String> cobblestone_lore = new ArrayList<>() {{
            add("§8Left-Click §7to sell 1 cobblestone");
            add("§8Right-Click §7to sell 64 cobblestones");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String dirt_name = "§4§lDirt";
        public List<String> dirt_lore = new ArrayList<>() {{
            add("§8Left-Click §7to sell 1 dirt");
            add("§8Right-Click §7to sell 64 dirts");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String grassblock_name = "§4§lGrass Block";
        public List<String> grassblock_lore = new ArrayList<>() {{
            add("§8Left-Click §7to sell 1 grass block");
            add("§8Right-Click §7to sell 64 grass blocks");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String emerald_name = "§4§lEmerald";
        public List<String> emerald_lore = new ArrayList<>() {{
            add("§8Left-Click §7to sell 1 emerald");
            add("§8Right-Click §7to sell 64 emeralds");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String diamond_name = "§4§lDiamond";
        public List<String> diamond_lore = new ArrayList<>() {{
            add("§8Left-Click §7to sell 1 diamond");
            add("§8Right-Click §7to sell 64 diamonds");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String netherite_name = "§4§lNetherite";
        public List<String> netherite_lore = new ArrayList<>() {{
            add("§8Left-Click §7to sell 1 netherite");
            add("§8Right-Click §7to sell 64 netherites");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String gold_ingot_name = "§4§lGold ingot";
        public List<String> gold_ingot_lore = new ArrayList<>() {{
            add("§8Left-Click §7to sell 1 gold ingot");
            add("§8Right-Click §7to sell 64 gold ingots");
            add(" ");
            add("§7Price: §c{price}");
        }};

        public String iron_ingot_name = "§4§lIron ingot";
        public List<String> iron_ingot_lore = new ArrayList<>() {{
            add("§8Left-Click §7to sell 1 iron ingot");
            add("§8Right-Click §7to sell 64 iron ingots");
            add(" ");
            add("§7Price: §c{price}");
        }};

    }

}
