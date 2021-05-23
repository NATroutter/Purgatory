package net.natroutter.purgatory.features.shop;

import net.natroutter.natlibs.handlers.gui.GUIItem;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import net.natroutter.natlibs.objects.BaseItem;

import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.utilities.Config;
import net.natroutter.purgatory.utilities.Items;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ShopGUI {

    private final static Config config = Purgatory.getCfg();
    private final static Lang lang = Purgatory.getLang();

    private static HashMap<UUID, GUIWindow> GUIS = new HashMap<UUID, GUIWindow>();

    //shop items
    private static BaseItem stone = Items.shopItem(Material.STONE, lang.shop.stone_name, lang.shop.stone_lore, config.Shop.prices.stone);
    private static BaseItem andesite = Items.shopItem(Material.ANDESITE, lang.shop.andesite_name, lang.shop.andesite_lore, config.Shop.prices.andesite);
    private static BaseItem granite = Items.shopItem(Material.GRANITE, lang.shop.granite_name, lang.shop.granite_lore, config.Shop.prices.granite);
    private static BaseItem diorite = Items.shopItem(Material.DIORITE, lang.shop.diorite_name, lang.shop.diorite_lore, config.Shop.prices.diorite);
    private static BaseItem cobblestone = Items.shopItem(Material.COBBLESTONE, lang.shop.cobblestone_name, lang.shop.cobblestone_lore, config.Shop.prices.cobblestone);
    private static BaseItem dirt = Items.shopItem(Material.DIRT, lang.shop.dirt_name, lang.shop.dirt_lore, config.Shop.prices.dirt);
    private static BaseItem grassblock = Items.shopItem(Material.GRASS_BLOCK, lang.shop.grassblock_name, lang.shop.grassblock_lore, config.Shop.prices.grassblock);
    private static BaseItem emerald = Items.shopItem(Material.EMERALD, lang.shop.emerald_name, lang.shop.emerald_lore, config.Shop.prices.emerald);
    private static BaseItem diamond = Items.shopItem(Material.DIAMOND, lang.shop.diamond_name, lang.shop.diamond_lore, config.Shop.prices.diamond);
    private static BaseItem netherite_ingot = Items.shopItem(Material.NETHERITE_INGOT, lang.shop.netherite_name, lang.shop.netherite_lore, config.Shop.prices.netherite_ingot);
    private static BaseItem gold_ingot = Items.shopItem(Material.GOLD_INGOT, lang.shop.gold_ingot_name, lang.shop.gold_ingot_lore, config.Shop.prices.gold_ingot);
    private static BaseItem iron_ingot = Items.shopItem(Material.IRON_INGOT, lang.shop.iron_ingot_name, lang.shop.iron_ingot_lore, config.Shop.prices.iron_ingot);

    private static GUIWindow getGUI(Player p) {
        if (!GUIS.containsKey(p.getUniqueId())) {
            GUIS.put(p.getUniqueId(), new GUIWindow(lang.shop.title, GUIWindow.Rows.row6, true));
        }
        return GUIS.get(p.getUniqueId());
    }

    public static void show(Player p) {
        guiBuilder(p).show(p, true);
    }

    private static GUIWindow guiBuilder(Player p) {
        GUIWindow gui = getGUI(p);

        gui.setItem(new GUIItem(stone, (e)-> {
            ShopHandler.sell(p, stone.getType(), e.getAction());
            guiBuilder(p);
        }), GUIWindow.Rows.row2, 1);

        gui.setItem(new GUIItem(andesite, (e)-> {
            ShopHandler.sell(p, andesite.getType(), e.getAction());
            guiBuilder(p);
        }), GUIWindow.Rows.row2, 2);

        gui.setItem(new GUIItem(granite, (e)-> {
            ShopHandler.sell(p, granite.getType(), e.getAction());
            guiBuilder(p);
        }), GUIWindow.Rows.row2, 3);

        gui.setItem(new GUIItem(diorite, (e)-> {
            ShopHandler.sell(p, diorite.getType(), e.getAction());
            guiBuilder(p);
        }), GUIWindow.Rows.row2, 4);

        gui.setItem(new GUIItem(cobblestone, (e)-> {
            ShopHandler.sell(p, cobblestone.getType(), e.getAction());
            guiBuilder(p);
        }), GUIWindow.Rows.row2, 5);

        gui.setItem(new GUIItem(dirt, (e)-> {
            ShopHandler.sell(p, dirt.getType(), e.getAction());
            guiBuilder(p);
        }), GUIWindow.Rows.row2, 6);

        gui.setItem(new GUIItem(grassblock, (e)-> {
            ShopHandler.sell(p, grassblock.getType(), e.getAction());
            guiBuilder(p);
        }), GUIWindow.Rows.row2, 7);

        gui.setItem(new GUIItem(emerald, (e)-> {
            ShopHandler.sell(p, emerald.getType(), e.getAction());
            guiBuilder(p);
        }), GUIWindow.Rows.row3, 2);

        gui.setItem(new GUIItem(diamond, (e)-> {
            ShopHandler.sell(p, diamond.getType(), e.getAction());
            guiBuilder(p);
        }), GUIWindow.Rows.row3, 3);

        gui.setItem(new GUIItem(netherite_ingot, (e)-> {
            ShopHandler.sell(p, netherite_ingot.getType(), e.getAction());
            guiBuilder(p);
        }), GUIWindow.Rows.row3, 4);

        gui.setItem(new GUIItem(gold_ingot, (e)-> {
            ShopHandler.sell(p, gold_ingot.getType(), e.getAction());
            guiBuilder(p);
        }), GUIWindow.Rows.row3, 5);

        gui.setItem(new GUIItem(iron_ingot, (e)-> {
            ShopHandler.sell(p, iron_ingot.getType(), e.getAction());
            guiBuilder(p);
        }), GUIWindow.Rows.row3, 6);

        gui.setItem(new GUIItem(Items.playerInfo(p), (e)->{}), GUIWindow.Rows.row5, 1);

        gui.setItem(new GUIItem(Items.banInfo(p), (e)->{}), GUIWindow.Rows.row5, 4);

        gui.setItem(new GUIItem(Items.buyUnban(), (e)-> {
            ShopHandler.buyShortenBan(p, e.getAction());
            guiBuilder(p);
        }), GUIWindow.Rows.row5, 7);

        return gui;
    }

}
