package dev.panasovsky.telegram.composer.util;

import dev.panasovsky.telegram.composer.util.commands.Commands;
import dev.panasovsky.telegram.composer.util.commands.ParsedCommand;

import lombok.extern.log4j.Log4j2;
import lombok.RequiredArgsConstructor;

import org.glassfish.grizzly.utils.*;


@Log4j2
@RequiredArgsConstructor
public class Parser {

    private final String botName;
    private final String DELIMITER_COMMAND_BOTNAME = "@";


    public ParsedCommand getParsedCommand(final String text) {

        String trimText = "";
        if (text != null) trimText = text.trim();

        final ParsedCommand parsedCommand = new ParsedCommand(Commands.UNDEFINED, trimText);
        if ("".equals(trimText)) return parsedCommand;

        final Pair<String, String> commandAndText = getDelimitedCommandFromText(trimText);

        if (isCommand(commandAndText.getFirst())) {
            if (isCommandForMe(commandAndText.getFirst())) {
                final String commandForParse = cutCommandFromFullText(commandAndText.getFirst());
                final Commands commandFromText = getCommandFromText(commandForParse);
                parsedCommand.setText(commandAndText.getSecond());
                parsedCommand.setCommand(commandFromText);
            } else {
                parsedCommand.setCommand(Commands.GAMMA);
                parsedCommand.setText(commandAndText.getSecond());
            }
        }

        return parsedCommand;
    }

    private String cutCommandFromFullText(final String text) {
        return text.contains(DELIMITER_COMMAND_BOTNAME) ?
                text.substring(1, text.indexOf(DELIMITER_COMMAND_BOTNAME)) :
                text.substring(1);
    }

    private Commands getCommandFromText(final String text) {

        final String upperCaseText = text.toUpperCase().trim();
        Commands command = Commands.UNDEFINED;
        try {
            command = Commands.valueOf(upperCaseText);
        } catch (IllegalArgumentException e) {
            log.debug("Can't parse command: " + text);
        }

        return command;
    }

    private Pair<String, String> getDelimitedCommandFromText(final String trimText) {

        Pair<String, String> commandText;
        if (trimText.contains(" ")) {
            int indexOfSpace = trimText.indexOf(" ");
            commandText = new Pair<>(trimText.substring(0, indexOfSpace),
                    trimText.substring(indexOfSpace + 1));
        } else {
            commandText = new Pair<>(trimText, "");
        }

        return commandText;
    }

    private boolean isCommandForMe(final String command) {

        if (command.contains(DELIMITER_COMMAND_BOTNAME)) {
            final String botNameForEqual =
                    command.substring(command.indexOf(DELIMITER_COMMAND_BOTNAME) + 1);
            return botName.equals(botNameForEqual);
        }

        return true;
    }

    private boolean isCommand(final String text) {
        return text.startsWith("/");
    }

}