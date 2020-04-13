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
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnDamage implements Listener {
	private final Main plugin;

	public OnDamage(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler(ignoreCancelled = true)
	public void onEntityDamage(EntityDamageByEntityEvent e) {
		stopFlight(e.getEntity(), "event.getDamage.stopFlight", "event.getDamage.message", "event.getDamage.disableFlight", e);
		stopFlight(e.getDamager(), "event.doDamage.stopFlight", "event.doDamage.message", "event.doDamage.disableFlight", e);
	}

	private void stopFlight(Entity damager, String s, String s2, String s3, EntityDamageByEntityEvent e) {
		if (damager instanceof Player) {
			Player d = (Player) damager;
			if (d.getGameMode().equals(GameMode.SURVIVAL)) {
				if (plugin.config.getBoolean(s)) {
					d.setFlying(false);
					if (plugin.config.getBoolean("messages")) {
						d.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString(s2)));
					}
				}
				if (plugin.config.getBoolean(s3)) {
					d.setAllowFlight(false);
					if (plugin.config.getBoolean("messages")) {
						d.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString(s2)));
					}
				}
			}
		}
	}
}
