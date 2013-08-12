package com.gmail.thesmallterror;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Wool;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.Vector;



public class PlayerManager implements Listener {

    CTT_Plugin master; // I know circular references suck, but whatever I have to do.
    
    Scoreboard mainScoreBoard;
    
    
    
    public PlayerManager(CTT_Plugin master) {
        this.master = master;
//        mainScoreBoard = master.getServer().getScoreboardManager().getMainScoreboard();
//        
//        
//        for (Objective o : mainScoreBoard.getObjectives())
//        {
//            o.unregister();
        
//        }
        
        
        
        
        
    }
    
  
    
    
    
    
    
    @EventHandler
    public void normalLogin(PlayerLoginEvent event)
    {
        master.getLogger().info(String.format("%s has logged in.", event.getPlayer().getDisplayName()));
       
        
    }
    
    @EventHandler
    public void playerDropItemEvent(PlayerDropItemEvent event)
    {
        master.getServer().broadcastMessage("Sometime was dropped");
        
        
    }
    
    
    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent event)
    {
        master.getServer().broadcastMessage(event.getEntity().getDisplayName() + "has died");
        
        for (Iterator<ItemStack> it = event.getDrops().iterator(); it.hasNext();)
        {
            ItemStack drop = it.next();
            
            if (drop.getTypeId() == Material.WOOL.getId())
                it.remove();
                
        }
    }
    
    final static Vector redBottom =  new Vector(-93, 17, 3);
    final static Vector redTop = new Vector(-89, 19, 13);
    
    final static Vector blueBottom =  new Vector(-17, 17, 3);
    final static Vector blueTop = new Vector(-14, 19, 13);
    
    ArrayList<Player> bluePlayers = new ArrayList<>();
    ArrayList<Player> redPlayers = new ArrayList<>();
    
    
    public void startGame()
    {
        
        
        Player[] players = master.getServer().getOnlinePlayers();
        
        redPlayers.clear();
        bluePlayers.clear();
        
        for (Player play : players)
        {
            if (isWithin(play, redBottom, redTop))
            {
                master.getServer().broadcastMessage(play.getName() + " is in the " + ChatColor.RED + "red base");
                redPlayers.add(play);
                setHatColor(play, DyeColor.RED);
            }
            else if (isWithin(play,blueBottom,blueTop))
            {
                master.getServer().broadcastMessage(play.getName() + " is in the " + ChatColor.BLUE + "blue base");
                bluePlayers.add(play);
                setHatColor(play, DyeColor.BLUE);
            }
            else
            {
                master.getServer().broadcastMessage(play.getName() + " is not playing");
            }
            
            
            
            
        }
        
    }



    
    


    private void setHatColor(Player player, DyeColor color) {
        PlayerInventory inv = player.getInventory();
        Wool f = new Wool(color);
        inv.setHelmet(f.toItemStack());
    }





    private boolean isWithin(Player player, Vector bottom, Vector top) {
        Location loc = player.getLocation();
        return (bottom.getX() <= loc.getX() && loc.getX() <= top.getX() && 
            bottom.getY() <= loc.getY() && loc.getY() <= top.getY() &&
            bottom.getZ() <= loc.getZ() && loc.getZ() <= top.getZ());
        
    }
    
}
