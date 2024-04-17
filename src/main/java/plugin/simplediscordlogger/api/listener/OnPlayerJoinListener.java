package plugin.simplediscordlogger.api.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import plugin.simplediscordlogger.api.config.PluginConfig;
import plugin.simplediscordlogger.discord.adapter.repository.rest.DiscordRepository;
import plugin.simplediscordlogger.discord.adapter.repository.rest.impl.DiscordRepositoryImpl;
import plugin.simplediscordlogger.discord.domain.DiscordWebhook;
import plugin.simplediscordlogger.discord.exception.DiscordWebhookErrorException;

@RequiredArgsConstructor
public class OnPlayerJoinListener implements Listener {

	private final PluginConfig config;

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (config.hasErrors()) {
			Bukkit.getLogger().severe("Discord Webhook URL is not set in the config.yml file!");
			return;
		}
		try {
			String playerName = event.getPlayer().getName();
			final DiscordRepository repository = new DiscordRepositoryImpl(config.getWebhookUrl());
			repository.sendWebhook(DiscordWebhook.playerJoin(playerName, config.getPlayerJoinMessage()));
		} catch (DiscordWebhookErrorException e) {
			Bukkit.getLogger().severe("Fail to send Discord Webhook: " + e.getMessage());
		}
	}

}
