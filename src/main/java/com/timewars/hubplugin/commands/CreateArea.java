package com.timewars.hubplugin.commands;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.timewars.hubplugin.HubPlugin;
import com.timewars.hubplugin.util.Area;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CreateArea implements TabExecutor {

    private HubPlugin pvPArena;

    public CreateArea(HubPlugin pvPArena) {
        this.pvPArena = pvPArena;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        World world = player.getWorld();

        LocalSession playerSession = pvPArena.getWorldEditPlugin().getSession(player);
        List<Location> locations = new ArrayList<>();
        try {
            Area area = new Area(
                    playerSession.getRegionSelector(playerSession.getSelectionWorld()).getRegion().getMaximumPoint(),
                    playerSession.getRegionSelector(playerSession.getSelectionWorld()).getRegion().getMinimumPoint(),
                    world
            );
            pvPArena.addArea(area);
            player.sendMessage(ChatColor.GREEN + "You successfully created area!");
            return true;
        } catch (IncompleteRegionException e) {
            player.sendMessage(ChatColor.RED + "You have to select a region before creating area!");
            return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completionList = new ArrayList<>();


        return completionList;
    }
}
