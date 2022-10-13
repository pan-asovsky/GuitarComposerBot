package dev.panasovsky.telegram.composer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class TelegramConfig {
    @Value("${telegram.webhook-path}")
    private String webhookPath;
    @Value("${telegram.bot-username}")
    private String botUserName;
    @Value("${telegram.bot-token}")
    private String botToken;

    public TelegramConfig() {
    }

    public String getWebhookPath() {
        return this.webhookPath;
    }

    public String getBotUserName() {
        return this.botUserName;
    }

    public String getBotToken() {
        return this.botToken;
    }
}