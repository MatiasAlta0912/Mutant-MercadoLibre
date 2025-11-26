package com.example.mutant.service;

import com.example.mutant.repository.DnaRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.mutant.model.DnaRecord;


@Service
@RequiredArgsConstructor
public class StatsService {

    private final DnaRecordRepository repository;

    public long countMutants() {
        return repository.findAll()
                .stream()
                .filter(DnaRecord::isMutant)
                .count();
    }

    public long countHumans() {
        return repository.findAll()
                .stream()
                .filter(r -> !r.isMutant())
                .count();
    }

    public double getRatio() {
        long humans = countHumans();
        return humans == 0 ? 0 : (double) countMutants() / humans;
    }
}
