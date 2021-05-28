package net.natroutter.purgatory.features.TrackerCompass;

import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.Spectator.SpectatorHandler;
import net.natroutter.purgatory.handlers.AdminHandler;
import net.natroutter.purgatory.utilities.Config;
import net.natroutter.purgatory.utilities.Items;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.Location;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.UUID;

public class CompassEvents implements Listener {

    private static final Lang lang = Purgatory.getLang();
    private static final Config config = Purgatory.getCfg();

    HashMap<UUID, Boolean> Trackers = new HashMap<>();
    HashMap<UUID, Long> cool1 = new HashMap<>();

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (AdminHandler.isAdmin(p)) {return;}
        if (Trackers.getOrDefault(p.getUniqueId(), false)) {
            for (Entity ent : p.getNearbyEntities(config.CompassRadius, config.CompassRadius, config.CompassRadius)) {
                if (!(ent instanceof Player)) { continue; }
                Player target = (Player)ent;

                if (AdminHandler.isAdmin(target)) {continue;}
                if (SpectatorHandler.isSpectator(target)) {continue;}

                Location l1 = target.getLocation();
                Location l2 = p.getLocation();
                double distance = Math.sqrt(Math.pow(l1.getX() - l2.getX(), 2) + Math.pow(l1.getY() - l2.getY(), 2) + Math.pow(l1.getZ() - l2.getZ(), 2) );

                StringHandler title = new StringHandler(lang.Track_title);
                title.replaceAll("{target}", target.getName());
                StringHandler subtitle = new StringHandler(lang.Track_subtitle);
                subtitle.replaceAll("{distance}", (int)distance);

                p.sendTitle(title.build(),subtitle.build(),0,15,15);
                break;
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (!e.hasItem()) { return; }
        if (!e.getAction().name().startsWith("RIGHT_CLICK")) {return;}

        Player p = e.getPlayer();
        if (AdminHandler.isAdmin(p)) {return;}

        BaseItem item = BaseItem.from(e.getItem());

        if (item.matches(Items.TrackerCompass())) {
            long cl = ((cool1.getOrDefault(p.getUniqueId(), 0L) /1000)+1) - (System.currentTimeMillis()/1000);
            if (cl > 0) { return; }
            cool1.put(p.getUniqueId(), System.currentTimeMillis());

            item.removeEnchants();
            if (Trackers.containsKey(p.getUniqueId())) {
                Trackers.remove(p.getUniqueId());
            } else {
                item.addUnsafeEnchantment(Enchantment.DURABILITY,1);
                Trackers.put(p.getUniqueId(), true);
            }
        }

    }


}
