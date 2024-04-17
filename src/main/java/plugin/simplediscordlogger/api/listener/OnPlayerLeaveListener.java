package plugin.simplediscordlogger.api.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import plugin.simplediscordlogger.api.config.PluginConfig;
import plugin.simplediscordlogger.discord.adapter.repository.rest.DiscordRepository;
import plugin.simplediscordlogger.discord.adapter.repository.rest.impl.DiscordRepositoryImpl;
import plugin.simplediscordlogger.discord.domain.DiscordWebhook;
import plugin.simplediscordlogger.discord.exception.DiscordWebhookErrorException;

@RequiredArgsConstructor
public class OnPlayerLeaveListener implements Listener {

	private final DiscordRepository repository = new DiscordRepositoryImpl();

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		PluginConfig config = PluginConfig.getInstance();
		if (config.hasErrors()) {
			Bukkit.getLogger().severe("Discord Webhook URL is not set in the config.yml file!");
			return;
		}

		try {
			String playerName = event.getPlayer().getName();
			repository.sendWebhook(DiscordWebhook.playerLeave(playerName, config.getPlayerLeaveMessage()));
		} catch (DiscordWebhookErrorException e) {
			Bukkit.getLogger().severe("Fail to send Discord Webhook: " + e.getMessage());
		}
	}

}
