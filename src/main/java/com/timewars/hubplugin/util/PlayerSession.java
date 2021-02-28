package com.timewars.hubplugin.util;

import org.bukkit.entity.Player;

public class PlayerSession {

    private Player player;


    private boolean isActive;

    private int kills;


    public PlayerSession(Player player) {
        this.player = player;
        isActive = false;
        kills = 0;
    }

    public void start(){
        if (isActive) return;

        player.setHealth(20);
    }

    public void end(){

        player.setHealth(20);
    }
}