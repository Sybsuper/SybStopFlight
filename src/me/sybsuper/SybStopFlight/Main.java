/*
 * Copyright (c) 2020 Sybsuper
 * All Rights Reserved
 *
 * Do not use this code without permission of the developer.
 */

package me.sybsuper.SybStopFlight;

import me.sybsuper.SybStopFlight.listeners.OnDamage;
import me.sybsuper.SybStopFlight.listeners.OnDrop;
import me.sybsuper.SybStopFlight.listeners.OnShoot;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {
	public FileConfiguration config;
	public File configFile;

	@Override
	public void onEnable() {
		config = getConfig();
		config.options().copyDefaults(true);
		saveConfig();
		Bukkit.getPluginManager().registerEvents(new OnDamage(this), this);
		Bukkit.getPluginManager().registerEvents(new OnDrop(this), this);
		Bukkit.getPluginManager().registerEvents(new OnShoot(this), this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("sybstopflight.reload")) {
			reloadConfig();
			config = getConfig();
			config.options().copyDefaults(true);
			saveConfig();
			sender.sendMessage("Successfully reloaded the config.yml file.");
		}
		return true;
	}
}