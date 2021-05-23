package net.natroutter.purgatory.features.abilities;

import org.bukkit.entity.Player;

public abstract class Ability {

    private AbilityItem item;
    private Integer cooldownSeconds;
    private String permission;

    public AbilityItem getAbilityItem(){ return item; }
    public long getCooldownSeconds() { return cooldownSeconds; }
    public String getPermission() { return permission; }

    public Ability(AbilityItem item, Integer cooldownSeconds, String permission) {
        this.item = item;
        this.cooldownSeconds = cooldownSeconds;
        this.permission = permission;
    }

    public abstract void activeAbility(Player p, Player target);


}
