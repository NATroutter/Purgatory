package net.natroutter.purgatory.handlers;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.natroutter.purgatory.objects.BanData;
import net.natroutter.purgatory.utilities.Config;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class LitebansHandler {

    private Config config;
    private HikariConfig hikConfig;
    private HikariDataSource hikData;
    private JavaPlugin pl;
    private ConsoleCommandSender console;
    private boolean valid = false;

    public LitebansHandler(JavaPlugin pl, Config config) {
        this.pl = pl;
        this.config = config;
        this.console = pl.getServer().getConsoleSender();

        Config.LitebansDB db = config.litebansDB;

        if (db.host.length() < 1 || db.user.length() < 1 || db.pass.length() < 1 || db.name.length() < 1) {
            console.sendMessage("§4["+pl.getName()+"][LitebansHandler] §cInvalid Database credentials you are missing host, user, pass or name");
            return;
        }

        hikConfig = new HikariConfig();
        hikConfig.setJdbcUrl("jdbc:mysql://"+config.litebansDB.host+":"+config.litebansDB.port+"/" + config.litebansDB.name);
        hikConfig.setUsername(config.litebansDB.user);
        if (config.litebansDB.pass.length() > 1) {
            hikConfig.setPassword(config.litebansDB.pass);
        }
        hikConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikConfig.setPoolName("LiteBansHandler");

        hikData = new HikariDataSource(hikConfig);
        valid = true;

    }

    public boolean shortenBan(UUID uuid, long newTime) {
        if (!valid) {return false;}

        String sql = "UPDATE " + config.litebansDB.prefix + "bans SET until=? WHERE uuid=? AND active=1";

        try (Connection con = hikData.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
            st.setLong(1, newTime);
            st.setString(2, uuid.toString());
            return st.executeUpdate() > 0;
        } catch (Exception e) {
            console.sendMessage("§4["+pl.getName()+"][LitebansHandler] §cFailed to retrive data from database for UUID: " + uuid.toString());
            e.printStackTrace();
        }
        return false;
    }

    public boolean unBan(UUID uuid) {
        if (!valid) {return false;}

        String sql = "UPDATE " + config.litebansDB.prefix + "bans SET removed_by_name=?, removed_by_date=?, active=? WHERE uuid=? AND active=1";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try (Connection con = hikData.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, config.BanRemover);
            st.setString(2, sdf.format(new Date()));
            st.setInt(3, 0);
            st.setString(4, uuid.toString());
            return st.executeUpdate() > 0;
        } catch (Exception e) {
            console.sendMessage("§4["+pl.getName()+"][LitebansHandler] §cFailed to retrive data from database for UUID: " + uuid.toString());
            e.printStackTrace();
        }
        return false;
    }

    public BanData getBan(UUID uuid) {
        if (!valid) {return null;}

        String sql = "SELECT * FROM " + config.litebansDB.prefix + "bans WHERE uuid=? AND active=1";

        try (Connection con = hikData.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, uuid.toString());
            ResultSet rs = st.executeQuery();

            if (!rs.next()) { return null; }

            return new BanData(
                    rs.getInt("id"),
                    uuid,
                    rs.getString("reason"),
                    UUID.fromString(rs.getString("banned_by_uuid")),
                    rs.getString("banned_by_name"),
                    rs.getLong("until")
            );
        } catch (Exception e) {
            console.sendMessage("§4["+pl.getName()+"][LitebansHandler] §cFailed to retrive data from database for UUID: " + uuid.toString());
            e.printStackTrace();
        }
        return null;
    }

}
