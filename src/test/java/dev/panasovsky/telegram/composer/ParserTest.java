package dev.panasovsky.telegram.composer;

import dev.panasovsky.telegram.composer.util.Parser;
import dev.panasovsky.telegram.composer.util.commands.Commands;
import dev.panasovsky.telegram.composer.util.commands.ParsedCommand;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;


public class ParserTest {

    private Parser parser;
    private static final String botName = "composer";

    @Before
    public void setParser() {
        parser = new Parser(botName);
    }

    @Test
    public void getParsedCommandChord() {

        final String text = "/chord D#";
        final ParsedCommand parsedCommand = parser.getParsedCommand(text);

        assertEquals("D#", parsedCommand.getText());
        Assert.assertEquals(Commands.CHORD, parsedCommand.getCommand());
    }

    @Test
    public void getParsedCommandTonality() {

        final String text = "/gamma@" + botName + " C";
        final ParsedCommand parsedCommand = parser.getParsedCommand(text);

        assertEquals(Commands.GAMMA, parsedCommand.getCommand());
        assertEquals("C", parsedCommand.getText());
    }

    @Test
    public void getParsedCommandUndefined() {

        final String text = "/start@" + botName + " just text";
        final ParsedCommand parsedCommand = parser.getParsedCommand(text);

        assertEquals(Commands.UNDEFINED, parsedCommand.getCommand());
        assertEquals("just text", parsedCommand.getText());
    }

}
