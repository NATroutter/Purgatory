package net.natroutter.purgatory.handlers.abilities.ability;

import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.handlers.abilities.Ability;
import net.natroutter.purgatory.handlers.abilities.AbilityItem;
import net.natroutter.purgatory.utilities.Lang;

public class Smokescreen extends Ability {

    private static final Lang lang = Purgatory.getLang();

    public Smokescreen(AbilityItem item, Integer cooldownSeconds) {
        super(item, cooldownSeconds);
    }

    @Override
    public void activeAbility(BasePlayer p, BasePlayer target) {

        p.sendMessage("§7Activated ability §c" + getAbilityItem().getName() + " §7to player §c" + target.getName());

    }
}
