package net.natroutter.purgatory.utilities;

import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.SkullCreator;
import net.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.purgatory.handlers.EcoHandler;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.bancheck.BanChecker;
import net.natroutter.purgatory.features.bancheck.BanData;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.List;

public class Items {

    private final static Lang lang = Purgatory.getLang();
    private final static Config config = Purgatory.getCfg();

    public static BaseItem RemoveHelmet() {
        BaseItem item = new BaseItem(Material.BARRIER);
        item.setDisplayName(lang.settings.removehead_name);
        item.setLore(lang.settings.removehead_lore);
        item.addItemFlags(ItemFlag.values());
        return item;
    }

    public static BaseItem WearableHead(String name, List<String> lore, String texture) {
        BaseItem item = new SkullCreator().Create(name, texture);
        item.setLore(lore);
        item.addItemFlags(ItemFlag.values());
        return item;
    }

    public static BaseItem Visibility(boolean visible) {
        BaseItem item = new BaseItem(visible ? Material.SLIME_BALL : Material.MAGMA_CREAM);
        item.setDisplayName(lang.settings.Visibility);
        List<String> lore = new ArrayList<>();
        for (String line : lang.settings.Visibility_lore) {
            StringHandler str = new StringHandler(line);
            str.replaceAll("{status}", visible ? lang.settings.VisibilityStatues.Hidden : lang.settings.VisibilityStatues.Shown);
            lore.add(str.build());
        }
        item.setLore(lore);
        item.addItemFlags(ItemFlag.values());
        return item;
    }

    public static BaseItem ComplateVisibility(boolean visible) {
        BaseItem item = new BaseItem(visible ? Material.SLIME_BALL : Material.MAGMA_CREAM);
        item.setDisplayName(lang.settings.Complete_Visibility);

        List<String> lore = new ArrayList<>();
        for (String line : lang.settings.Complete_Visibility_lore) {
            StringHandler str = new StringHandler(line);
            str.replaceAll("{status}", visible ? lang.settings.VisibilityStatues.Hidden : lang.settings.VisibilityStatues.Shown);
            lore.add(str.build());
        }
        item.setLore(lore);
        item.addItemFlags(ItemFlag.values());
        return item;
    }

    public static BaseItem Settings() {
        BaseItem item = new BaseItem(Material.KNOWLEDGE_BOOK);
        item.setDisplayName(lang.settings.item);
        item.setLore(lang.settings.item_lore);
        item.addItemFlags(ItemFlag.values());
        return item;
    }

    public static BaseItem TrackerCompass() {
        BaseItem item = new BaseItem(Material.COMPASS);
        item.setDisplayName(lang.items.TrackerCompass_name);
        item.setLore(lang.items.TrackerCompass_lore);
        item.addItemFlags(ItemFlag.values());
        return item;
    }

    public static BaseItem abilityBox() {
        BaseItem item = new BaseItem(Material.ENDER_CHEST);
        item.setDisplayName(lang.items.abilities_name);
        item.setLore(lang.items.abilities_lore);
        item.addItemFlags(ItemFlag.values());
        return item;
    }

    public static BaseItem shopItem(Material mat, String name, List<String> lore, double price) {
        BaseItem item = new BaseItem(mat);
        item.setDisplayName(name);

        List<String> nlore = new ArrayList<>();
        for (String line : lore) {
            StringHandler str = new StringHandler(line);
            str.replaceAll("{price}", Utils.CurrencyFormat(price));
            nlore.add(str.build());
        }

        item.setLore(nlore);
        item.addItemFlags(ItemFlag.values());
        return item;
    }

    public static BaseItem playerInfo(BasePlayer p) {
        BaseItem item = new SkullCreator().Create(p);

        StringHandler name = new StringHandler(lang.items.playerinfo_name);
        name.replaceAll("{name}", p.getName());
        item.setDisplayName(name.build());

        List<String> lore = new ArrayList<>();
        for (String line : lang.items.playerinfo_lore) {
            StringHandler str = new StringHandler(line);
            double bal = EcoHandler.getBalance(p.getUniqueId());
            str.replaceAll("{balance}", Utils.CurrencyFormat(bal));
            lore.add(str.build());
        }
        item.setLore(lore);

        item.addItemFlags(ItemFlag.values());
        return item;
    }

    public static BaseItem banInfo(BasePlayer p) {
        BaseItem item = new BaseItem(Material.WRITABLE_BOOK);

        item.setDisplayName(lang.items.baninfo_name);

        if (BanChecker.isBanned(p)) {
            BanData data = BanChecker.check(p);
            List<String> lore = new ArrayList<>();
            for (String line : lang.items.baninfo_lore) {
                StringHandler str = new StringHandler(line);
                if (data != null) {
                    str.replaceAll("{reason}", data.getReason());
                    str.replaceAll("{bannedby}", data.getBanned_by_name());
                    str.replaceAll("{length}", Utils.timeLeft(data.getBanSeconds()));
                }
                lore.add(str.build());
            }

            item.setLore(lore);
        } else {
            item.setLore(lang.NotBanned_lore);
        }

        item.addItemFlags(ItemFlag.values());
        return item;
    }
    public static BaseItem buyUnban() {
        BaseItem item = new BaseItem(Material.FIREWORK_STAR);
        item.setDisplayName(lang.items.buyunban_name);

        List<String> lore = new ArrayList<>();
        for (String line : lang.items.buyunban_lore) {
            StringHandler str = new StringHandler(line);
            str.replaceAll("{price}", Utils.CurrencyFormat(config.OneDayPrice));
            lore.add(str.build());
        }
        item.setLore(lore);

        item.addItemFlags(ItemFlag.values());
        return item;
    }

}
