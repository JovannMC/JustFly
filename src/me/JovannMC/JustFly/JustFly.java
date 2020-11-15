package me.JovannMC.JustFly;

import me.JovannMC.JustFly.Commands.FlyCommand;
import me.JovannMC.JustFly.Commands.JustFlyCommand;
import me.JovannMC.JustFly.Utils.Utils;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class JustFly extends JavaPlugin implements Listener {
    private File config = new File(getDataFolder() + File.separator, "config.yml");
    
    private final Utils utils = new Utils();

    @Override
    public void onEnable() {
    	configTasks();

        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("justfly").setExecutor(new JustFlyCommand());
        
        final int pluginId = 9095;
        @SuppressWarnings("unused")
		final Metrics metrics = new Metrics(this, pluginId);

    }
    
    public void onDisable() {
    	saveConfig();
    }
	
	protected void configTasks() {
		saveDefaultConfig();
    	
    	if (!getConfig().getString("ConfigVersion").equalsIgnoreCase("1")) {
    		if (getConfig().getString("ConfigVersionAction").equals("MESSAGE")) {
    			utils.error(getConfig().getString("Prefix") + " CONFIG VERSION IN CONFIG IS INVALID, PLEASE BACKUP YOUR CONFIG AND REGENERATE THE CONFIG AS SET BY THE ConfigVersionAction.");
    		} else if (getConfig().getString("ConfigVersionAction").equals("DELETE")) {
    			utils.error(getConfig().getString("Prefix") + " CONFIG VERSION INVALID, REGENERATING CONFIG AS SET BY THE ConfigVersionAction...");
        		config.delete();
        		saveDefaultConfig();
    		} else {
    			utils.error(getConfig().getString("Prefix") + " CONFIG VERSION IN CONFIG IS INVALID, PLEASE BACKUP YOUR CONFIG AND REGENERATE THE CONFIG AS SET BY THE ConfigVersionAction.");
    		}
    		
    	}
		
    	if (getConfig().getBoolean("UpdateChecker")) {
            checkForUpdates();
        }
	}

    private void checkForUpdates() {

        new UpdateChecker(this, 84407).getVersion(version -> {
            if (getDescription().getVersion().equalsIgnoreCase(version)) {
                utils.info("You are on the latest version.");
            } else {
                utils.info("You are on an outdated version. Update at https://www.spigotmc.org/resources/84407/");
            }
        });

    }

}
