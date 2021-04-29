package net.natroutter.purgatory.handlers.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.natroutter.purgatory.handlers.database.tables.PlayerData;
import net.natroutter.purgatory.utilities.Config;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.UUID;

public class Database {

    private Dao<PlayerData, UUID> PlayerDao;

    private boolean valid = false;

    public boolean getValid() {
        return valid;
    }

    public Database(JavaPlugin pl, Config config) {
        ConsoleCommandSender console = pl.getServer().getConsoleSender();

        Config.Database conf = config.Database;

        if (conf.host.length() < 1 || conf.user.length() < 1 || conf.pass.length() < 1 || conf.name.length() < 1) {
            console.sendMessage("§4["+pl.getName()+"][Database] §cInvalid Database credentials you are missing host, user, pass or name");
            return;
        }

        try {
            String ConString = "jdbc:mysql://"+conf.host+":"+conf.port+"/" + conf.name;
            JdbcPooledConnectionSource source = new JdbcPooledConnectionSource(ConString);
            source.setUsername(conf.user);
            source.setPassword(conf.pass);

            TableUtils.createTableIfNotExists(source, PlayerData.class);

            PlayerDao = DaoManager.createDao(source, PlayerData.class);

            valid = true;
        } catch (Exception e) {
            console.sendMessage("§4["+pl.getName()+"][Database] §cDatabase failure");
            e.printStackTrace();
        }
    }

    public Dao<PlayerData, UUID> getPlayerData() {
        if (!valid) {return null;}
        return PlayerDao;
    }

}
