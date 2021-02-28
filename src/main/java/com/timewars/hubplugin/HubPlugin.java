package com.timewars.hubplugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.timewars.hubplugin.commands.CreateArea;
import com.timewars.hubplugin.listeners.CheckPlayerPositionListener;
import com.timewars.hubplugin.util.Area;
import com.timewars.hubplugin.util.PlayerSession;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class HubPlugin extends JavaPlugin {

    private WorldEditPlugin worldEditPlugin;

    private PluginManager pluginManager;

    private Map<Player, PlayerSession> sessions;
    private List<Area> areas;

    @Override
    public void onEnable() {
        setupUtil();
        connectPlugins();
        registerCommands();
        registerEventListeners();
    }

    @Override
    public void onDisable() {
        sessions = null;
        areas = null;
    }

    private void setupUtil() {
        pluginManager = getServer().getPluginManager();

        sessions = new HashMap<>();
        areas = new ArrayList<>();
    }

    private void connectPlugins() {
        worldEditPlugin = (WorldEditPlugin) pluginManager.getPlugin("WorldEdit");
        if (worldEditPlugin != null) {
            pluginManager.disablePlugin(this);
        }
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("createArea")).setExecutor(new CreateArea(this));
    }

    private void registerEventListeners() {
        pluginManager.registerEvents(new CheckPlayerPositionListener(this), this);
    }

    public WorldEditPlugin getWorldEditPlugin() {
        return worldEditPlugin;
    }

    public void addArea(Area area) {
        areas.add(area);
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void startSession(Player player) {
        PlayerSession playerSession = new PlayerSession(player);
        sessions.put(player, playerSession);
        playerSession.start();
    }

    public void endSession(Player player) {
        try {
            sessions.get(player).end();
            sessions.remove(player);
        } catch (NullPointerException ignored) {

        }
    }
}
