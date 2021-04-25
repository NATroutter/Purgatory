package net.natroutter.purgatory.utilities;

import net.natroutter.natlibs.handlers.gui.GUIWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lang {

    public String prefix = "§4§lPurgatory §8§l\u00BB ";
    public String ingameOnly = "§7This command can only be run ingame!";
    public String noPerm = "§7You do not have permission to do this";
    public String InvalidArgs = "§7Invalid command arguments";
    public String ToomanyArgs = "§7Too many command arguments";
    public String bannedOnly = "§7You cant use this because you are not banned";
    public String NotEnoughItems = "§7You don\u0027t have enough items in your inventory!";
    public String YouSold = "§7You sold §c{amount}§7x §c{item} §7for §c{price}";
    public String NotEnoughMoney = "§7You don\u0027t have enough money!";
    public String BanShorten = "§7Your ban has been shortened!";
    public String Unbanned = "§7Your have been unbanned!";
    public String InvalidPlayer = "§7Invalid player";
    public String InvalidAmount = "§7Invalid amount";
    public String maximumBalance = "§7Maximum balance is {max}";
    public String NoNegativeBalance = "§7Balance can\u0027t be negative";
    public String BalanceAdded = "§7Added §c{balance} §7to §c{name}\u0027s §7balance";
    public String BalanceRemoved = "§7Removed §c{balance} §7from §c{name}\u0027s §7balance";
    public String BalanceSet = "§c{name}\u0027s §7balance set to §c{balance}";
    public String BalanceReset = "§c{name}\u0027s §7balance rested!";
    public String FailedToAlterBalance = "§7Failed to alter balance!";
    public String ShopOpenCooldown = "§7I\u0027m busy right now...";
    public String NotBanned = "§7You are not banned!";

    public List<String> NotBanned_lore = new ArrayList<String>() {{
        add("§7You are not banned!");
    }};

    public List<String> Permaban_kickmessage = new ArrayList<String>() {{
        add("                    §4§lPurgatory               ");
        add("         §7You are permanently banned           ");
        add("§7so you don\u0027t have permissions to use purgatory");
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

    public items items = new items();
    public static class items {
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
