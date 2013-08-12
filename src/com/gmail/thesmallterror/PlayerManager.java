package com.gmail.thesmallterror;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerManager implements Listener {

    CTT_Plugin master; // I know circular refrences suck, but whatever I have to do.
    
    public PlayerManager(CTT_Plugin master) {
        this.master = master;
    }
    
    @EventHandler
    public void normalLogin(PlayerLoginEvent event)
    {
        master.getLogger().info(String.format("%s has logged in.", event.getPlayer().getDisplayName()));
    }
    
    
}
