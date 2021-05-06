package net.natroutter.purgatory.handlers;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Hooks {

    public WorldGuard_Hook worldguard;
    public vault_Hook vault;

    public Hooks(JavaPlugin pl) {
        worldguard = new WorldGuard_Hook(pl, new Hook("WorldGuard"));
        vault = new vault_Hook(pl, new Hook("Vault"));

    }

    public static class WorldGuard_Hook {
        private final Hook hook;
        private WorldGuardPlugin WGPL;
        public WorldGuard_Hook(JavaPlugin pl, Hook hook) {
            this.hook = hook;
            if (hook.getHooked()) {
                WGPL = (WorldGuardPlugin)hook.getPlugin();
            }
        }

        public boolean isHooked() { return hook.Hooked; }
        public WorldGuardPlugin getPlugin() { return WGPL; }
    }

    public static class vault_Hook {
        private final Hook hook;
        private Chat chat;
        public vault_Hook(JavaPlugin pl, Hook hook) {
            this.hook = hook;
            if (hook.Hooked) {
                RegisteredServiceProvider<Chat> chatProvider = pl.getServer().getServicesManager().getRegistration(Chat.class);
                if (chatProvider != null) {
                    this.chat = chatProvider.getProvider();
                    this.hook.Hooked = true;
                } else {
                    this.hook.Hooked = false;
                }
            }
        }

        public boolean isHooked() { return hook.Hooked; }
        public Chat getChat() { return chat; }
    }


    private static class Hook {
        private Plugin plugin = null;
        private boolean Hooked = false;

        public Plugin getPlugin() {return plugin;}
        public boolean getHooked() {return Hooked;}

        public Hook(String name) {
            Plugin HookPL = Bukkit.getServer().getPluginManager().getPlugin(name);
            ConsoleCommandSender console = Bukkit.getConsoleSender();
            if (HookPL != null && HookPL.isEnabled()) {
                this.plugin = HookPL;
                this.Hooked = true;
                console.sendMessage("§4[Purgatory] §c" + plugin.getName() + " §7hooked succesfully!");
            } else {
                console.sendMessage("§4[Purgatory] §c" + name + " §7hooking failed!");
            }
        }
    }

}