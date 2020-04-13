/*
 * Copyright (c) 2020 Sybsuper
 * All Rights Reserved
 *
 * Do not use this code without permission of the developer.
 */

package me.sybsuper.SybStopFlight.listeners;

import me.sybsuper.SybStopFlight.Main;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

public class OnShoot implements Listener {
	private final Main plugin;

	public OnShoot(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler(ignoreCancelled = true)
	public void onShootBow(EntityShootBowEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (p.getGameMode().equals(GameMode.SURVIVAL)) {
				if (plugin.config.getBoolean("event.bowShot.stopFlight")) {
					p.setFlying(false);
					if (plugin.config.getBoolean("messages")) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("event.bowShot.message")));
					}
				}
				if (plugin.config.getBoolean("event.bowShot.disableFlight")) {
					p.setAllowFlight(false);
					if (plugin.config.getBoolean("messages")) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("event.bowShot.message")));
					}
				}
			}
		}
	}
}
