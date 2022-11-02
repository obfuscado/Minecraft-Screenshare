package com.github.thikerblow.screenshare.events;

import com.github.thikerblow.screenshare.commands.Screenshare;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEventListener implements Listener {

    public PlayerEventListener() {
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (!Screenshare.isMember(event.getPlayer())) {
            return;
        }

        Player p = event.getPlayer();

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("thikerblow.screenshare.permission") || player.isOp()) {
                player.sendMessage("§cThe player §b" + p.getName() + "§c has logged out in screenshare");
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        Player player = ((Player) event.getDamager()).getPlayer();

        if (player == null) {
            return;
        }

        if (!Screenshare.isMember(player)) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (!Screenshare.isMember(event.getPlayer())) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!Screenshare.isMember(event.getPlayer())) {
            return;
        }

        Player player = event.getPlayer();

        if (Screenshare.isMember(player)) {
            event.setCancelled(true);
        }
    }
}
