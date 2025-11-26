package com.example.mutant.service;

import com.example.mutant.model.DnaRecord;
import com.example.mutant.repository.DnaRecordRepository;
import com.example.mutant.util.MutantDetector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MutantService {

    private final DnaRecordRepository repository;
    private final MutantDetector detector = new MutantDetector();

    public boolean isMutant(String[] dna) {

        String compact = String.join("-", dna);

        // Si ya existe en la BD lo devolvemos directamente
        if (repository.existsByDna(compact)) {
            return repository.findAll()
                    .stream()
                    .filter(r -> r.getDna().equals(compact))
                    .findFirst()
                    .get()
                    .isMutant();
        }

        boolean result = detector.isMutant(dna);

        DnaRecord record = DnaRecord.builder()
                .dna(compact)
                .mutant(result)
                .build();

        repository.save(record);

        return result;
    }
}
