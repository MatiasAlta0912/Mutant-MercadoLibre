package com.example.mutant.service;

import com.example.mutant.repository.DnaRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final DnaRecordRepository repository;

    public long countMutants() {
        return repository.countByMutantTrue();
    }

    public long countHumans() {
        return repository.countByMutantFalse();
    }

    public double getRatio() {
        long humans = countHumans();
        if (humans == 0) {
            return 0.0;
        }
        return (double) countMutants() / humans;
    }
}
