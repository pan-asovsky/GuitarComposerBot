package dev.panasovsky.telegram.composer.util;

import dev.panasovsky.telegram.composer.engine.ComposerHelper;
import dev.panasovsky.telegram.composer.util.commands.ParsedCommand;

import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.methods.*;
import org.telegram.telegrambots.meta.api.methods.send.*;

import lombok.*;


@RequiredArgsConstructor
public class UpdateHandler {

    final private String botUserName;


    public BotApiMethod<?> parseAndProcessCommand(final Update update) {

        final Parser parser = new Parser(botUserName);

        final String messageText = update.getMessage().getText();
        final ParsedCommand parsedCommand = parser.getParsedCommand(messageText);
        final String result = processCommand(parsedCommand);

        final SendMessage message = new SendMessage();
        message.setText("*" + result + "*");
        message.setParseMode("markdown");
        message.setChatId(update.getMessage().getChatId());

        return message;
    }

    private String processCommand(final ParsedCommand parsedCommand) {

        final ComposerHelper composer = new ComposerHelper();
        final String text = parsedCommand.getText();
        if (text.isEmpty()) return "Пустая команда!";

        return switch (parsedCommand.getCommand()) {
            case GAMMA -> composer.buildAnyGamutByChord(text);
            case CHORD -> composer.decomposeChordByNotes(text);
            case UNDEFINED -> composer.getRandomGamut();
        };
    }

}