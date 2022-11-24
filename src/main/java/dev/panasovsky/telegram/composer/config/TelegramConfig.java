package dev.panasovsky.telegram.composer.config;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@Configuration
public class TelegramConfig {

    @Value("${telegram.bot-token}")
    private String botToken;
    @Value("${telegram.bot-username}")
    private String botUserName;
    @Value("${telegram.webhook-path}")
    private String webhookPath;

}