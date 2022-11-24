package dev.panasovsky.telegram.composer.engine;

import dev.panasovsky.telegram.composer.exceptions.ChordConstructingException;

import java.util.*;


public class ComposerHelper {

    private final ChordParser chordParser = new ChordParser();


    public String buildAnyGamutByChord(final String chord) {

        final String correctChord;

        try {
            correctChord = chordParser.validateAndGetChord(chord);
        } catch (ChordConstructingException e) {
            return e.getException();
        }

        return buildGamut(correctChord);
    }

    private String buildGamut(final String chord) {

        final String[] gamma = new String[7];
        gamma[0] = chord;

        final String tonic = chordParser.getTonic(chord);
        gamma[1] = buildInterval(tonic, Interval.MAJOR_SECOND);
        gamma[3] = buildInterval(tonic, Interval.PERFECT_FOURTH);
        gamma[4] = buildInterval(tonic, Interval.PERFECT_FIFTH);

        if (chordParser.isMinor(chord)) buildMinorGamut(gamma);
        else buildMajorGamut(gamma);

        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < 6; i++) result.append(gamma[i]).append(" ");
        result.append(gamma[6]);

        return result.toString();
    }

    private void buildMinorGamut(final String[] gamma) {

        final String tonic = chordParser.getTonic(gamma[0]);
        gamma[2] = buildInterval(tonic, Interval.MINOR_THIRD);
        gamma[5] = buildInterval(tonic, Interval.MINOR_SIXTH);
        gamma[6] = buildInterval(tonic, Interval.MINOR_SEVENTH);

        setHarmonyPattern(gamma, Harmony.MINOR);
    }

    private void buildMajorGamut(final String[] gamma) {

        final String tonic = gamma[0];
        gamma[2] = buildInterval(tonic, Interval.MAJOR_THIRD);
        gamma[5] = buildInterval(tonic, Interval.MAJOR_SIXTH);
        gamma[6] = buildInterval(tonic, Interval.MAJOR_SEVENTH);

        setHarmonyPattern(gamma, Harmony.MAJOR);
    }

    private void setHarmonyPattern(final String[] gamma, final Harmony harmony) {

        final String dim = "dim";
        final String minor = "m";

        switch (harmony) {
            case MAJOR -> {
                gamma[1] = gamma[1] + minor;
                gamma[2] = gamma[2] + minor;
                gamma[5] = gamma[5] + minor;
                gamma[6] = gamma[6] + dim;
            }
            case MINOR -> {
                gamma[1] = gamma[1] + dim;
                gamma[3] = gamma[3] + minor;
                gamma[4] = gamma[4] + minor;
            }
        }
    }


    public String decomposeChordByNotes(final String chord) {

        final String correctChord;

        try {
            correctChord = chordParser.validateAndGetChord(chord);
        } catch (ChordConstructingException e) {
            return e.getException();
        }

        return decomposeChord(correctChord);
    }

    private String decomposeChord(final String chord) {

        final String tonic;
        try {
            tonic = chordParser.getTonic(chord);
        } catch (ChordConstructingException e) {
            return e.getException();
        }

        final String third = buildInterval(tonic, Interval.PERFECT_FIFTH);
        final String second;
        if (chordParser.isMinor(chord)) second = buildInterval(tonic, Interval.MINOR_THIRD);
        else second = buildInterval(tonic, Interval.MAJOR_THIRD);

        return tonic + " " + second + " " + third;
    }


    public String getRandomGamut() {

        final String randomChord = getRandomChord();
        return buildGamut(randomChord);
    }

    private String getRandomChord() {

        final List<String> sequence = ChordParser.getSequence();
        final int sequenceSize = sequence.size();
        final int random = (int) (Math.random() * sequenceSize);

        final String remaining;
        if (random % 2 == 0) remaining = "m";
        else remaining = "";
        return sequence.get(random) + remaining;
    }

    public String buildInterval(final String tonic, final Interval interval) {

        final List<String> sequence = ChordParser.getSequence();

        String builtInterval = "";
        final int intervalSemitone = interval.getSemitone();
        for (String note : sequence) {
            if (note.equals(tonic)) {
                final int indexOfNote = sequence.indexOf(note);
                builtInterval = sequence.get(indexOfNote + intervalSemitone);
            }
        }
        return builtInterval;
    }

}