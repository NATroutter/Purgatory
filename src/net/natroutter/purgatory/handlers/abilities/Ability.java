package net.natroutter.purgatory.handlers.abilities;

import net.natroutter.natlibs.objects.BasePlayer;

public abstract class Ability {

    private AbilityItem item;
    private Integer cooldownSeconds;

    public AbilityItem getAbilityItem(){ return item; }
    public long getCooldownSeconds() { return cooldownSeconds; }

    public Ability(AbilityItem item, Integer cooldownSeconds) {
        this.item = item;
        this.cooldownSeconds = cooldownSeconds;
    }

    public abstract void activeAbility(BasePlayer p, BasePlayer target);


}
