package plugin.simplediscordlogger.api;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.simplediscordlogger.api.listener.OnPlayerJoinListener;
import plugin.simplediscordlogger.api.listener.OnPlayerLeaveListener;

import java.io.File;

@Getter
public class SimpleDiscordLogger extends JavaPlugin {

	private FileConfiguration config;

	@Override
	public void onEnable() {
		loadConfig();

		getLogger().info("SimpleDiscordPlugin has been enabled!");

		String url = getConfig().getString("discord.webhook_url");
		getServer().getPluginManager().registerEvents(new OnPlayerJoinListener(url), this);
		getServer().getPluginManager().registerEvents(new OnPlayerLeaveListener(url), this);
	}

	@Override
	public void onDisable() {
		getLogger().info("SimpleDiscordPlugin has been disabled!");
	}

	private void loadConfig() {
		saveDefaultConfig();

		if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}

		File configFile = new File(getDataFolder(), "config.yml");

		if (!configFile.exists()) {
			saveResource("config.yml", false);
		}

		config = YamlConfiguration.loadConfiguration(configFile);
	}

}
