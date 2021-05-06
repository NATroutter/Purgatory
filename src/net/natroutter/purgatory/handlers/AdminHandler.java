package net.natroutter.purgatory.handlers;

import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.Spectator.SpectatorHandler;
import net.natroutter.purgatory.handlers.database.PlayerDataHandler;
import net.natroutter.purgatory.handlers.database.tables.PlayerData;
import net.natroutter.purgatory.utilities.Lang;

public class AdminHandler {

    private static final Lang lang = Purgatory.getLang();
    private static PlayerData dataStored;

    public static void ToggleAdminMode(BasePlayer p) {
        PlayerData data = PlayerDataHandler.queryForID(p.getUniqueId());
        if (data != null) {
            if (data.getAdminMode()) {
                data.setAdminMode(false);
                StringHandler message = new StringHandler(lang.AdminModeToggled).setPrefix(lang.prefix);
                message.replaceAll("{status}", lang.statues.disable);
                message.send(p);
            } else {
                data.setAdminMode(true);
                SpectatorHandler.clean(p);
                StringHandler message = new StringHandler(lang.AdminModeToggled).setPrefix(lang.prefix);
                message.replaceAll("{status}", lang.statues.enable);
                message.send(p);
            }
            dataStored = data;
            PlayerDataHandler.updateForID(data);
            SpectatorHandler.spectatorMode(p, !data.getAdminMode(), false);
        }
    }

    public static boolean isAdmin(BasePlayer p) {
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
