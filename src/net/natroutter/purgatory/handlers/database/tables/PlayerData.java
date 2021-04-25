package net.natroutter.purgatory.handlers.database.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.UUID;

@DatabaseTable(tableName = "PlayerData")
public class PlayerData {

    @DatabaseField(canBeNull = false, id = true)
    public UUID uuid;

    @DatabaseField(canBeNull = false)
    public Double balance;

    public PlayerData() {}
    public PlayerData(UUID uuid, Double balance) {
        this.uuid = uuid;
        this.balance = balance;
    }

    public UUID getUUID() {
        return uuid;
    }

    public Double getBalance() {
        return balance;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
