package dev.panasovsky.telegram.composer.config;

import dev.panasovsky.telegram.composer.web.TelegramWebhookBotCore;

import lombok.extern.log4j.Log4j2;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;


@Log4j2
@Configuration
@RequiredArgsConstructor
public class SpringConfig {

    private final TelegramConfig telegramConfig;


    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(this.telegramConfig.getWebhookPath()).build();
    }

    @Bean
    public TelegramWebhookBotCore springWebhookBot(final SetWebhook setWebhook) {

        final TelegramWebhookBotCore telegramBot = new TelegramWebhookBotCore(setWebhook);
        telegramBot.setBotPath(this.telegramConfig.getWebhookPath());
        telegramBot.setBotToken(this.telegramConfig.getBotToken());
        telegramBot.setBotUsername(this.telegramConfig.getBotUserName());

        log.debug("Bot constructor created with token: {}, botPath: {}",
                this.telegramConfig.getBotToken(), this.telegramConfig.getWebhookPath());
        return telegramBot;
    }

}