package plugin.simplediscordlogger.discord.exception;

public class DiscordWebhookErrorException extends Exception {

	public DiscordWebhookErrorException(String message) {
		super("Error to send webhook to discord: " + message);
	}
}
