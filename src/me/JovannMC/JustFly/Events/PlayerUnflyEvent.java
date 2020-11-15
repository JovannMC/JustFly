package me.JovannMC.JustFly.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerUnflyEvent extends Event implements Cancellable {
	
	private static final HandlerList HANDLERS = new HandlerList();
	
	private boolean isCancelled;
	
	private Player player;
	
	public PlayerUnflyEvent(Player eventCaller) {
		this.player = eventCaller;
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
	
	public Player getPlayer() {
		return this.player;
	}

}
