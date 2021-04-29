package net.natroutter.purgatory.features.shop;

import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.bancheck.BanChecker;
import net.natroutter.purgatory.handlers.EcoHandler;
import net.natroutter.purgatory.handlers.LitebansHandler;
import net.natroutter.purgatory.features.bancheck.BanData;
import net.natroutter.purgatory.utilities.Config;
import net.natroutter.purgatory.utilities.Lang;
import net.natroutter.purgatory.utilities.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;

public class ShopHandler {

    private static final Config config = Purgatory.getCfg();
    private static final Lang lang = Purgatory.getLang();
    private static final LitebansHandler lbh = Purgatory.getLitebans();

    public static void sell(BasePlayer p, Material mat, InventoryAction act) {
        if (act.equals(InventoryAction.PICKUP_HALF)) {
            boolean hasItem = false;
            for (ItemStack item : p.getInventory().getContents()) {
                if (item == null) { continue; }
                if (item.getType().equals(mat)) {
                    if (item.getAmount() >= 64) {
                        hasItem=true;
                        item.setAmount(item.getAmount() - 64);
                        double price = materialToPrice(mat) * 64;
                        EcoHandler.addBalance(p.getUniqueId(), price);

                        StringHandler msg = new StringHandler(lang.YouSold);
                        msg.setPrefix(lang.prefix);
                        msg.replaceAll("{amount}", 64);
                        msg.replaceAll("{item}", materialToName(mat));
                        msg.replaceAll("{price}", Utils.CurrencyFormat(price));
                        msg.send(p);
                        return;
                    }
                }
            }
            if (!hasItem) { p.sendMessage(lang.prefix + lang.NotEnoughItems); }
        } else if (act.equals(InventoryAction.PICKUP_ALL)) {
            boolean hasItem = false;
            for (ItemStack item : p.getInventory().getContents()) {
                if (item == null) { continue; }
                if (item.getType().equals(mat)) {
                    hasItem = true;
                    item.setAmount(item.getAmount() - 1);
                    EcoHandler.addBalance(p.getUniqueId(), materialToPrice(mat));

                    StringHandler msg = new StringHandler(lang.YouSold);
                    msg.setPrefix(lang.prefix);
                    msg.replaceAll("{amount}", 1);
                    msg.replaceAll("{item}", materialToName(mat));
                    msg.replaceAll("{price}", Utils.CurrencyFormat(materialToPrice(mat)));
                    msg.send(p);
                    return;
                }
            }
            if (!hasItem) { p.sendMessage(lang.prefix + lang.NotEnoughItems); }
        }
    }

    public static void buyShortenBan(BasePlayer p, InventoryAction act) {
        if (act.equals(InventoryAction.PICKUP_ALL)) {
            if (!BanChecker.isBanned(p)) {
                p.sendMessage(lang.prefix + lang.NotBanned);
                return;
            }

            if (EcoHandler.getBalance(p.getUniqueId()) == null) {return;}
            if (EcoHandler.getBalance(p.getUniqueId()) >= config.OneDayPrice) {
                if (EcoHandler.removeBalance(p.getUniqueId(), config.OneDayPrice)) {

                    BanData data = BanChecker.check(p);
                    if (data == null) {return;}

                    long newUntil = data.getUntil() - 86400000;

                    if (System.currentTimeMillis() >= newUntil) {
                        if (lbh.unBan(p.getUniqueId())) {
                            BanChecker.update(p);
                            p.sendMessage(lang.prefix + lang.Unbanned);
                        }

                    } else {
                        if (lbh.shortenBan(p.getUniqueId(), newUntil)) {
                            BanChecker.update(p);
                            p.sendMessage(lang.prefix + lang.BanShorten);
                        }
                    }

                }
            } else {
                p.sendMessage(lang.prefix + lang.NotEnoughMoney);
            }
        }
    }

    public static String materialToName(Material mat) {
        switch (mat) {
            case STONE: return ChatColor.stripColor(lang.shop.stone_name);
            case ANDESITE: return ChatColor.stripColor(lang.shop.andesite_name);
            case GRANITE: return ChatColor.stripColor(lang.shop.granite_name);
            case DIORITE: return ChatColor.stripColor(lang.shop.diorite_name);
            case COBBLESTONE: return ChatColor.stripColor(lang.shop.cobblestone_name);
            case DIRT: return ChatColor.stripColor(lang.shop.dirt_name);
            case GRASS_BLOCK: return ChatColor.stripColor(lang.shop.grassblock_name);
            case EMERALD: return ChatColor.stripColor(lang.shop.emerald_name);
            case DIAMOND: return ChatColor.stripColor(lang.shop.diamond_name);
            case NETHERITE_INGOT: return ChatColor.stripColor(lang.shop.netherite_name);
            case IRON_INGOT: return ChatColor.stripColor(lang.shop.iron_ingot_name);
            case GOLD_INGOT: return ChatColor.stripColor(lang.shop.gold_ingot_name);
            default: return "Unknown";
        }
    }

    public static double materialToPrice(Material mat) {
        switch (mat) {
            case STONE: return config.Shop.prices.stone;
            case ANDESITE: return config.Shop.prices.andesite;
            case GRANITE: return config.Shop.prices.granite;
            case DIORITE: return config.Shop.prices.diorite;
            case COBBLESTONE: return config.Shop.prices.cobblestone;
            case DIRT: return config.Shop.prices.dirt;
            case GRASS_BLOCK: return config.Shop.prices.grassblock;
            case EMERALD: return config.Shop.prices.emerald;
            case DIAMOND: return config.Shop.prices.diamond;
            case NETHERITE_INGOT: return config.Shop.prices.netherite_ingot;
            case IRON_INGOT: return config.Shop.prices.iron_ingot;
            case GOLD_INGOT: return config.Shop.prices.gold_ingot;
            default: return 0.0;
        }
    }


}
