package com.gmail.thesmallterror;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
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
    public void worldLoadEvent(World world)
    {

    }
    
    @EventHandler
    public void normalLogin(PlayerLoginEvent event)
    {
        master.getLogger().info(String.format("%s has logged in.", event.getPlayer().getDisplayName()));
       
        
    }
    
    
    final static Vector redBottom =  new Vector(-93, 17, 3);
    final static Vector redTop = new Vector(-89, 19, 13);
    
    final static Vector blueBottom =  new Vector(-17, 17, 3);
    final static Vector blueTop = new Vector(-14, 19, 13);
    
    public void startGame()
    {
        
        
        Player[] players = master.getServer().getOnlinePlayers();
        
        for (Player play : players)
        {
            if (isWithin(play, redBottom, redTop))
                master.getLogger().info(play.getName() + " is in the read base");
            else if (isWithin(play,blueBottom,blueTop))
                master.getLogger().info(play.getName() + " is in the blue base");
            else
                master.getLogger().info(play.getName() + " is not playing");
        }
        
    }





    private boolean isWithin(Player player, Vector bottom, Vector top) {
        Location loc = player.getLocation();
        return (bottom.getX() <= loc.getX() && loc.getX() <= top.getX() && 
            bottom.getY() <= loc.getY() && loc.getY() <= top.getY() &&
            bottom.getZ() <= loc.getZ() && loc.getZ() <= top.getZ());
        
    }
    
}
