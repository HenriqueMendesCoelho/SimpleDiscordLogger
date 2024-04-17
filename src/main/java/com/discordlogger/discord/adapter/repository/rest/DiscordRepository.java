package com.discordlogger.discord.adapter.repository.rest;

import com.discordlogger.discord.domain.DiscordWebhook;
import com.discordlogger.discord.exception.DiscordWebhookErrorException;

public interface DiscordRepository {

	void sendWebhook(DiscordWebhook request) throws DiscordWebhookErrorException;

}
