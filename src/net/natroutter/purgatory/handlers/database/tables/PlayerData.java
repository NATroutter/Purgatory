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

    @DatabaseField(canBeNull = false)
    public Boolean isSpectator;

    @DatabaseField(canBeNull = false)
    public Long spectateCooldown;

    public PlayerData() {}
    public PlayerData(UUID uuid, Double balance, Boolean isSpectator, Long spectateCooldown) {
        this.uuid = uuid;
        this.balance = balance;
        this.isSpectator = isSpectator;
        this.spectateCooldown = spectateCooldown;
    }

    public UUID getUUID() {
        return uuid;
    }
    public Double getBalance() {
        return balance;
    }
    public Boolean IsSpectator() { return isSpectator; }
    public Long getSpectateCooldown() { return spectateCooldown; }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setSpectator(boolean state) {this.isSpectator = state;}
    public void setSpectateCooldown(long value) {this.spectateCooldown = value;}

}
