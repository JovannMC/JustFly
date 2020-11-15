package me.JovannMC.JustFly.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.JovannMC.JustFly.Events.PlayerFlyAdminEvent;
import me.JovannMC.JustFly.Events.PlayerFlyEvent;
import me.JovannMC.JustFly.Events.PlayerUnflyAdminEvent;
import me.JovannMC.JustFly.Events.PlayerUnflyEvent;
import me.JovannMC.JustFly.Utils.Utils;

public class FlyCommand implements CommandExecutor {

	private Utils utils = new Utils();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			if (args.length == 0) {
				if (sender.hasPermission("JustFly.fly")) {
					if (((Player) sender).getAllowFlight() == false) {

						PlayerFlyEvent playerFlyEvent = new PlayerFlyEvent((Player) sender);
						Bukkit.getPluginManager().callEvent(playerFlyEvent);

						if (!playerFlyEvent.isCancelled()) {
							((Player) sender).setAllowFlight(true);
							utils.message(sender, utils.color(utils.getConfigString("Prefix")) + " "
									+ utils.color(utils.getConfigString("Messages.FlightEnabledMessage")));
						}
					} else {

						PlayerUnflyEvent playerUnflyEvent = new PlayerUnflyEvent((Player) sender);
						Bukkit.getPluginManager().callEvent(playerUnflyEvent);

						if (!playerUnflyEvent.isCancelled()) {
							((Player) sender).setAllowFlight(false);
							utils.message(sender, utils.color(utils.getConfigString("Prefix")) + " "
									+ utils.color(utils.getConfigString("Messages.FlightDisabledMessage")));
						}
					}
				} else {
					utils.message(sender, utils.color(utils.getConfigString("NoPermissionMessage")));
				}

			} else if (args.length == 1) {
				Player player = Bukkit.getPlayerExact(args[0].toString());

				if (player != null && player.isOnline()) {
					if (player.getAllowFlight()) {
						if (sender.hasPermission("JustFly.fly.others.disableFly")) {

							PlayerUnflyAdminEvent playerUnflyAdminEvent = new PlayerUnflyAdminEvent((Player) sender,
									player);
							Bukkit.getPluginManager().callEvent(playerUnflyAdminEvent);

							if (!playerUnflyAdminEvent.isCancelled()) {
								utils.message(sender, utils.color(utils.getConfigString("Prefix")) + " "
										+ utils.color(utils.getConfigString("Messages.SetOtherFlightOffMessage").replace("%player%", player.getName())));
								utils.message(player, utils.color(utils.getConfigString("Prefix")) + " "
										+ utils.color(utils.getConfigString("Messages.FlightOffByAdminMessage").replace("%player%", player.getName())));
								player.setAllowFlight(false);
							}
						} else {
							utils.message(sender, utils.color(utils.getConfigString("NoPermissionMessage")));
						}
					} else {

						if (sender.hasPermission("JustFly.fly.others.enableFly")) {

							PlayerFlyAdminEvent playerFlyAdminEvent = new PlayerFlyAdminEvent((Player) sender, player);
							Bukkit.getPluginManager().callEvent(playerFlyAdminEvent);

							if (!playerFlyAdminEvent.isCancelled()) {
								utils.message(sender, utils.color(utils.getConfigString("Prefix")) + " "
										+ utils.color(utils.getConfigString("Messages.SetOtherFlightOnMessage").replace("%player%", player.getName())));
								utils.message(player, utils.color(utils.getConfigString("Prefix")) + " "
										+ utils.color(utils.getConfigString("Messages.FlightOnByAdminMessage").replace("%player%", player.getName())));
								player.setAllowFlight(true);
							}
						} else {
							utils.message(sender, utils.color(utils.getConfigString("NoPermissionMessage")));
						}
					}

				} else {
					utils.message(sender, utils.color(utils.getConfigString("Prefix")) + " "
							+ utils.color(utils.getConfigString("Messages.PlayerNotValidMessage")));
				}
			} else if (args.length == 2) {
				if (args[1].equalsIgnoreCase("check") || args[1].equalsIgnoreCase("c")) {
					if (sender.hasPermission("JustFly.fly.check")) {
						Player target = Bukkit.getPlayerExact(args[0].toString());

						if (target != null) {
							if (target.getAllowFlight()) {
								utils.message(sender, utils.color(utils.getConfigString("Prefix")) + " " +
										utils.color(utils.getConfigString("Messages.FlightCheckCanFlyMessage")
												.replace("%player%", target.getName())));
							} else {
								utils.message(sender, utils.color(utils.getConfigString("Prefix")) + " " +
										utils.color(utils.getConfigString("Messages.FlightCheckCanNotFlyMessage")
												.replace("%player%", target.getName())));
							}
						}
					} else {
						utils.message(sender, utils.color(utils.getConfigString("NoPermissionMessage")));
					}
				}
			} else {
				utils.message(sender, utils.color(utils.getConfigString("Messages.InvalidUsageMessage"))
						.replace("%usage%", "/fly [player] [c/check]"));
			}
		} else {
			utils.info(utils.color(utils.getConfigString("Messages.CommandInConsoleMessage")));
		}
		return false;
	}

}
