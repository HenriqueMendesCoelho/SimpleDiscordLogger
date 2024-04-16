package plugin.simplediscordlogger.discord.adapter.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import plugin.simplediscordlogger.discord.domain.DiscordWebhook;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscordWebhookRequestDto {

	public List<EmbedRequestDto> embeds;

	public DiscordWebhookRequestDto(DiscordWebhook discordWebhook) {
		embeds = discordWebhook.getEmbeds().stream().map(EmbedRequestDto::new).toList();
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
	}

}
