/*
 * Copyright (c) 2020 Sybsuper
 * All Rights Reserved
 *
 * Do not use this code without permission of the developer.
 */

package me.sybsuper.SybStopFlight.listeners;

import jdk.jfr.events.ThrowablesEvent;
import me.sybsuper.SybStopFlight.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class OnDrop implements Listener {
	private Main plugin;

	public OnDrop(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler(ignoreCancelled = true)
	public void onDropItem(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (plugin.config.getBoolean("event.drop.stopFlight")) {
			p.setFlying(false);
			if (plugin.config.getBoolean("messages")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("event.drop.message")));
			}
		}
		if (plugin.config.getBoolean("event.drop.disableFlight")) {
			p.setAllowFlight(false);
			if (plugin.config.getBoolean("messages")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("event.drop.message")));
			}
		}
	}
}
