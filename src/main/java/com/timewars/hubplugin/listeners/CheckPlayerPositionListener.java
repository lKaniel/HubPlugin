package com.timewars.hubplugin.listeners;

import com.timewars.hubplugin.HubPlugin;
import com.timewars.hubplugin.util.Area;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class CheckPlayerPositionListener implements Listener {

    private HubPlugin pvPArena;


    public CheckPlayerPositionListener(HubPlugin pvPArena) {
        this.pvPArena = pvPArena;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent playerMoveEvent) {
        Player player = playerMoveEvent.getPlayer();
        for (Area area : pvPArena.getAreas()) {
            pvPArena.endSession(player);
            if (!area.inArea(player)) return;
        }
        pvPArena.startSession(player);
    }

    @EventHandler
    public void onPlayerKill(EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.getDamager() instanceof Player) return;
        if (entityDamageByEntityEvent.getEntity() instanceof Player) return;

        Player player = (Player) entityDamageByEntityEvent.getEntity();

        double damage = entityDamageByEntityEvent.getFinalDamage();

        if (player.getHealth() - damage >= 0) return;

        entityDamageByEntityEvent.setCancelled(true);
        player.setHealth(20);
        player.setInvulnerable(true);



    }

}
