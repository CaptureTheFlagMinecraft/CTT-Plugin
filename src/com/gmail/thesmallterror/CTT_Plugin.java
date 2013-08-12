package com.gmail.thesmallterror;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class CTT_Plugin extends JavaPlugin {
    @Override
    public void onEnable(){
    	getLogger().info("CTT is ready");
    }
 
    @Override
    public void onDisable() {
    	getLogger().info("CTT is going offline");
    }
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("startctt")) {
			
			return true;
		}
		return false;
	}

}
