package com.example.mutant.controller;

import com.example.mutant.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class StatsController {

    private final StatsService service;

    @GetMapping("/stats")
    public Map<String, Object> stats() {
        return Map.of(
                "count_mutant_dna", service.countMutants(),
                "count_human_dna", service.countHumans(),
                "ratio", service.getRatio()
        );
    }
}
