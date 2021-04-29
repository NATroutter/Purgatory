package net.natroutter.purgatory.handlers.abilities;

import net.natroutter.natlibs.handlers.gui.GUIItem;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.inventory.ItemStack;

public class AbilityGui {

    private static final Lang lang = Purgatory.getLang();

    public static void show(BasePlayer p) {
        guiBuilder(p).show(p);
    }

    private static GUIWindow guiBuilder(BasePlayer p) {
        GUIWindow gui = new GUIWindow(lang.abilities.gui_title, GUIWindow.Rows.row6, true);
        Integer pos = 0;

        for (Ability ab : AbilityHandler.abilities) {
            gui.setItem(new GUIItem(ab.getAbilityItem().getItem(), (e)->{

                for (ItemStack item : p.getInventory().getContents()) {
                    if (item == null) {continue;}
                    BaseItem invItem = BaseItem.from(item);
                    if (invItem.matches(ab.getAbilityItem().getItem())) {
                        p.sendMessage(lang.prefix + lang.AlreadyHaveThisItem);
                        return;
                    }
                }
                p.getInventory().addItem(ab.getAbilityItem().getItem());

            }),pos);
            pos++;
        }

        return gui;
    }

}
