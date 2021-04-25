package net.natroutter.purgatory.utilities;

import java.math.BigDecimal;

public class Config {

    public String BanRemover = "Purgatory";

    public Integer OneDayPrice = 35000;
    public Integer MaxBalance = 100000000;

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

    public Npcs Npcs = new Npcs();
    public static class Npcs {
        public String Shop_loc_world = "world";
        public double Shop_loc_x = 0.5;
        public double Shop_loc_y = 100;
        public double Shop_loc_z = 0.5;
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
