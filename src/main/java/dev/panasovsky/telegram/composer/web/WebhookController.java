package dev.panasovsky.telegram.composer.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;


@Log4j2
@RestController
@RequiredArgsConstructor
public class WebhookController {

    private final TelegramWebhookBotCore webhookBotCore;

    @PostMapping({"/composer"})
    public BotApiMethod<?> onUpdateReceived(@RequestBody final Update update) {

        log.debug("Update has been received at WebhookController: {}", update.getUpdateId());
        return this.webhookBotCore.onWebhookUpdateReceived(update);
    }

}