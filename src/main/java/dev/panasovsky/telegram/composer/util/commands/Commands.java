package dev.panasovsky.telegram.composer.util.commands;


public enum Commands {

    CHORD("chord"),
    GAMMA("gamma"),
    UNDEFINED("undefined");

    final String name;

    Commands(final String name) {
        this.name = name;
    }

}