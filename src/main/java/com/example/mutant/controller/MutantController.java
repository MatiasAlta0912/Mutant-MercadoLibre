package com.example.mutant.controller;

import com.example.mutant.dto.DnaRequest;
import com.example.mutant.dto.MutantResponse;
import com.example.mutant.service.MutantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Mutants", description = "Endpoints para detección de mutantes")
@RestController
@RequestMapping("/mutant")
@RequiredArgsConstructor
public class MutantController {

    private final MutantService mutantService;

    @Operation(
            summary = "Verifica si un ADN pertenece a un mutante",
            description = "Devuelve 200 si es mutante, 403 si es humano y 400 ante errores de validación."
    )
    @PostMapping
    public ResponseEntity<MutantResponse> isMutant(@Valid @RequestBody DnaRequest request) {

        boolean isMutant = mutantService.isMutant(
                request.getDna().toArray(new String[0])
        );

        HttpStatus status = isMutant ? HttpStatus.OK : HttpStatus.FORBIDDEN;

        return ResponseEntity.status(status)
                .body(new MutantResponse(isMutant));
    }
}
