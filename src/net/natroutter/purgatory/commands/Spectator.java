package net.natroutter.purgatory.commands;

import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.BanChecker;
import net.natroutter.purgatory.features.Spectator.SpectatorHandler;
import net.natroutter.purgatory.handlers.database.PlayerDataHandler;
import net.natroutter.purgatory.handlers.database.tables.PlayerData;
import net.natroutter.purgatory.utilities.Lang;
import net.natroutter.purgatory.utilities.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spectator extends Command {

    private static final Lang lang = Purgatory.getLang();

    static Integer cooldown = 10;

    public Spectator() {
        super("");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(lang.prefix + lang.ingameOnly);
            return false;
        }
        BasePlayer p = BasePlayer.from(sender);

        if (args.length == 0) {
            if (BanChecker.isBanned(p)) {
                p.sendMessage(lang.prefix + lang.notBannedOnly);
                return false;
            }

            PlayerData data = PlayerDataHandler.queryForID(p.getUniqueId());
            if (data==null){return false;}

            long seconds = ((data.getSpectateCooldown()/1000)+cooldown) - (System.currentTimeMillis()/1000);
            if (seconds > 0) {
                StringHandler str = new StringHandler(lang.SpectateSwitchCooldown);
                str.setPrefix(lang.prefix).replaceAll("{cooldown}", Utils.timeLeft(seconds));
                str.send(p);
                return false;
            }
            data.setSpectateCooldown(System.currentTimeMillis());

            p.getInventory().clear();
            StringHandler msg = new StringHandler(lang.SpectatorModeToggled).setPrefix(lang.prefix);
            if (data.IsSpectator()) {
                data.setSpectator(false);
                SpectatorHandler.spectatorMode(p, false);
                msg.replaceAll("{status}", lang.statues.disable);
            } else {
                data.setSpectator(true);
                SpectatorHandler.spectatorMode(p, true);
                msg.replaceAll("{status}", lang.statues.enable);
            }
            msg.send(p);
            PlayerDataHandler.updateForID(data);


        } else {
            p.sendMessage(lang.prefix + lang.ToomanyArgs);
        }
        return false;
    }
}
