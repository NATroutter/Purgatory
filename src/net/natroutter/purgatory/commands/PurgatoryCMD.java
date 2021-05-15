package net.natroutter.purgatory.commands;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.natlibs.utilities.StringHandler;
import net.natroutter.purgatory.handlers.AdminHandler;
import net.natroutter.purgatory.handlers.EcoHandler;
import net.natroutter.purgatory.handlers.LitebansHandler;
import net.natroutter.purgatory.Purgatory;
import net.natroutter.purgatory.handlers.NpcHandler;
import net.natroutter.purgatory.handlers.database.PlayerDataHandler;
import net.natroutter.purgatory.handlers.database.tables.PlayerData;
import net.natroutter.purgatory.utilities.Config;
import net.natroutter.purgatory.utilities.Lang;
import net.natroutter.purgatory.utilities.Utils;
import org.antlr.v4.runtime.misc.NotNull;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("all")
public class PurgatoryCMD extends Command {

    private final Lang lang = Purgatory.getLang();
    private final Config config = Purgatory.getCfg();
    private final LitebansHandler litebans = Purgatory.getLitebans();
    private final YamlDatabase database = Purgatory.getYamlDatabase();

    public PurgatoryCMD() {
        super("Purgatory");
    }

    public void helpMessage(BasePlayer p) {

    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(lang.prefix + lang.ingameOnly);
            return false;
        }
        BasePlayer p = BasePlayer.from(sender);

