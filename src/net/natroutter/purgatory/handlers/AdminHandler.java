package net.natroutter.purgatory.handlers;


import net.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.Spectator.SpectatorHandler;
import net.natroutter.purgatory.handlers.database.PlayerDataHandler;
import net.natroutter.purgatory.handlers.database.tables.PlayerData;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.entity.Player;

public class AdminHandler {

    private static final Lang lang = Purgatory.getLang();

    public static void AdminMode(Player p, boolean state) {AdminMode(p,state,false);}
    public static void AdminMode(Player p, boolean state, boolean silent) {
        PlayerData data = PlayerDataHandler.queryForID(p.getUniqueId());
        if (data != null) {
            if (state) {
                data.setAdminMode(true);
                if (!silent) {
                    StringHandler message = new StringHandler(lang.AdminModeToggled).setPrefix(lang.prefix);
                    message.replaceAll("{status}", lang.statues.enable);
                    message.send(p);
                }
            } else {
                data.setAdminMode(false);
                if (!silent) {
                    StringHandler message = new StringHandler(lang.AdminModeToggled).setPrefix(lang.prefix);
                    message.replaceAll("{status}", lang.statues.disable);
                    message.send(p);
                }
            }
            PlayerDataHandler.updateForID(data);
        }
    }

    public static boolean isAdmin(Player p) {
        PlayerData data = PlayerDataHandler.queryForID(p.getUniqueId());
        if (data != null) {
            return data.getAdminMode();
        }
        return false;
    }

}
