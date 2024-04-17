package plugin.simplediscordlogger.api;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.simplediscordlogger.api.config.PluginConfig;
import plugin.simplediscordlogger.api.listener.OnPlayerJoinListener;
import plugin.simplediscordlogger.api.listener.OnPlayerLeaveListener;

@Getter
public class SimpleDiscordLogger extends JavaPlugin {

	private FileConfiguration config;

	@Override
	public void onEnable() {
		PluginConfig.initialize(this);
		PluginConfig config = PluginConfig.getInstance();

		getLogger().info("SimpleDiscordPlugin has been enabled!");

		getServer().getPluginManager().registerEvents(new OnPlayerJoinListener(config), this);
		getServer().getPluginManager().registerEvents(new OnPlayerLeaveListener(config), this);
	}

	@Override
	public void onDisable() {
		getLogger().info("SimpleDiscordPlugin has been disabled!");
	}

}
