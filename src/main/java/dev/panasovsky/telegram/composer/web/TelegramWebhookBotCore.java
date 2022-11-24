package dev.panasovsky.telegram.composer.web;

import dev.panasovsky.telegram.composer.util.UpdateHandler;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

import java.util.Objects;


@Getter
@Setter
@Log4j2
public class TelegramWebhookBotCore extends SpringWebhookBot {

    private String botPath;
    private String botToken;
    private String botUsername;


    public TelegramWebhookBotCore(final SetWebhook setWebhook) {
        super(setWebhook);
    }

    public BotApiMethod<?> onWebhookUpdateReceived(final Update update) {

        log.debug("Update has been received at TelegramWebhookBotCore: {}",
                update.getUpdateId());

        final UpdateHandler updateHandler = new UpdateHandler(botUsername);
        if (!isCorrectUpdate(update)) return null;
        else return updateHandler.parseAndProcessCommand(update);
    }

    private boolean isCorrectUpdate(final Update update) {
        return isCallbackQuery(update)
                ? isCorrectCallbackUpdate(update.getCallbackQuery())
                : isCorrectNoCallbackUpdate(update);
    }

    private boolean isCallbackQuery(final Update update) {
        return update.hasCallbackQuery();
    }

    private boolean isCorrectCallbackUpdate(final CallbackQuery callback) {

        if (Objects.isNull(callback.getMessage())) {
            log.error("CallbackQuery doesn't contain a Message.");
            return false;
        } else if (Objects.isNull(callback.getMessage().getChat())) {
            log.error("CallbackQuery doesn't contain a Chat.");
            return false;
        } else if (Objects.isNull(callback.getId())) {
            log.error("CallbackQuery doesn't contain a ID.");
            return false;
        } else if (Objects.isNull(callback.getData())) {
            log.error("CallbackQuery doesn't contain a Data.");
            return false;
        } else {
            log.debug("ChatID: {}, CallbackID: {}",
                    callback.getMessage().getChatId(), callback.getId());
            return true;
        }
    }

    private boolean isCorrectNoCallbackUpdate(final Update update) {

        if (Objects.isNull(update.getMessage())) {
            log.error("Update doesn't contain a Message.");
            return false;
        } else if (Objects.isNull(update.getMessage().getChat())) {
            log.error("Message doesn't contain a Chat.");
            return false;
        } else if (Objects.isNull(update.getMessage().getChatId())) {
            log.error("Message doesn't contain a ChatID.");
            return false;
        } else {
            log.debug("ChatID: {}", update.getMessage().getChatId());
            return true;
        }
    }

}