package plugin.simplediscordlogger.api.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import plugin.simplediscordlogger.discord.adapter.rest.DiscordRepository;
import plugin.simplediscordlogger.discord.adapter.rest.impl.DiscordRepositoryImpl;
import plugin.simplediscordlogger.discord.domain.DiscordWebhook;
import plugin.simplediscordlogger.discord.exception.DiscordWebhookErrorException;

@RequiredArgsConstructor
public class OnPlayerLeaveListener implements Listener {

	private final String url;
	private final String messageJoin;

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		if (url == null || url.equals("discordwebhookurl")) {
			Bukkit.getLogger().severe("Discord Webhook URL is not set in the config.yml file!");
			return;
		}

		String playerName = event.getPlayer().getName();
		try {
			final DiscordRepository repository = new DiscordRepositoryImpl(url);
			repository.sendWebhook(DiscordWebhook.playerLeave(playerName, messageJoin));
		} catch (DiscordWebhookErrorException e) {
			Bukkit.getLogger().severe("Fail to send Discord Webhook: " + e.getMessage());
		}
	}

}
