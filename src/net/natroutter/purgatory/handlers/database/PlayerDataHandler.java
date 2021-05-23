package net.natroutter.purgatory.handlers.database;

import com.j256.ormlite.dao.Dao;

import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.handlers.database.Database;
import net.natroutter.purgatory.handlers.database.tables.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.sql.SQLException;
import java.util.UUID;

public class PlayerDataHandler {

    private static final Database database = Purgatory.getDatabase();
    private final static ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

    public static PlayerData queryForID(UUID uuid) {
        try {
            PlayerData data = database.getPlayerData().queryForId(uuid);
            if (data == null) {
                data = DefaultPlayerData(uuid);
                if (database.getPlayerData().create(data) == 0) {
                    console.sendMessage("§4[Purgatory][PlayerDataHandler](query) §cFailed to create new player data to database replying default!");
                }
            }
            return data;
        } catch (SQLException e) {
            console.sendMessage("§4[Purgatory][PlayerDataHandler](query) §cDatabase failure!");
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateForID(PlayerData data) {
        try {
            database.getPlayerData().createOrUpdate(data);
            return true;
        } catch (SQLException e) {
            console.sendMessage("§4[Purgatory][PlayerDataHandler](update) §cDatabase failure!");
            e.printStackTrace();
        }
        return false;
    }

    private static PlayerData DefaultPlayerData(UUID uuid) {
        return new PlayerData(uuid, 0.0, true, 0L, false);
    }


}
