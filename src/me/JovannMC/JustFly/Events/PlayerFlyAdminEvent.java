package me.JovannMC.JustFly.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerFlyAdminEvent extends Event implements Cancellable {
	
	private static final HandlerList HANDLERS = new HandlerList();
	
	private boolean isCancelled;
	
	private Player admin;
	
	private Player target;
	
	public PlayerFlyAdminEvent(Player admin, Player target) {
		this.admin = admin;
		this.target = target;
	}

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

	public boolean isCancelled() {
		
		return this.isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }
	
	public Player getAdmin() {
		return this.admin;
	}
	
	public Player getTarget() {
		return this.target;
	}

}
