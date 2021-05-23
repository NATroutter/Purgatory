package net.natroutter.purgatory.features;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.purgatory.Purgatory;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class GeneralHandler implements Listener {

    private YamlDatabase database = Purgatory.getYamlDatabase();

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

}
