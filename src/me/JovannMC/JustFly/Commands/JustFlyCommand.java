package me.JovannMC.JustFly.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import me.JovannMC.JustFly.JustFly;
import me.JovannMC.JustFly.UpdateChecker;
import me.JovannMC.JustFly.Utils.Utils;

public class JustFlyCommand implements CommandExecutor {

	private Utils utils = new Utils();

	private JavaPlugin plugin = JustFly.getPlugin(JustFly.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender.hasPermission("JustFly.use")) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("check")) {
					if (sender.hasPermission("JustFly.use.check")) {
						new UpdateChecker(plugin, 84407).getVersion(version -> {
							if (plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
								utils.message(sender, utils.color(utils.getConfigString("Prefix")) + ChatColor.GOLD
										+ " You are on the latest version.");
							} else {
								utils.message(sender, utils.color(utils.getConfigString("Prefix")) + ChatColor.GOLD
										+ " You are on an outdated version. Update at https://www.spigotmc.org/resources/84407/");
							}
						});
					} else {
						utils.message(sender, utils.color(utils.getConfigString("NoPermissionMessage")));
					}

				} else if (args[0].equalsIgnoreCase("reload")) {
					if (sender.hasPermission("JustFly.use.reload")) {
						try {
							plugin.reloadConfig();
							utils.message(sender, utils.color(utils.getConfigString("Prefix") + " &6Reloaded the config"));
						} catch (Exception e) {

							utils.message(sender, utils.color(
									"&4An error occurred while manually reloading the config, please check the console for more info."));
							utils.error(
									"An error occurred while manually reloading the config, please check the stacktrace for more info.");
							e.printStackTrace();
						}
					} else {
						utils.message(sender, utils.color(utils.getConfigString("NoPermissionMessage")));
					}

				} else {
					utils.message(sender, utils.color(utils.getConfigString("Messages.InvalidUsageMessage"))
							.replace("%usage%", "/justfly <check/reload>"));
				}
			} else {
				utils.message(sender, utils.color(utils.getConfigString("Messages.InvalidUsageMessage")).replace("%usage%",
						"/justfly <check/reload>"));
			}
		} else {
			utils.message(sender, utils.color(utils.getConfigString("NoPermissionMessage")));
		}
		return false;
	}
}
