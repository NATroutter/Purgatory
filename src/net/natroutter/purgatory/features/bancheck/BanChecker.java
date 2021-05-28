package net.natroutter.purgatory.features.bancheck;


import net.natroutter.purgatory.handlers.LitebansHandler;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.handlers.database.Database;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class BanChecker implements Listener {

    private final static LitebansHandler litebans = Purgatory.getLitebans();
    private final static Database database = Purgatory.getDatabase();
    private final static Lang lang = Purgatory.getLang();
    private static HashMap<UUID, BanData> Bans = new HashMap<UUID, BanData>();

    public static BanData check(Player p) {
        return Bans.getOrDefault(p.getUniqueId(), null);
    }

    public static boolean isBanned(Player p) {
        return Bans.containsKey(p.getUniqueId());
    }

    public static void update(Player p) {
        BanData data = litebans.getBan(p.getUniqueId());
        if (data != null) {
            Bans.put(p.getUniqueId(), data);
        } else {
            Bans.remove(p.getUniqueId());
            p.setDisplayName("§7" + p.getName());
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPreJoin(AsyncPlayerPreLoginEvent e) {
        if (database.getValid() && litebans.getValid()) {
            BanData data = litebans.getBan(e.getUniqueId());
            if (data != null) {
                if (data.isPermanent()) {
                    e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, "\n" + String.join("\n", lang.Permaban_kickmessage));
                    return;
                }
                Bans.put(e.getUniqueId(), data);
            }
        } else {
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "\n" + String.join("\n", lang.configFail_kickmessage));
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Bans.remove(e.getPlayer().getUniqueId());
    }
}
