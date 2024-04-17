package com.discordlogger.discord.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscordWebhook {

	public List<Embed> embeds;

	public static DiscordWebhook playerJoin(String playerName, String message) {
		return DiscordWebhook.builder()
				.embeds(List.of(Embed.builder()
						.title(message.replace("%player%", playerName))
						.timestamp(OffsetDateTime.now())
						.color(65280)
						.build()))
				.build();
	}

	public static DiscordWebhook playerLeave(String playerName, String message) {
		return DiscordWebhook.builder()
				.embeds(List.of(Embed.builder()
						.title(message.replace("%player%", playerName))
						.timestamp(OffsetDateTime.now())
						.color(16711680)
						.build()))
				.build();
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Embed {

		@Builder.Default
		private int type = 1;
		private String title;
		private int color;
		private OffsetDateTime timestamp;
	}

}
