package dev.panasovsky.telegram.composer;

import dev.panasovsky.telegram.composer.engine.*;
import org.junit.*;


public class ComposerHelperTest {

    final ComposerHelper composer = new ComposerHelper();

    @Test
    public void buildIntervalMinorSecond() {

        final String tonic = "C";
        final Interval interval = Interval.MINOR_SECOND;

        final String minorSecond = composer.buildInterval(tonic, interval);
        Assert.assertEquals(minorSecond, "C#");
    }

    @Test
    public void buildIntervalMajorSecond() {

        final String tonic = "C#";
        final Interval interval = Interval.MAJOR_SECOND;

        final String majorSecond = composer.buildInterval(tonic, interval);
        Assert.assertEquals(majorSecond, "D#");
    }

    @Test
    public void buildIntervalMinorThird() {

        final String tonic = "D";
        final Interval interval = Interval.MINOR_THIRD;

        final String minorThird = composer.buildInterval(tonic, interval);
        Assert.assertEquals(minorThird, "F");
    }

    @Test
    public void buildIntervalMajorThird() {

        final String tonic = "D#";
        final Interval interval = Interval.MAJOR_THIRD;

        final String majorThird = composer.buildInterval(tonic, interval);
        Assert.assertEquals(majorThird, "G");
    }

    @Test
    public void buildIntervalPerfectFourth() {

        final String tonic = "E";
        final Interval interval = Interval.PERFECT_FOURTH;

        final String perfectFourth = composer.buildInterval(tonic, interval);
        Assert.assertEquals(perfectFourth, "A");

        final String newTonic = "E";
        final Interval newInterval = Interval.PERFECT_FOURTH;

        final String newPerfectFourth = composer.buildInterval(newTonic, newInterval);
        Assert.assertEquals(newPerfectFourth, "A");
    }

    @Test
    public void buildIntervalAugmentedFourth() {

        final String tonic = "F";
        final Interval interval = Interval.AUGMENTED_FOURTH;

        final String augmentedFourth = composer.buildInterval(tonic, interval);
        Assert.assertEquals(augmentedFourth, "B");
    }

    @Test
    public void buildIntervalPerfectFifth() {

        final String tonic = "F#";
        final Interval interval = Interval.PERFECT_FIFTH;

        final String perfectFifth = composer.buildInterval(tonic, interval);
        Assert.assertEquals(perfectFifth, "C#");
    }

    @Test
    public void buildIntervalMinorSixth() {

        final String tonic = "G";
        final Interval interval = Interval.MINOR_SIXTH;

        final String minorSixth = composer.buildInterval(tonic, interval);
        Assert.assertEquals(minorSixth, "D#");
    }

    @Test
    public void buildIntervalMajorSixth() {

        final String tonic = "G#";
        final Interval interval = Interval.MAJOR_SIXTH;

        final String majorSixth = composer.buildInterval(tonic, interval);
        Assert.assertEquals(majorSixth, "F");
    }

    @Test
    public void buildIntervalMinorSeventh() {

        final String tonic = "A";
        final Interval interval = Interval.MINOR_SEVENTH;

        final String minorSeventh = composer.buildInterval(tonic, interval);
        Assert.assertEquals(minorSeventh, "G");
    }

    @Test
    public void buildIntervalMajorSeventh() {

        final String tonic = "A#";
        final Interval interval = Interval.MAJOR_SEVENTH;

        final String majorSeventh = composer.buildInterval(tonic, interval);
        Assert.assertEquals(majorSeventh, "A");
    }

    @Test
    public void buildIntervalOctave() {

        final String tonic = "B";
        final Interval interval = Interval.OCTAVE;

        final String octave = composer.buildInterval(tonic, interval);
        Assert.assertEquals(octave, "B");
    }

    @Test
    public void buildIntervalMinorNinth() {

        final String tonic = "C";
        final Interval interval = Interval.MINOR_NINTH;

        final String minorNinth = composer.buildInterval(tonic, interval);
        Assert.assertEquals(minorNinth, "C#");
    }

    @Test
    public void buildIntervalMajorNinth() {

        final String tonic = "C#";
        final Interval interval = Interval.MAJOR_NINTH;

        final String majorNinth = composer.buildInterval(tonic, interval);
        Assert.assertEquals(majorNinth, "D#");
    }

    @Test
    public void decomposeChordByNotes() {

        final String chordC = "C";
        final String decomposedC = composer.decomposeChordByNotes(chordC);
        Assert.assertEquals(decomposedC, "C E G");

        final String chordDSharp = "D#";
        final String decomposedDSharp = composer.decomposeChordByNotes(chordDSharp);
        Assert.assertEquals(decomposedDSharp, "D# G A#");

        final String chordFSharpMinor = "F#m";
        final String decomposedFSharpMinor = composer.decomposeChordByNotes(chordFSharpMinor);
        Assert.assertEquals(decomposedFSharpMinor, "F# A C#");

        final String chordCFlatMinor = "Cbm";
        final String decomposedBMinor = composer.decomposeChordByNotes(chordCFlatMinor);
        Assert.assertEquals(decomposedBMinor, "B D F#");

        final String chordESharp = "E#";
        final String decomposedESharp = composer.decomposeChordByNotes(chordESharp);
        Assert.assertEquals(decomposedESharp, "F A C");
    }

    @Test
    public void buildMajorGamutByChord() {

        final String cMajor = "C";
        final String gamutCMajor = composer.buildAnyGamutByChord(cMajor);
        Assert.assertEquals(gamutCMajor, "C Dm Em F G Am Bdim");

        final String dSharpMajor = "D#";
        final String gamutDSharpMajor = composer.buildAnyGamutByChord(dSharpMajor);
        Assert.assertEquals(gamutDSharpMajor, "D# Fm Gm G# A# Cm Ddim");

        final String EFlat = "Eb";
        final String gamutEFlatMajor = composer.buildAnyGamutByChord(EFlat);
        Assert.assertEquals(gamutEFlatMajor, "D# Fm Gm G# A# Cm Ddim");
    }

    @Test
    public void buildMinorGamutByChord() {

        final String cMinor = "Cm";
        final String gamutCMinor = composer.buildAnyGamutByChord(cMinor);
        Assert.assertEquals(gamutCMinor, "Cm Ddim D# Fm Gm G# A#");

        final String dFlatMinor = "Dbm";
        final String gamutDFlatMinor = composer.buildAnyGamutByChord(dFlatMinor);
        Assert.assertEquals(gamutDFlatMinor, "C#m D#dim E F#m G#m A B");

        final String aSharpMinor = "A#m";
        final String gamutASharpMinor = composer.buildAnyGamutByChord(aSharpMinor);
        Assert.assertEquals(gamutASharpMinor, "A#m Cdim C# D#m Fm F# G#");
    }

}