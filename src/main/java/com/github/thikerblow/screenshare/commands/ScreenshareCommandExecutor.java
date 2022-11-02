package com.github.thikerblow.screenshare.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ScreenshareCommandExecutor implements CommandExecutor {

    public ScreenshareCommandExecutor() {
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can execute this command.");
            return false;
        }
        Player player = (Player) sender;

        if (!player.isOp() || !player.hasPermission("thikerblow.screenshare.permission")) {
            player.sendMessage("§cYou don't have permission to execute this command.");
            return false;
        }

        // ss <freeze/unfreeze> <player>
        if (args.length != 2) {
            sender.sendMessage("§cIncorrect syntax!\nUsage: /ss <mode> <player>");
            return false;
        }

        Player target = Bukkit.getPlayerExact(args[1]);
        String action = args[0];

        if (target == null) {
            sender.sendMessage("§cOffline or invalid player.");
            return false;
        }

        if (target.isOp() || target.hasPermission("thikerblow.screenshare.permission")) {
            player.sendMessage("§cYou cannot screenshare a staff member.");
            return false;
        }

        if (!action.equalsIgnoreCase("freeze") && !action.equalsIgnoreCase("unfreeze")) {
            player.sendMessage("§cAvailable screenshare modes: §b/screenshare <freeze> <player> || /screenshare <unfreeze> player");
            return false;
        }

        if (action.equalsIgnoreCase("freeze")) {
            Screenshare.addOn(target);

            player.sendMessage("§aYou froze the player §b" + target.getName());

            for (int i = 0; i < 6; i++) {
                target.sendMessage("§cYou have been frozen, please download: https://anydesk.com");
            }

            return true;
        }

        if (action.equalsIgnoreCase("unfreeze")) {

            if (!Screenshare.isMember(target)) {
                player.sendMessage("§cThis player aren't in screenshare.");
                return false;
            }

            player.sendMessage("§aYou have unfreeze the player §b" + target.getName());
            target.sendMessage("§aYou are unfreeze. Thanks for collaborating with the team. :)");
            Screenshare.removeFrom(target);
            return true;
        }

        return true;
    }
}
