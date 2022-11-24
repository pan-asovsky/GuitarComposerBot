package dev.panasovsky.telegram.composer.engine;

import java.util.Map;
import java.util.List;
import java.util.Arrays;

import dev.panasovsky.telegram.composer.exceptions.ChordConstructingException;


public class ChordParser {

    private final static List<String> SEQUENCE = Arrays.asList(
            "C", "C#", "D", "D#", "E", "F", "F#", "G",
            "G#", "A", "A#", "B", "C", "C#", "D", "D#",
            "E", "F", "F#", "G", "G#", "A", "A#", "B",
            "C", "C#", "D", "D#");

    private final static Map<String, String> INCORRECT_CHORDS = Map.of(
            "E#", "F", "E#m", "Fm", "B#", "C", "B#m", "Cm"
    );


    public String validateAndGetChord(final String chord) throws ChordConstructingException {

        if (isFlatAndSharp(chord)) throw new ChordConstructingException("Неверный аккорд!");
        if (isNotChord(chord)) throw new ChordConstructingException("Неверный аккорд!");

        String resultChord = chord;
        if (isIncorrectChord(resultChord)) resultChord = convertToCorrectChord(resultChord);
        if (isFlat(resultChord)) resultChord = convertToSequenceView(resultChord);

        return resultChord;
    }

    public static List<String> getSequence() {
        return SEQUENCE;
    }

    public String getTonic(final String chord) throws ChordConstructingException {

        String temp;
        final int chordLength = chord.length();

        temp = chord.substring(0, chordLength);
        for (String note : SEQUENCE) if (temp.equals(note)) return temp;

        temp = chord.substring(0, 2);
        for (String note : SEQUENCE) if (temp.equals(note)) return temp;

        temp = chord.substring(0, 1);
        for (String note : SEQUENCE) if (temp.equals(note)) return temp;

        throw new ChordConstructingException("Не удалось найти тонику!");
    }

    public boolean isMinor(final String chord) {
        return chord.contains("m");
    }

    private boolean isFlat(final String chord) {
        return chord.contains("b");
    }

    private boolean isFlatAndSharp(final String chord) {
        return chord.contains("b") && chord.contains("#");
    }

    private boolean isNotChord(final String chord) {

        final String firstChar = String.valueOf(chord.charAt(0));
        return (!SEQUENCE.contains(firstChar));
    }

    private boolean isIncorrectChord(final String chord) {
        return INCORRECT_CHORDS.containsKey(chord);
    }

    private String convertToCorrectChord(final String chord) {
        return INCORRECT_CHORDS.get(chord);
    }

    private String convertToSequenceView(final String chord) {

        final ComposerHelper composerHelper = new ComposerHelper();
        final String tonic = String.valueOf(chord.charAt(0));
        final String newTonic = composerHelper.buildInterval(tonic, Interval.MAJOR_SEVENTH);

        String remainingPart = chord.substring(1);
        if (remainingPart.length() > 1) {
            remainingPart = remainingPart.replace("#", "");
            remainingPart = remainingPart.replace("b", "");
            return newTonic + remainingPart;
        }
        return newTonic;
    }

    public String validateAnswerChord(final String chord) {

        final String sharp = "#";
        final String minor = "m";
        final String dim = "dim";

        String result = chord;
        if (result.contains(sharp)) result = removeExcessSym(result, sharp);
        if (result.contains(dim)) result = removeExcessSym(result, dim);
        if (result.contains(minor)) result = removeExcessSym(result, minor);

        return result;
    }

    private String removeExcessSym(final String text, final String excessSym) {

        String result = text;

        int excessCount = count(text, excessSym);
        while (excessCount > 1) {
            result = result.replaceFirst(excessSym, "");
            excessCount = count(result, excessSym);
        }

        return result;
    }

    private int count(final String str, final String target) {
        return (str.length() - str.replace(target, "").length()) / target.length();
    }

}