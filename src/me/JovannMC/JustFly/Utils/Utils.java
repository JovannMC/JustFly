package me.JovannMC.JustFly.Utils;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.JovannMC.JustFly.JustFly;

public class Utils {

	public String getConfigString(String configToPath) { return JustFly.getPlugin(JustFly.class).getConfig().getString(configToPath); }
	
	public void message(CommandSender player, String string) { player.sendMessage(string); }
	
	public void info(String message) { Bukkit.getLogger().log(Level.INFO, message); }

	public void warning(String message) { Bukkit.getLogger().log(Level.WARNING, message); }

	public void error(String message) { Bukkit.getLogger().log(Level.SEVERE, message); }
	
	public String color(String string) {
		String colored = ChatColor.translateAlternateColorCodes('&', string);
		return colored;
	}
}
