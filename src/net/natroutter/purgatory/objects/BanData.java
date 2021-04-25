package net.natroutter.purgatory.objects;

import java.util.UUID;

public class BanData {

    private Integer id;
    private UUID uuid;
    private String reason;
    private UUID banned_by_uuid;
    private String banned_by_name;
    private Long until;

    public BanData(Integer id, UUID uuid, String reason, UUID banned_by_uuid, String banned_by_name, Long until) {
        this.id = id;
        this.uuid = uuid;
        this.reason = reason;
        this.banned_by_uuid = banned_by_uuid;
        this.banned_by_name = banned_by_name;
        this.until = until;
    }

    public Boolean isPermanent() {
        if (until > -1) {
            return false;
        }
        return true;
    }

    public Long getBanSeconds() {
        return (until - System.currentTimeMillis()) / 1000;
    }

    public Integer getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getReason() {
        return reason;
    }

    public UUID getBanned_by_uuid() {
        return banned_by_uuid;
    }

    public String getBanned_by_name() {
        return banned_by_name;
    }

    public Long getUntil() {
        return until;
    }

}
