package com.example.mutant.service;

import com.example.mutant.model.DnaRecord;
import com.example.mutant.repository.DnaRecordRepository;
import com.example.mutant.util.DnaHashUtils;
import com.example.mutant.util.MutantDetector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MutantService {

    private final DnaRecordRepository repository;
    private final MutantDetector detector;

    public boolean isMutant(String[] dna) {

        // 1) Compactamos el ADN
        String compact = String.join("-", dna);

        // 2) Calculamos el hash
        String hash = DnaHashUtils.sha256(compact);

        // 3) Si ya existe, devolvemos el resultado guardado
        return repository.findByDnaHash(hash)
                .map(DnaRecord::isMutant)
                .orElseGet(() -> {

                    // 4) Si no existe, ejecutamos el detector
                    boolean result = detector.isMutant(dna);

                    // 5) Guardamos en base
                    DnaRecord record = DnaRecord.builder()
                            .dna(compact)
                            .dnaHash(hash)
                            .mutant(result)
                            .build();

                    repository.save(record);

                    return result;
                });
    }
}
