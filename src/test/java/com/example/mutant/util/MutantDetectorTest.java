package com.example.mutant.util;

import com.example.mutant.exception.InvalidDnaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MutantDetectorTest {

    private final MutantDetector detector = new MutantDetector();

    @Test
    void testMutantHorizontal() {
        String[] dna = {
                "AAAAAA",
                "CAGTGC",
                "TTATGT",
                "AGACGG",
                "GCGTCA",
                "TCACTG"
        };

        assertTrue(detector.isMutant(dna));
    }

    @Test
    void testHuman() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATTT",
                "AGACGG",
                "GCGTCA",
                "TCACTG"
        };

        assertFalse(detector.isMutant(dna));
    }

    @Test
    void testInvalidCharacters() {
        String[] dna = {
                "ABCDEF",
                "TTTTTT",
                "TTTTTT",
                "TTTTTT",
                "TTTTTT",
                "TTTTTT"
        };

        assertThrows(InvalidDnaException.class,
                () -> detector.isMutant(dna));
    }

    @Test
    void testInvalidMatrixNotNxN() {
        String[] dna = {
                "ATGC",
                "CAGT",
                "TTA"
        };

        assertThrows(InvalidDnaException.class,
                () -> detector.isMutant(dna));
    }
}
