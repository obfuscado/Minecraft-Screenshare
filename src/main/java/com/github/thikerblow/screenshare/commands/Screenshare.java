package com.github.thikerblow.screenshare.commands;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Screenshare {

    @Getter
    private static final List<UUID> playersUUID = new ArrayList<>();

    public Screenshare() {

    }

    public static boolean isMember(Player player) {
        return playersUUID.contains(player.getUniqueId());
    }

    public static void removeFrom(Player player) {
        playersUUID.remove(player.getUniqueId());
    }

    public static void addOn(Player player) {
        playersUUID.add(player.getUniqueId());
    }
}
