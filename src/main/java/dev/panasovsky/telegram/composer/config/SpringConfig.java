package dev.panasovsky.telegram.composer.config;

import dev.panasovsky.telegram.composer.web.TelegramWebhookBotCore;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
public class SpringConfig {

    private static final Logger log = LogManager.getLogger(SpringConfig.class);
    private final TelegramConfig telegramConfig;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(this.telegramConfig.getWebhookPath()).build();
    }

    @Bean
    public TelegramWebhookBotCore springWebhookBot(final SetWebhook setWebhook) {
        TelegramWebhookBotCore telegramBot = new TelegramWebhookBotCore(setWebhook);
        telegramBot.setBotPath(this.telegramConfig.getWebhookPath());
        telegramBot.setBotToken(this.telegramConfig.getBotToken());
        telegramBot.setBotUsername(this.telegramConfig.getBotUserName());
        log.debug("Bot constructor created with token: {}, botPath: {}", this.telegramConfig.getBotToken(), this.telegramConfig.getWebhookPath());
        return telegramBot;
    }

    public SpringConfig(final TelegramConfig telegramConfig) {
        this.telegramConfig = telegramConfig;
    }

}