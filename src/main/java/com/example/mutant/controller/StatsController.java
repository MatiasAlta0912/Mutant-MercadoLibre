package com.example.mutant.controller;

import com.example.mutant.dto.StatsResponse;
import com.example.mutant.service.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Stats", description = "Estadísticas de análisis de ADN")
@RestController
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    @Operation(
            summary = "Obtiene estadísticas de ADN",
            description = "Retorna cantidad de ADN mutante, humano y el ratio entre ambos."
    )
    @GetMapping("/stats")
    public StatsResponse getStats() {
        long mutants = statsService.countMutants();
        long humans = statsService.countHumans();
        double ratio = statsService.getRatio();

        return new StatsResponse(mutants, humans, ratio);
    }
}
