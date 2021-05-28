package net.natroutter.purgatory.features;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.handlers.Hooks;
import net.natroutter.purgatory.utilities.Config;
import net.natroutter.purgatory.utilities.Lang;
import net.natroutter.purgatory.utilities.Utils;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.world.PortalCreateEvent;

public class GeneralHandler implements Listener {

    private YamlDatabase database = Purgatory.getYamlDatabase();
    private Hooks hooks = Purgatory.getHooks();
    private Config config = Purgatory.getCfg();
    private Lang lang = Purgatory.getLang();

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (p.getBedSpawnLocation() == null) {
            Location loc = database.getLocation("General", "Spawn");
            if (loc != null) {
                e.setRespawnLocation(loc);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPlayedBefore()) {
            Location loc = database.getLocation("General", "Spawn");
            if (loc != null) {
                p.teleport(loc);
            }
        }
    }

    @EventHandler
    public void onPortalCreate(PortalCreateEvent e) {
        if (e.getReason().equals(PortalCreateEvent.CreateReason.NETHER_PAIR) || e.getReason().equals(PortalCreateEvent.CreateReason.FIRE)) {
            for (BlockState b : e.getBlocks()) {
                if (hooks.worldguard.isHooked()) {
                    if (Utils.inRegion(b.getLocation(), config.SpawnRegionName)) {
                        e.setCancelled(true);
                        return;
                    }
                }
            }
        }
    }

}