        if (args.length == 0) {
            helpMessage(p);

        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("setspawn")) {
                if (p.hasPermission("purgatory.setspawn")) {
                    Location loc = p.getLocation();
                    loc.setX(loc.getBlockX() + 0.5);
                    loc.setZ(loc.getBlockZ() + 0.5);
                    database.saveLoc("General", "Spawn", loc);
                    p.getWorld().setSpawnLocation(p.getLocation());
                    p.sendMessage(lang.prefix + lang.SpawnSet);
                } else {
                    p.sendMessage(lang.prefix + lang.noPerm);
                }
            } else if (args[0].equalsIgnoreCase("admin")) {
                if (p.hasPermission("purgatory.admin")) {
                    AdminHandler.ToggleAdminMode(p);

                } else {
                    p.sendMessage(lang.prefix + lang.noPerm);
                }
            } else if (args[0].equalsIgnoreCase("balance")) {
                if (p.hasPermission("purgatory.balance")) {
                    StringHandler msg = new StringHandler(lang.YourBalance);
                    msg.setPrefix(lang.prefix);
                    msg.replaceAll("{balance}", EcoHandler.getBalance(p.getUniqueId()));
                    msg.send(p);
                } else {
                    p.sendMessage(lang.prefix + lang.noPerm);
                }
            } else if (args[0].equalsIgnoreCase("credits")) {
                p.sendMessage(" ");
                p.sendMessage("&c&l-----+ &4&lPurgatory &c&l+-----");
                p.sendMessage(" ");
                p.sendMessage("§8§l» §7Plugin made by: §cNATroutter");
                p.sendMessage("§8§l» §7Website: https://NATroutter.net");
                p.sendMessage(" ");
                p.sendMessage("&c&l-----+ &4&lPurgatory &c&l+-----");
                p.sendMessage(" ");

            }

        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("balance")) {

                if (p.hasPermission("purgatory.balance.other")) {
                    OfflinePlayer target = Utils.getOfflinePlayer(args[1]);
                    if (target == null) {
                        p.sendMessage(lang.prefix + lang.InvalidPlayer);
                        return false;
                    }
                    StringHandler msg = new StringHandler(lang.OtherBalance);
                    msg.setPrefix(lang.prefix);
                    msg.replaceAll("{balance}", EcoHandler.getBalance(target.getUniqueId()));
                    msg.replaceAll("{player}", target.getName());
                    msg.send(p);

                } else {
                    p.sendMessage(lang.prefix + lang.noPerm);
                }
            } else {
                p.sendMessage(lang.prefix + lang.InvalidArgs);
            }
        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase("eco")) {
                if (!p.hasPermission("purgatory.eco")) {
                    p.sendMessage(lang.prefix + lang.noPerm);
                }
                OfflinePlayer target = Utils.getOfflinePlayer(args[2]);
                if (target == null) {
                    p.sendMessage(lang.prefix + lang.InvalidPlayer);
                    return false;
                }

                if (args[1].equalsIgnoreCase("reset")) {
                    if (!p.hasPermission("purgatory.eco.reset")) {
                        p.sendMessage(lang.prefix + lang.noPerm);
                    }

                    if (EcoHandler.setBalance(target.getUniqueId(), 0)) {
                        StringHandler msg = new StringHandler(lang.BalanceReset).setPrefix(lang.prefix);
                        msg.replaceAll("{name}", target.getName());
                        msg.send(p);
                    } else {
                        p.sendMessage(lang.prefix + lang.FailedToAlterBalance);
                    }

                } else {
                    p.sendMessage(lang.prefix + lang.InvalidArgs);
                }
            } else {
                p.sendMessage(lang.prefix + lang.InvalidArgs);
            }

        } else if (args.length == 4) {

            if (args[0].equalsIgnoreCase("eco")) {
                if (!p.hasPermission("purgatory.eco")) {
                    p.sendMessage(lang.prefix + lang.noPerm);
                }
                OfflinePlayer target = Utils.getOfflinePlayer(args[2]);
                if (target == null) {
                    p.sendMessage(lang.prefix + lang.InvalidPlayer);
                    return false;
                }

                Integer amount = Utils.TryParseInt(args[3]);
                if (amount == null) {
                    p.sendMessage(lang.prefix + lang.InvalidAmount);
                    return false;
                }
                if (amount < 0) {
                    p.sendMessage(lang.prefix + lang.NoNegativeBalance);
                    return false;
                }
                if (amount > config.MaxBalance) {
                    StringHandler str = new StringHandler(lang.maximumBalance).setPrefix(lang.prefix);
                    str.replaceAll("{max}", Utils.CurrencyFormat(config.MaxBalance));
                    str.send(p);
                    return false;
                }

                if (args[1].equalsIgnoreCase("give")) {
                    if (!p.hasPermission("purgatory.eco.give")) {
                        p.sendMessage(lang.prefix + lang.noPerm);
                    }
                    if (EcoHandler.addBalance(target.getUniqueId(), amount)) {
                        StringHandler msg = new StringHandler(lang.BalanceAdded).setPrefix(lang.prefix);
                        msg.replaceAll("{balance}", Utils.CurrencyFormat(amount));
                        msg.replaceAll("{name}", target.getName());
                        msg.send(p);
                    } else {
                        p.sendMessage(lang.prefix + lang.FailedToAlterBalance);
                    }
                } else if (args[1].equalsIgnoreCase("take")) {
                    if (!p.hasPermission("purgatory.eco.take")) {
                        p.sendMessage(lang.prefix + lang.noPerm);
                    }
                    if (EcoHandler.removeBalance(target.getUniqueId(), amount)) {
                        StringHandler msg = new StringHandler(lang.BalanceRemoved).setPrefix(lang.prefix);
                        msg.replaceAll("{balance}", Utils.CurrencyFormat(amount));
                        msg.replaceAll("{name}", target.getName());
                        msg.send(p);
                    } else {
                        p.sendMessage(lang.prefix + lang.FailedToAlterBalance);
                    }

                } else if (args[1].equalsIgnoreCase("set")) {
                    if (!p.hasPermission("purgatory.eco.set")) {
                        p.sendMessage(lang.prefix + lang.noPerm);
                    }
                    if (EcoHandler.setBalance(target.getUniqueId(), amount)) {
                        StringHandler msg = new StringHandler(lang.BalanceSet).setPrefix(lang.prefix);
                        msg.replaceAll("{balance}", Utils.CurrencyFormat(amount));
                        msg.replaceAll("{name}", target.getName());
                        msg.send(p);
                    } else {
                        p.sendMessage(lang.prefix + lang.FailedToAlterBalance);
                    }
                } else {
                    p.sendMessage(lang.prefix + lang.InvalidArgs);
                }
            } else {
                p.sendMessage(lang.prefix + lang.InvalidArgs);
            }
        } else {
            p.sendMessage(lang.prefix + lang.ToomanyArgs);
        }
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {

        if (args.length == 1) {

            ArrayList<String> firstArgs = new ArrayList<>();
            firstArgs.add("credits");
            if (sender.hasPermission("purgatory.eco")) {
                firstArgs.add("eco");
            }
            if (sender.hasPermission("purgatory.setspawn")) {
                firstArgs.add("setspawn");
            }
            if (sender.hasPermission("purgatory.admin")) {
                firstArgs.add("admin");
            }
            if (sender.hasPermission("purgatory.balance")) {
                firstArgs.add("balance");
            }

            List<String> shorted = new ArrayList<>();
            StringUtil.copyPartialMatches(args[0], firstArgs, shorted);
            Collections.sort(shorted);
            return shorted;

        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("eco")) {
                if (sender.hasPermission("purgatory.eco")) {
                    ArrayList<String> EcoArgs = new ArrayList<>();

                    if (sender.hasPermission("purgatory.eco.give")) {
                        EcoArgs.add("give");
                    }
                    if (sender.hasPermission("purgatory.eco.take")) {
                        EcoArgs.add("take");
                    }
                    if (sender.hasPermission("purgatory.eco.set")) {
                        EcoArgs.add("set");
                    }

                    List<String> shorted = new ArrayList<>();
                    StringUtil.copyPartialMatches(args[1], EcoArgs, shorted);
                    Collections.sort(shorted);
                    return shorted;
                }
            } else if (args[0].equalsIgnoreCase("balance")) {
                if (sender.hasPermission("purgatory.balance.other")) {
                    return Utils.playerNameList();
                }
            }
        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase("eco")) {

                if (args[1].equalsIgnoreCase("give")) {
                    if (sender.hasPermission("purgatory.eco.give")) {
                        return Utils.playerNameList();
                    }
                } else if (args[1].equalsIgnoreCase("take")) {
                    if (sender.hasPermission("purgatory.eco.take")) {
                        return Utils.playerNameList();
                    }
                } else if (args[1].equalsIgnoreCase("set")) {
                    if (sender.hasPermission("purgatory.eco.set")) {
                        return Utils.playerNameList();
                    }
                }
            }
        }  else if (args.length == 4) {
            if (args[0].equalsIgnoreCase("eco")) {
                if (args[1].equalsIgnoreCase("give")) {
                    if (sender.hasPermission("purgatory.eco.give")) {
                        return Collections.singletonList("<amount>");
                    }
                } else if (args[1].equalsIgnoreCase("take")) {
                    if (sender.hasPermission("purgatory.eco.take")) {
                        return Collections.singletonList("<amount>");
                    }
                } else if (args[1].equalsIgnoreCase("set")) {
                    if (sender.hasPermission("purgatory.eco.set")) {
                        return Collections.singletonList("<amount>");
                    }
                }
            }
        }
        return null;
    }
}
