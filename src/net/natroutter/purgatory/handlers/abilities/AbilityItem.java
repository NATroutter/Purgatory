package net.natroutter.purgatory.handlers.abilities;

import net.natroutter.natlibs.objects.BaseItem;
import org.bukkit.Material;

import java.util.List;

public class AbilityItem {

    private String id;
    private Material mat;
    private String name;
    private List<String> lore;

    public AbilityItem(String id, Material mat, String name, List<String> lore) {
        this.id = id;
        this.mat = mat;
        this.name = name;
        this.lore = lore;
    }

    public BaseItem getItem() {
        BaseItem item = new BaseItem(mat);
        item.setDisplayName(name);
        item.setLore(lore);
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
