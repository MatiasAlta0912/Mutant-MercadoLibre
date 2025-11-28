package com.example.mutant.controller;

import com.example.mutant.dto.StatsResponse;
import com.example.mutant.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/stats")
    public StatsResponse getStats() {
        long mutants = statsService.countMutants();
        long humans = statsService.countHumans();
        double ratio = statsService.getRatio();

        return new StatsResponse(mutants, humans, ratio);
    }
}
