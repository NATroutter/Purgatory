package net.natroutter.purgatory.features.abilities.settings;

import net.natroutter.natlibs.handlers.gui.GUIItem;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import net.natroutter.natlibs.objects.BaseItem;

import net.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.Spectator.SpectatorHandler;
import net.natroutter.purgatory.features.Spectator.SpectatorHandler.HelmetStatus;
import net.natroutter.purgatory.utilities.Config;
import net.natroutter.purgatory.utilities.Items;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class SettingsGUI {

    private static final Lang lang = Purgatory.getLang();
    private static final Config config = Purgatory.getCfg();

    //String TestHead = "TWl0w6RzIHPDpCBvaWtlaW4gbHV1bGV0IHRla2V2w6RzPyAKUGx1Z2luIG1hZGUgYnk6IE5BVHJvdXR0ZXIgCldlYnNpdGU6IE5BVHJvdXR0ZXIubmV0IApCVFcgem9sdHVzIG9uIGt1c2lww6TDpCBydW5ra3U="
    private static final BaseItem head1 = Items.WearableHead(lang.settings.head1_name, lang.settings.head1_lore, config.settings.head1_base64);
    private static final BaseItem head2 = Items.WearableHead(lang.settings.head2_name, lang.settings.head2_lore, config.settings.head2_base64);
    private static final BaseItem head3 = Items.WearableHead(lang.settings.head3_name, lang.settings.head3_lore, config.settings.head3_base64);
    private static final BaseItem head4 = Items.WearableHead(lang.settings.head4_name, lang.settings.head4_lore, config.settings.head4_base64);
    private static final BaseItem head5 = Items.WearableHead(lang.settings.head5_name, lang.settings.head5_lore, config.settings.head5_base64);

    private static HashMap<UUID, GUIWindow> GUIS = new HashMap<UUID, GUIWindow>();

    private static GUIWindow getGUI(Player p) {
        if (!GUIS.containsKey(p.getUniqueId())) {
            GUIS.put(p.getUniqueId(), new GUIWindow(lang.settings.title, GUIWindow.Rows.row5, true));
        }
        return GUIS.get(p.getUniqueId());
    }

    private static BaseItem DisplayItem(BaseItem item, String needed) {
        BaseItem newitem = new BaseItem(item);
        StringHandler needs = new StringHandler(lang.UnlockNeeds).replaceAll("{need}", needed);
        newitem.addLore(" ", needs.build());
        return newitem;
    }

    public static void show(Player p) {
        guiBuilder(p).show(p, true);
    }

    public static GUIWindow guiBuilder(Player p) {
        GUIWindow gui = getGUI(p);

        gui.setItem(new GUIItem(Items.Visibility(SettingsHandler.isInvisibility(p)), (e)-> {
            SettingsHandler.toggleVisibility(p);
            guiBuilder(p);
        }), GUIWindow.Rows.row2, 3);

        gui.setItem(new GUIItem(Items.ComplateVisibility(SettingsHandler.isCompleteVisibility(p)), (e)-> {
            SettingsHandler.toggleCompleteVisibility(p);
            guiBuilder(p);
        }), GUIWindow.Rows.row2, 5);

        gui.setItem(new GUIItem(Items.RemoveHelmet(), (e)-> {
            HelmetStatus status = SpectatorHandler.setHelmet(p, null);
            if (status.equals(HelmetStatus.REMOVED)) {
                p.sendMessage(lang.prefix + lang.HeadRemoved);
            } else if (status.equals(HelmetStatus.NOTWEARING)) {
                p.sendMessage(lang.prefix + lang.NotWearing);
            }
        }), GUIWindow.Rows.row4, 1);

        gui.setItem(new GUIItem(DisplayItem(head1, config.settings.head1_needToUnlock), (e)-> {
            if (!p.hasPermission(config.settings.head1_permission)) {
                p.sendMessage(lang.prefix + lang.noPerm);
                return;
            }
            HelmetStatus status = SpectatorHandler.setHelmet(p, head1);
            if (status.equals(HelmetStatus.MODIFIED)) {
                StringHandler message = new StringHandler(lang.WearingHead).setPrefix(lang.prefix);
                message.replaceAll("{head}", ChatColor.stripColor(lang.settings.head1_name));
                message.send(p);
            } else if (status.equals(HelmetStatus.ALREADY)) {
                p.sendMessage(lang.prefix + lang.AlreadyWearing);
            }
        }), GUIWindow.Rows.row4, 3);

        gui.setItem(new GUIItem(DisplayItem(head2, config.settings.head2_needToUnlock), (e)-> {
            if (!p.hasPermission(config.settings.head2_permission)) {
                p.sendMessage(lang.prefix + lang.noPerm);
                return;
            }
            HelmetStatus status = SpectatorHandler.setHelmet(p, head2);
            if (status.equals(HelmetStatus.MODIFIED)) {
                StringHandler message = new StringHandler(lang.WearingHead).setPrefix(lang.prefix);
                message.replaceAll("{head}", ChatColor.stripColor(lang.settings.head2_name));
                message.send(p);
            } else if (status.equals(HelmetStatus.ALREADY)) {
                p.sendMessage(lang.prefix + lang.AlreadyWearing);
            }
        }), GUIWindow.Rows.row4, 4);

        gui.setItem(new GUIItem(DisplayItem(head3, config.settings.head3_needToUnlock), (e)-> {
            if (!p.hasPermission(config.settings.head3_permission)) {
                p.sendMessage(lang.prefix + lang.noPerm);
                return;
            }
            HelmetStatus status = SpectatorHandler.setHelmet(p, head3);
            if (status.equals(HelmetStatus.MODIFIED)) {
                StringHandler message = new StringHandler(lang.WearingHead).setPrefix(lang.prefix);
                message.replaceAll("{head}", ChatColor.stripColor(lang.settings.head3_name));
                message.send(p);
            } else if (status.equals(HelmetStatus.ALREADY)) {
                p.sendMessage(lang.prefix + lang.AlreadyWearing);
            }
        }), GUIWindow.Rows.row4, 5);

        gui.setItem(new GUIItem(DisplayItem(head4, config.settings.head4_needToUnlock), (e)-> {
            if (!p.hasPermission(config.settings.head4_permission)) {
                p.sendMessage(lang.prefix + lang.noPerm);
                return;
            }
            HelmetStatus status = SpectatorHandler.setHelmet(p, head4);
            if (status.equals(HelmetStatus.MODIFIED)) {
                StringHandler message = new StringHandler(lang.WearingHead).setPrefix(lang.prefix);
                message.replaceAll("{head}", ChatColor.stripColor(lang.settings.head4_name));
                message.send(p);
            } else if (status.equals(HelmetStatus.ALREADY)) {
                p.sendMessage(lang.prefix + lang.AlreadyWearing);
            }
        }), GUIWindow.Rows.row4, 6);

        gui.setItem(new GUIItem(DisplayItem(head5, config.settings.head5_needToUnlock), (e)-> {
            if (!p.hasPermission(config.settings.head5_permission)) {
                p.sendMessage(lang.prefix + lang.noPerm);
                return;
            }
            HelmetStatus status = SpectatorHandler.setHelmet(p, head5);
            if (status.equals(HelmetStatus.MODIFIED)) {
                StringHandler message = new StringHandler(lang.WearingHead).setPrefix(lang.prefix);
                message.replaceAll("{head}", ChatColor.stripColor(lang.settings.head5_name));
                message.send(p);
            } else if (status.equals(HelmetStatus.ALREADY)) {
                p.sendMessage(lang.prefix + lang.AlreadyWearing);
            }
        }), GUIWindow.Rows.row4, 7);

        return gui;
    }









}
