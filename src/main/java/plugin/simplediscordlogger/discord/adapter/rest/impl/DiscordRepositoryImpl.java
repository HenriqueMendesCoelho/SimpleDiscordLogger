package plugin.simplediscordlogger.discord.adapter.rest.impl;

import lombok.RequiredArgsConstructor;
import plugin.simplediscordlogger.discord.adapter.rest.DiscordRepository;
import plugin.simplediscordlogger.discord.adapter.rest.dto.DiscordWebhookRequestDto;
import plugin.simplediscordlogger.discord.domain.DiscordWebhook;
import plugin.simplediscordlogger.discord.exception.DiscordWebhookErrorException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
public class DiscordRepositoryImpl implements DiscordRepository {

	private final String url;

	@Override
	public void sendWebhook(DiscordWebhook request) throws DiscordWebhookErrorException {
		try {
			try (HttpClient httpClient = HttpClient.newHttpClient()) {
				String json = new DiscordWebhookRequestDto(request).toJson();
				HttpRequest httpRequest = HttpRequest.newBuilder()
						.uri(URI.create(url))
						.header("Content-Type", "application/json")
						.method("POST", HttpRequest.BodyPublishers.ofString(json))
						.build();
				HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

				if (response.statusCode() >= 300) {
					throw new DiscordWebhookErrorException("Unexpected code " + response.statusCode());
				}
			}
		} catch (IOException | InterruptedException e) {
			throw new DiscordWebhookErrorException(e.getMessage());
		}

	}

}
