package com.example.mutant.util;

public class MutantDetector {

    public boolean isMutant(String[] dna) {
        int n = dna.length;
        int sequences = 0;

        // Validación NxN
        for (String row : dna) {
            if (row.length() != n) return false;
        }

        char[][] table = new char[n][n];
        for (int i = 0; i < n; i++) {
            table[i] = dna[i].toCharArray();
        }

        // Buscar más de una secuencia de 4 letras iguales
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = table[i][j];

                // Horizontal →
                if (j + 3 < n &&
                        c == table[i][j + 1] &&
                        c == table[i][j + 2] &&
                        c == table[i][j + 3])
                    sequences++;

                // Vertical ↓
                if (i + 3 < n &&
                        c == table[i + 1][j] &&
                        c == table[i + 2][j] &&
                        c == table[i + 3][j])
                    sequences++;

                // Diagonal ↘
                if (i + 3 < n && j + 3 < n &&
                        c == table[i + 1][j + 1] &&
                        c == table[i + 2][j + 2] &&
                        c == table[i + 3][j + 3])
                    sequences++;

                // Diagonal ↙
                if (i + 3 < n && j - 3 >= 0 &&
                        c == table[i + 1][j - 1] &&
                        c == table[i + 2][j - 2] &&
                        c == table[i + 3][j - 3])
                    sequences++;

                if (sequences > 1) return true;
            }
        }

        return false;
    }
}
