package net.natroutter.purgatory.features;

import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.bancheck.BanChecker;
import net.natroutter.purgatory.handlers.LitebansHandler;
import net.natroutter.purgatory.utilities.Config;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class AllowedCommands implements Listener {

    Config config = Purgatory.getCfg();
    Lang lang = Purgatory.getLang();

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        if (BanChecker.isBanned(p)) {
            if (!config.BannedAllowedCommands.contains(e.getMessage())) {
                e.setCancelled(true);
                p.sendMessage(lang.prefix + lang.notBannedOnly);
            }
        }
    }


}
