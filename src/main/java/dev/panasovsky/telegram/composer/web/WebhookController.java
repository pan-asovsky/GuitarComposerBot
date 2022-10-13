package dev.panasovsky.telegram.composer.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;


@RestController
public class WebhookController {
    private final TelegramWebhookBotCore webhookBotCore;
    private static final Logger LOG = LoggerFactory.getLogger(WebhookController.class);

    @PostMapping({"/telegrambot"})
    public BotApiMethod<?> onUpdateReceived(@RequestBody final Update update) {
        LOG.debug("Update has been received at WebhookController: {}", update.getUpdateId());
        return this.webhookBotCore.onWebhookUpdateReceived(update);
    }

    public WebhookController(final TelegramWebhookBotCore webhookBotCore) {
        this.webhookBotCore = webhookBotCore;
    }

}