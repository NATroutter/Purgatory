package net.natroutter.purgatory;

import net.natroutter.natlibs.NATLibs;
import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.handlers.EventManager;
import net.natroutter.natlibs.handlers.FileManager;
import net.natroutter.natlibs.utilities.Utilities;
import net.natroutter.purgatory.commands.Spectator;
import net.natroutter.purgatory.features.ChatFormater;
import net.natroutter.purgatory.features.Spectator.SpectatorEvents;
import net.natroutter.purgatory.features.TrackerCompass.CompassEvents;
import net.natroutter.purgatory.features.abilities.AbilityHandler;
import net.natroutter.purgatory.features.abilities.AbilityListeners;
import net.natroutter.purgatory.features.abilities.ability.Thief;
import net.natroutter.purgatory.handlers.Hooks;
import net.natroutter.purgatory.handlers.LitebansHandler;
import net.natroutter.purgatory.commands.PurgatoryCMD;
import net.natroutter.purgatory.features.bancheck.BanChecker;
import net.natroutter.purgatory.handlers.NpcHandler;
import net.natroutter.purgatory.handlers.database.Database;
import net.natroutter.purgatory.utilities.Config;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import net.natroutter.natlibs.handlers.FileManager.ConfType;

public class Purgatory extends JavaPlugin {

    private static JavaPlugin instance;
    private static Config config;
    private static Lang lang;
    private static LitebansHandler lbh;
    private static Database database;
    private static Utilities utilities;
    private static YamlDatabase yamlDatabase;
    private static Hooks hooks;

    public static JavaPlugin getInstance() { return instance; }
    public static Config getCfg() { return config; }
    public static Lang getLang() { return lang; }
    public static LitebansHandler getLitebans() {return lbh;}
    public static Database getDatabase() {return database;}
    public static Utilities getUtilities() {return utilities;}
    public static YamlDatabase getYamlDatabase() { return yamlDatabase; }
    public static Hooks getHooks() { return hooks; }

    @Override
    public void onEnable() {
        instance = this;
        NATLibs libs = new NATLibs(this);

        //Register & load configs
        FileManager cfgM = new FileManager(instance, ConfType.Config);
        config = cfgM.load(Config.class);

        //Register & load language
        FileManager langM = new FileManager(instance, ConfType.Lang);
        lang = langM.load(Lang.class);

        //Create litebans handler
        lbh = new LitebansHandler(this, config);
        database = new Database(this, config);
        utilities = new Utilities(this);
        yamlDatabase = new YamlDatabase(this);
        hooks = new Hooks(this);

        //Register abilities
        AbilityHandler.RegisterAbilities();

        //Create event manager
        EventManager evm = new EventManager(this);

        //register listeners
        evm.RegisterListeners(
                BanChecker.class,
                NpcHandler.class,
                SpectatorEvents.class,
                AbilityHandler.class,
                ChatFormater.class,
                AbilityListeners.class,
                CompassEvents.class
        );

        //register commands
        evm.RegisterCommands(
                PurgatoryCMD.class,
                Spectator.class
        );



        for (World w : Bukkit.getWorlds()) {
            w.setGameRuleValue("doInsomnia", "false");
            w.setGameRuleValue("universalAnger", "false");
            w.setGameRuleValue("spawnRadius", "0");
        }

        NpcHandler.spawnAll();

    }

    @Override
    public void onDisable() {
        NpcHandler.despawnAll();
    }
}
