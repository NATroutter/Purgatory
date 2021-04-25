package net.natroutter.purgatory.utilities;

import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.SkullCreator;
import net.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.purgatory.handlers.EcoHandler;
import net.natroutter.purgatory.handlers.database.PlayerDataHandler;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.BanChecker;
import net.natroutter.purgatory.objects.BanData;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Items {

    private final static Lang lang = Purgatory.getLang();
    private final static Config config = Purgatory.getCfg();

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

        if (BanChecker.isBanned(p)) {
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
