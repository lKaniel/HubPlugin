package com.timewars.hubplugin.util;

import com.sk89q.worldedit.math.BlockVector3;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;


public class Area {

    private Location maxPoint;
    private Location minPoint;
    private World world;

    public Area(BlockVector3 maxPoint, BlockVector3 minPoint, World world) {
        this.maxPoint = new Location(world, maxPoint.getBlockX(), maxPoint.getBlockY(), maxPoint.getBlockZ());
        this.minPoint = new Location(world, minPoint.getBlockX(), minPoint.getBlockY(), minPoint.getBlockZ());
        this.world = world;
    }

    public boolean inArea(Player player){
        if (!player.getWorld().equals(world)) return false;
        Location location = player.getLocation();
        if (location.getBlockX() <= maxPoint.getBlockX() && location.getBlockX() >= minPoint.getBlockX()){
            if (location.getBlockY() <= maxPoint.getBlockY() && location.getBlockY() >= minPoint.getBlockY()){
                return location.getBlockZ() <= maxPoint.getBlockZ() && location.getBlockZ() >= minPoint.getBlockZ();
            }
        }
        return false;
    }
}
