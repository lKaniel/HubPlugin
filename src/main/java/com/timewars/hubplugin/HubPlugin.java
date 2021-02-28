package com.timewars.hubplugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.timewars.hubplugin.util.PlayerSession;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class HubPlugin extends JavaPlugin {

    private PluginManager pluginManager;
    private WorldEditPlugin worldEditPlugin;

    private Map<Player, PlayerSession> sessions;


    @Override
    public void onEnable() {
        setupUtil();
        registerCommands();
        registerEventListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void setupUtil(){
        sessions = new HashMap<>();
    }

    private void registerCommands(){

    }

    private void registerEventListeners(){

    }









}
