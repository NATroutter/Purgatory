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
    private static PlayerData dataStored;

    public static void ToggleAdminMode(Player p) {
        PlayerData data = PlayerDataHandler.queryForID(p.getUniqueId());
        if (data != null) {
            if (data.getAdminMode()) {
                data.setAdminMode(false);
                data.setSpectator(true);
                StringHandler message = new StringHandler(lang.AdminModeToggled).setPrefix(lang.prefix);
                message.replaceAll("{status}", lang.statues.disable);
                message.send(p);
                SpectatorHandler.spectatorMode(p, true, false);
            } else {
                data.setAdminMode(true);
                data.setSpectator(false);
                SpectatorHandler.clean(p);
                StringHandler message = new StringHandler(lang.AdminModeToggled).setPrefix(lang.prefix);
                message.replaceAll("{status}", lang.statues.enable);
                message.send(p);
                SpectatorHandler.spectatorMode(p, false, false);
            }
            dataStored = data;
            PlayerDataHandler.updateForID(data);
        }
    }

    public static boolean isAdmin(Player p) {
        if (dataStored != null) {
            return dataStored.getAdminMode();
        } else {
            PlayerData data = PlayerDataHandler.queryForID(p.getUniqueId());
            if (data != null) {
                dataStored = data;
                return data.getAdminMode();
            }
        }
        return false;
    }

}
