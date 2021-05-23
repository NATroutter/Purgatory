package net.natroutter.purgatory.features;

import net.milkbowl.vault.chat.Chat;
import net.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.features.bancheck.BanChecker;
import net.natroutter.purgatory.handlers.Hooks;
import net.natroutter.purgatory.utilities.Config;
import net.natroutter.purgatory.utilities.Lang;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormater implements Listener {

    private static final Hooks hooks = Purgatory.getHooks();
    private static final Lang lang = Purgatory.getLang();
    private static final Config config = Purgatory.getCfg();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        if (BanChecker.isBanned(p)) {
            p.sendMessage(lang.prefix + lang.NoPermToChat);
            e.setCancelled(true);
            return;
        }

        StringHandler message = new StringHandler(e.getMessage());
        message.replaceAll("%", "%%");

        if (p.hasPermission("purgatory.chatformat")) {
            message.replaceAll("&", "§");
        }

        String prefix = "";
        String suffix = "";
        if (hooks.vault.isHooked()) {
            Chat chat = hooks.vault.getChat();
            prefix = chat.getPlayerPrefix(p).replaceAll("&", "§");
            suffix = chat.getPlayerSuffix(p).replaceAll("&", "§");
        }

        StringHandler format = new StringHandler(config.ChatFormat);
        format.replaceAll("{prefix}", prefix);
        format.replaceAll("{displayname}", p.getDisplayName());
        format.replaceAll("{name}", p.getName());
        format.replaceAll("{suffix}", suffix);
        format.replaceAll("{message}", message.build());

        e.setFormat(format.build());
    }

}
