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
		getServer().getPluginManager().registerEvents(new OnPlayerJoinListener(), this);
		getServer().getPluginManager().registerEvents(new OnPlayerLeaveListener(), this);

		getLogger().info("SimpleDiscordPlugin has been enabled!");
	}

	@Override
	public void onDisable() {
		getLogger().info("SimpleDiscordPlugin has been disabled!");
	}

}
