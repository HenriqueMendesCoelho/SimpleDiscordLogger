package com.discordlogger.api;

import com.discordlogger.api.config.PluginConfig;
import com.discordlogger.api.listener.OnPlayerJoinListener;
import com.discordlogger.api.listener.OnPlayerLeaveListener;
import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class SimpleDiscordLogger extends JavaPlugin {

	private FileConfiguration config;

	@Override
	public void onEnable() {
		int pluginId = 21635;
		Metrics metrics = new Metrics(this, pluginId);

		PluginConfig.initialize(this);
		getServer().getPluginManager().registerEvents(new OnPlayerJoinListener(), this);
		getServer().getPluginManager().registerEvents(new OnPlayerLeaveListener(), this);

		getLogger().info("SimpleDiscordPlugin has been enabled!");
	}

	@Override
	public void onDisable() {
		getLogger().info("SimpleDiscordPlugin has been disabled!");
	}

}
