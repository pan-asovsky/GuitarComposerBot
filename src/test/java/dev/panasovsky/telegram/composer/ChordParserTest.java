package dev.panasovsky.telegram.composer;

import dev.panasovsky.telegram.composer.engine.ChordParser;
import org.junit.*;


public class ChordParserTest {

    final ChordParser parser = new ChordParser();

    @Test
    public void getTonic() {

        final String A = "A#maj";
        final String FMinorSev = "Fm7";
        final String CSharpMinor = "C#m";

        Assert.assertEquals(parser.getTonic(A), "A#");
        Assert.assertEquals(parser.getTonic(FMinorSev), "F");
        Assert.assertEquals(parser.getTonic(CSharpMinor), "C#");
    }

    @Test
    public void validateAndGetChordAFlat() {

        final String AFlat = "Ab";
        final String GSharp = parser.validateAndGetChord(AFlat);
        Assert.assertEquals(GSharp, "G#");
    }

    @Test
    public void validateAndGetChordCFlat() {

        final String CFlat = "Cb";
        final String B = parser.validateAndGetChord(CFlat);
        Assert.assertEquals(B, "B");
    }

    @Test
    public void validateAndGetChordDFlatMinor() {

        final String DFlatMinor = "Dbm";
        final String CSharpMinor = parser.validateAndGetChord(DFlatMinor);
        Assert.assertEquals(CSharpMinor, "C#m");
    }

    @Test
    public void validateAndGetChordGFlatMinor7() {

        final String GFlatMinor7 = "Gbm7";
        final String FSharpMinor7 = parser.validateAndGetChord(GFlatMinor7);
        Assert.assertEquals(FSharpMinor7, "F#m7");
    }

    @Test
    public void validateAndGetChordBSharpMinor() {

        final String BSharpMinor = "B#m";
        final String CMinor = parser.validateAndGetChord(BSharpMinor);
        Assert.assertEquals(CMinor, "Cm");
    }

    @Test
    public void validateAndGetChordFFlatMinor9() {

        final String FFlatMinor9 = "Fbm9";
        final String EMinor9 = parser.validateAndGetChord(FFlatMinor9);
        Assert.assertEquals(EMinor9, "Em9");
    }

    @Test
    public void validateAnswerDSharp() {

        final String DSharp = "Db";
        final String validatedDSharp = parser.validateAnswerChord(DSharp);
        Assert.assertEquals(validatedDSharp, "Db");
    }

    @Test
    public void validateAnswerIncorrectDSharp() {

        final String incorrectDSharp = "D###m";
        final String validatedDSharp = parser.validateAnswerChord(incorrectDSharp);
        Assert.assertEquals(validatedDSharp, "D#m");
    }

    @Test
    public void validateAnswerEMinor() {

        final String EMinor = "Em";
        final String validatedEMinor = parser.validateAnswerChord(EMinor);
        Assert.assertEquals(validatedEMinor, "Em");
    }

    @Test
    public void validateAnswerIncorrectEMinor() {

        final String incorrectEMinor = "Emmmm";
        final String validatedEMinor = parser.validateAnswerChord(incorrectEMinor);
        Assert.assertEquals(validatedEMinor, "Em");
    }

    @Test
    public void validateAnswerGSharpDim() {

        final String GSharpDim = "G#dim";
        final String validatedGSharpDim = parser.validateAnswerChord(GSharpDim);
        Assert.assertEquals(validatedGSharpDim, "G#dim");
    }

    @Test
    public void validateAnswerIncorrectGSharpDim() {

        final String incorrectGSharpDim = "Gdim#dim";
        final String validatedGSharpDim = parser.validateAnswerChord(incorrectGSharpDim);
        Assert.assertEquals(validatedGSharpDim, "G#dim");
    }

    @Test
    public void validateAnswerIncorrectASharpMinor() {

        final String incorrectASharpMinor = "Am#m#m#m";
        final String validatedASharpMinor = parser.validateAnswerChord(incorrectASharpMinor);
        Assert.assertEquals(validatedASharpMinor, "A#m");
    }

}
