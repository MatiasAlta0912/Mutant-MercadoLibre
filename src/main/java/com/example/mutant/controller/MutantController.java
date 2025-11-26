package com.example.mutant.controller;

import com.example.mutant.service.MutantService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutant")
@RequiredArgsConstructor
public class MutantController {

    private final MutantService service;

    @PostMapping("/")
    public ResponseEntity<?> isMutant(@RequestBody DnaRequest request) {

        boolean mutant = service.isMutant(request.getDna());

        if (mutant) {
            return ResponseEntity.ok("Es mutante");
        } else {
            return ResponseEntity.status(403).body("No es mutante");
        }
    }

    @Data
    static class DnaRequest {
        private String[] dna;
    }
}

