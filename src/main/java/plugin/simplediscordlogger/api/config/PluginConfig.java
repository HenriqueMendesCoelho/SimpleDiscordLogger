package plugin.simplediscordlogger.api.config;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.simplediscordlogger.util.StringUtils;

import java.io.File;

@AllArgsConstructor
public class PluginConfig {
	private static PluginConfig instance;
	private FileConfiguration config;

	public static void initialize(JavaPlugin plugin) {
		if (instance == null) {
			instance = new PluginConfig(plugin.getConfig());
			instance.loadConfig(plugin);
		}
	}

	public static PluginConfig getInstance() {
		if (instance == null) {
			throw new IllegalStateException("PluginConfig has not been initialized");
		}
		return instance;
	}

	public boolean hasErrors() {
		if (StringUtils.isEmpty(getWebhookUrl()) || StringUtils.equals(getWebhookUrl(), "discordwebhookurl")) {
			Bukkit.getLogger().severe("Discord Webhook URL is not set in the config.yml file!");
			return true;
		}

		return false;
	}

	private void loadConfig(JavaPlugin plugin) {
		plugin.saveDefaultConfig();

		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdirs();
		}

		File configFile = new File(plugin.getDataFolder(), "config.yml");

		if (!configFile.exists()) {
			plugin.saveResource("config.yml", false);
		}

		config = YamlConfiguration.loadConfiguration(configFile);
	}

	public String getWebhookUrl() {
		return config.getString("discord.webhook_url");
	}

	public String getPlayerJoinMessage() {
		return config.getString("settings.player_join_message");
	}

	public String getPlayerLeaveMessage() {
		return config.getString("settings.player_leave_message");
	}
}
