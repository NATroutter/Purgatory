package net.natroutter.purgatory.utilities;

import java.math.BigDecimal;

public class Config {

    public String BanRemover = "Purgatory";
    public String ChatFormat = "{prefix}§7{displayname}{suffix}§8: §f{message}";

    public Integer OneDayPrice = 35000;
    public Integer MaxBalance = 100000000;
    public Integer SafeTimeSeconds = 300;
    public String SpawnRegionName = "spawn";

    public String spectatorCommand = "spectator";

    public settings settings = new settings();
    public static class settings {
        public String head1_permission = "purgatory.settings.head1";
        public String head1_needToUnlock = "Premium Vip";
        public String head1_base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2NhZDY3NzJiNmY4YTZiMDE0NWViZGVhMjdhOTc5ZTI0OTZmYWQ5ZTFlOWE5NTU1MjVhNzg4YzMxNWM3NDI1In19fQ==";

        public String head2_permission = "purgatory.settings.head2";
        public String head2_needToUnlock = "Premium+ vip";
        public String head2_base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTk3ZTY4MTQ5NTQ4NDc0ZmQwMmIxNzE5ZTU3NDlmODYyNTM3ZGQ3ODgxMTgwZTJmMmJlY2MzZDBkZmQ3YzM1MSJ9fX0=";

        public String head3_permission = "purgatory.settings.head3";
        public String head3_needToUnlock = "Ultimate vip";
        public String head3_base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJlZGI4ZDRiMDZlZWI5NzllZTUxNWY3NzhmMzFiM2RlZWY5MmZiNTgxN2YzNDUyZjUxZmM1OGQ0ODEzNCJ9fX0=";

        public String head4_permission = "purgatory.settings.head4";
        public String head4_needToUnlock = "Ultimate+ vip";
        public String head4_base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTRkNjYxZTE0YTQ4NjM2NWQ3OWZiMGNiZTdiYWQ1MjAzZDQ5YzBlY2RlZjBhMGFmMmM5MDFhOGVlMzI5In19fQ==";

        public String head5_permission = "purgatory.settings.head5";
        public String head5_needToUnlock = "Ultimate+ vip";
        public String head5_base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWCIsInVybCI6Imh0dHBzOi8vZWR1Y2F0aW9uLm1pbmVjcmFmdC5uZXQvd3AtY29udGVudC91cGxvYWRzLzY1YTUzNjkwLWFlNGYtMmMxZS1jZGNkLTQwMzc3YzkyODM2OC5wbmcifX19";

    }

    public abilities abilities = new abilities();
    public static class abilities {
        public String nause_permission = "purgatory.abilities.nause";
        public String nause_needToUnlock = "none";
        public Integer nause_cooldown = 120;

        public String flip_permission = "purgatory.abilities.flip";
        public String flip_needToUnlock = "none";
        public Integer flip_cooldown = 60;

        public String web_permission = "purgatory.abilities.web";
        public String web_needToUnlock = "none";
        public Integer web_cooldown = 60;

        public String smokescreen_permission = "purgatory.abilities.smkokescreen";
        public String smokescreen_needToUnlock = "none";
        public Integer smokescreen_cooldown = 60;

        public String slow_permission = "purgatory.abilities.slow";
        public String slow_needToUnlock = "none";
        public Integer slow_cooldown = 60;

        public String paralysis_permission = "purgatory.abilities.paralysis";
        public String paralysis_needToUnlock = "none";
        public Integer paralysis_cooldown = 90;

        public String sleep_permission = "purgatory.abilities.sleep";
        public String speed_needToUnlock = "none";
        public Integer sleep_cooldown = 60;

        public String jumpboost_permission = "purgatory.abilities.jumpboost";
        public String jumpboost_needToUnlock = "none";
        public Integer jumpboost_cooldown = 60;

        public String lagstick_permission = "purgatory.abilities.lagstick";
        public String lagstick_needToUnlock = "Premium Vip";
        public Integer lagstick_cooldown = 60;

        public String scrambler_permission = "purgatory.abilities.scrambler";
        public String scrambler_needToUnlock = "Premium Vip";
        public Integer scrambler_cooldown = 120;

        public String dropper_permission = "purgatory.abilities.dropper";
        public String dropper_needToUnlock = "Premium Vip";
        public Integer dropper_cooldown = 300;

        public String freeshovels_permission = "purgatory.abilities.freesholves";
        public String freeshovels_needToUnlock = "Premium Vip";
        public Integer freeshovels_cooldown = 120;

        public String superScrambler_permission = "purgatory.abilities.superscrambler";
        public String SuperScrambler_needToUnlock = "Premium Vip+";
        public Integer superScrambler_cooldown = 300;

        public String thief_permission = "purgatory.abilities.thief";
        public String thief_needToUnlock = "Ultimate vip";
        public Integer thief_cooldown = 300;

        public String oreswap_permission = "purgatory.abilities.oreswap";
        public String oreswap_needToUnlock = "Ultimate vip";
        public Integer oreswap_cooldown = 300;

        public String slowfall_permission = "purgatory.abilities.slowfall";
        public String slowfall_needToUnlock = "Ultimate+ vip";
        public Integer slowfall_cooldown = 300;
    }

    public Shop Shop = new Shop();
    public static class Shop {

        public Config.Shop.prices prices = new Config.Shop.prices();
        public static class prices {
            public double stone = 0.1;
            public double andesite = 0.1;
            public double granite = 0.1;
            public double diorite = 0.1;
            public double cobblestone = 0.1;
            public double dirt = 0.1;
            public double grassblock = 0.1;
            public double emerald = 0.5;
            public double diamond = 2;
            public double netherite_ingot = 6;
            public double iron_ingot = 1;
            public double gold_ingot = 1;

        }
    }

    public Database Database = new Database();
    public static class Database {
        public String name = "";
        public String host = "";
        public Integer port = 3306;
        public String user = "";
        public String pass = "";
        public String prefix = "purgatory_";
    }

    public LitebansDB litebansDB = new LitebansDB();
    public static class LitebansDB {
        public String name = "";
        public String host = "";
        public Integer port = 3306;
        public String user = "";
        public String pass = "";
        public String prefix = "litebans_";
    }

}
