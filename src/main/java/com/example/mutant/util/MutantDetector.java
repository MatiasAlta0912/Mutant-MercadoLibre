package com.example.mutant.util;

import com.example.mutant.exception.InvalidDnaException;
import org.springframework.stereotype.Component;

import java.util.Set;
@Component
public class MutantDetector {

    private static final int SEQ = 4;
    private static final Set<Character> VALID_CHARS = Set.of('A', 'T', 'C', 'G');

    public boolean isMutant(String[] dna) {
        validateDna(dna);

        int n = dna.length;
        char[][] table = new char[n][n];

        for (int i = 0; i < n; i++) {
            table[i] = dna[i].toCharArray();
        }

        int sequences = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = table[i][j];

                // Horizontal →
                if (j + SEQ - 1 < n &&
                        c == table[i][j + 1] &&
                        c == table[i][j + 2] &&
                        c == table[i][j + 3]) {
                    sequences++;
                }

                // Vertical ↓
                if (i + SEQ - 1 < n &&
                        c == table[i + 1][j] &&
                        c == table[i + 2][j] &&
                        c == table[i + 3][j]) {
                    sequences++;
                }

                // Diagonal ↘
                if (i + SEQ - 1 < n && j + SEQ - 1 < n &&
                        c == table[i + 1][j + 1] &&
                        c == table[i + 2][j + 2] &&
                        c == table[i + 3][j + 3]) {
                    sequences++;
                }

                // Diagonal ↙
                if (i + SEQ - 1 < n && j - (SEQ - 1) >= 0 &&
                        c == table[i + 1][j - 1] &&
                        c == table[i + 2][j - 2] &&
                        c == table[i + 3][j - 3]) {
                    sequences++;
                }

                if (sequences > 1) {
                    return true; // early termination
                }
            }
        }

        return false;
    }

    private void validateDna(String[] dna) {
        if (dna == null || dna.length == 0) {
            throw new InvalidDnaException("El ADN no puede ser nulo ni vacío");
        }

        int n = dna.length;

        if (n < SEQ) {
            throw new InvalidDnaException(
                    "El ADN debe tener al menos " + SEQ + " filas/columnas"
            );
        }

        for (String row : dna) {
            if (row == null || row.length() != n) {
                throw new InvalidDnaException("El ADN debe ser una matriz NxN");
            }
            for (char ch : row.toCharArray()) {
                if (!VALID_CHARS.contains(ch)) {
                    throw new InvalidDnaException(
                            "ADN inválido: solo se permiten caracteres A, T, C, G"
                    );
                }
            }
        }
    }
}
