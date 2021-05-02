package net.natroutter.purgatory.features.abilities;

import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.Material;

import java.util.List;

public class AbilityItem {

    private static final Lang lang = Purgatory.getLang();

    private String id;
    private Material mat;
    private String name;
    private List<String> lore;
    private String needed;

    public AbilityItem(String id, Material mat, String name, List<String> lore, String needed) {
        this.id = id;
        this.mat = mat;
        this.name = name;
        this.lore = lore;
        this.needed = needed;
    }

    public boolean hasNeeds() {
        return !needed.equals("none");
    }

    public BaseItem getItem() {
        BaseItem item = new BaseItem(mat);
        item.setDisplayName(name);
        item.setLore(lore);
        if (hasNeeds()) {
            StringHandler str = new StringHandler(lang.UnlockNeeds);
            str.replaceAll("{need}", needed);
            item.addLore(" ", str.build());
        }
        return item;
    }

    public String getId() {
        return id;
    }

    public Material getMaterial() {
        return mat;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }
}
