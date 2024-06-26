package com.discordlogger.discord.adapter.repository.rest.dto;

import com.discordlogger.discord.domain.DiscordWebhook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscordWebhookRequestDto {

	public List<EmbedRequestDto> embeds;

	public DiscordWebhookRequestDto(DiscordWebhook discordWebhook) {
		embeds = discordWebhook.getEmbeds().stream().map(EmbedRequestDto::new).collect(Collectors.toList());
	}

	public String toJson() {
		StringBuilder json = new StringBuilder();
		json.append("{\"embeds\": [");

		for (int i = 0; i < embeds.size(); i++) {
			String embedJson = embeds.get(i).toJson();
			if ((i + 1) < embeds.size()) {
				json.append(embedJson).append(",");
				continue;
			}

			json.append(embedJson).append("]");
		}

		return json.append("}").toString();
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class EmbedRequestDto {
		private int type;
		private String title;
		private int color;
		private OffsetDateTime timestamp;

		public EmbedRequestDto(DiscordWebhook.Embed embed) {
			type = embed.getType();
			title = embed.getTitle();
			color = embed.getColor();
			timestamp = embed.getTimestamp();
		}

		public String toJson() {
			StringBuilder json = new StringBuilder();
			json.append("{");
			json.append("\"type\": ").append(type).append(",");
			json.append("\"title\": \"").append(title).append("\",");
			json.append("\"color\": \"").append(color).append("\",");
			json.append("\"timestamp\":\"").append(timestamp).append("\"");
			json.append("}");

			return json.toString();
		}
	}
}
