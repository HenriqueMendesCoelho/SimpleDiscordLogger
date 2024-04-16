package plugin.simplediscordlogger.discord.adapter.rest;

import plugin.simplediscordlogger.discord.domain.DiscordWebhook;
import plugin.simplediscordlogger.discord.exception.DiscordWebhookErrorException;

public interface DiscordRepository {

	void sendWebhook(DiscordWebhook request) throws DiscordWebhookErrorException;

}
