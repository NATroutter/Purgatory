package net.natroutter.purgatory.handlers;

import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.handlers.database.Database;
import net.natroutter.purgatory.handlers.database.PlayerDataHandler;
import net.natroutter.purgatory.handlers.database.tables.PlayerData;
import net.natroutter.purgatory.utilities.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.UUID;

public class EcoHandler {

    private final static ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    private final static Config config = Purgatory.getCfg();

    public static Double getBalance(UUID id) {
        PlayerData data = PlayerDataHandler.queryForID(id);
        if (data != null) {
            return data.getBalance();
        }
        return null;
    }

    public static boolean removeBalance(UUID id, double balance) {
        PlayerData data = PlayerDataHandler.queryForID(id);
        if (data != null) {
            double newbal = data.getBalance() - balance;
            if (newbal < 0) {newbal = 0;}
            data.setBalance(newbal);
            return PlayerDataHandler.updateForID(data);
        }
        return false;
    }

    public static boolean addBalance(UUID id, double balance) {
        PlayerData data = PlayerDataHandler.queryForID(id);
        if (data != null) {
            double newbal = data.getBalance() + balance;
            if (newbal > config.MaxBalance) { newbal = config.MaxBalance; }
            data.setBalance(newbal);
            return PlayerDataHandler.updateForID(data);
        }
        return false;
    }

    public static boolean setBalance(UUID id, double balance) {
        PlayerData data = PlayerDataHandler.queryForID(id);
        if (data != null) {
            if (balance > config.MaxBalance) { balance = config.MaxBalance; }
            data.setBalance(balance);
            return PlayerDataHandler.updateForID(data);
        }
        return false;
    }

}
