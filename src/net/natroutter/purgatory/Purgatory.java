package net.natroutter.purgatory;

import net.natroutter.natlibs.NATLibs;
import net.natroutter.natlibs.handlers.EventManager;
import net.natroutter.natlibs.handlers.FileManager;
import net.natroutter.natlibs.utilities.Utilities;
import net.natroutter.purgatory.commands.Spectator;
import net.natroutter.purgatory.features.Spectator.SpectatorHandler;
import net.natroutter.purgatory.features.Spectator.SpectatorEvents;
import net.natroutter.purgatory.handlers.LitebansHandler;
import net.natroutter.purgatory.handlers.NpcHandler;
import net.natroutter.purgatory.commands.PurgatoryCMD;
import net.natroutter.purgatory.features.BanChecker;
import net.natroutter.purgatory.handlers.abilities.AbilityHandler;
import net.natroutter.purgatory.handlers.database.Database;
import net.natroutter.purgatory.utilities.Config;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.plugin.java.JavaPlugin;
import net.natroutter.natlibs.handlers.FileManager.ConfType;

public class Purgatory extends JavaPlugin {

    private static JavaPlugin instance;
    private static Config config;
    private static Lang lang;
    private static LitebansHandler lbh;
    private static Database database;
    private static Utilities utilities;

    public static JavaPlugin getInstance() { return instance; }
    public static Config getCfg() { return config; }
    public static Lang getLang() { return lang; }
    public static LitebansHandler getLitebans() {return lbh;}
    public static Database getDatabase() {return database;}
    public static Utilities getUtilities() {return utilities;}

    @Override
    public void onEnable() {
        instance = this;

        NATLibs libs = new NATLibs(this);

        //Register & load configs
        FileManager cfgM = new FileManager(this, ConfType.Config);
        config = cfgM.load(Config.class);

        //Register & load language
        FileManager langM = new FileManager(this, ConfType.Lang);
        lang = langM.load(Lang.class);

        //Create litebans handler
        lbh = new LitebansHandler(this, config);
        database = new Database(this, config);
        utilities = new Utilities(this);

        //Register abilities
        AbilityHandler.RegisterAbilities();

        //Create event manager
        EventManager evm = new EventManager(this);

        //register listeners
        evm.RegisterListeners(
                BanChecker.class,
                NpcHandler.class,
                SpectatorEvents.class,
                AbilityHandler.class
        );

        //register commands
        evm.RegisterCommands(
                PurgatoryCMD.class,
                Spectator.class
        );

        NpcHandler.spawnAll();

    }

    @Override
    public void onDisable() {
        NpcHandler.despawnAll();
    }
}
