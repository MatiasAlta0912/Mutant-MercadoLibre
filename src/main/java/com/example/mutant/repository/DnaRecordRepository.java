package com.example.mutant.repository;

import com.example.mutant.model.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DnaRecordRepository extends JpaRepository<DnaRecord, Long> {
    boolean existsByDna(String dna);
}
