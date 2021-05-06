package net.natroutter.purgatory.features.abilities;

import net.natroutter.natlibs.handlers.gui.GUIItem;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.utilities.Lang;
import net.natroutter.purgatory.utilities.Utils;
import org.bukkit.inventory.ItemStack;

public class AbilityGUI {

    private static final Lang lang = Purgatory.getLang();

    public static void show(BasePlayer p) {
        guiBuilder(p).show(p);
    }

    private static GUIWindow guiBuilder(BasePlayer p) {
        GUIWindow gui = new GUIWindow(lang.abilities.gui_title, GUIWindow.Rows.row6, true);
        Integer pos = 0;

        for (Ability ab : AbilityHandler.abilities) {
            BaseItem DisplayItem = new BaseItem(ab.getAbilityItem().getItem());
            StringHandler loreitem1 = new StringHandler(lang.abilities.ability_cooldown);
            loreitem1.replaceAll("{cooldown}" , Utils.timeLeft(ab.getCooldownSeconds()));

            if (ab.getAbilityItem().hasNeeds()) {
                DisplayItem.addLore(loreitem1.build());
            } else {
                DisplayItem.addLore(" ", loreitem1.build());
            }

            gui.setItem(new GUIItem(DisplayItem, (e)->{

                for (ItemStack item : p.getInventory().getContents()) {
                    if (item == null) {continue;}
                    BaseItem invItem = BaseItem.from(item);
                    if (invItem.matches(ab.getAbilityItem().getItem())) {
                        p.sendMessage(lang.prefix + lang.AlreadyHaveThisItem);
                        return;
                    }
                }

                if (!p.hasPermission(ab.getPermission())) {
                    p.sendMessage(lang.prefix + lang.NoPermToAbility);
                    return;
                }

                p.getInventory().addItem(ab.getAbilityItem().getItem());

            }),pos);
            pos++;
        }

        return gui;
    }

}
