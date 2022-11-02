package com.github.thikerblow.screenshare;

import com.github.thikerblow.screenshare.commands.ScreenshareCommandExecutor;
import com.github.thikerblow.screenshare.events.PlayerEventListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Plugin extends JavaPlugin {

    @Getter Plugin plugin;

    @Override
    public void onEnable() {
        loadCommands();
        loadEvents();
        Bukkit.getConsoleSender().sendMessage("§aScreenshare plugin enabled");
    }


    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§cScreenshare plugin disabled");
    }

    public void loadCommands() {
        Objects.requireNonNull(getCommand("ss")).setExecutor(new ScreenshareCommandExecutor());
    }

    public void loadEvents() {
        Bukkit.getPluginManager().registerEvents(new PlayerEventListener(), this);
    }
}
