package dev.panasovsky.telegram.composer.util.commands;


public class ParsedCommand {

    private String text;
    private Commands command;

    public ParsedCommand(final Commands command, final String text) {
        this.text = text;
        this.command = command;
    }

    public String getText() {
        return text;
    }

    public Commands getCommand() {
        return command;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCommand(Commands command) {
        this.command = command;
    }

}