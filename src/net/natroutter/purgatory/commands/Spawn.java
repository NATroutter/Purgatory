package net.natroutter.purgatory.commands;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.Location;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn extends Command {

    private static final Lang lang = Purgatory.getLang();
    private static final YamlDatabase database = Purgatory.getYamlDatabase();

    public Spawn() {
        super("");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(lang.prefix + lang.ingameOnly);
            return false;
        }
        Player p = (Player)sender;

        if (args.length == 0) {
            if (p.hasPermission("purgatory.spawn")) {
                Location loc = database.getLocation("General", "Spawn");
                if (loc != null) {
                    p.sendMessage(lang.prefix + lang.TeleportedToSpawn);
                    p.teleport(loc);
                } else {
                    p.sendMessage(lang.prefix + lang.SpawnNotSet);
                }
            } else {
                p.sendMessage(lang.prefix + lang.noPerm);
            }
        }
        return false;
    }
}
