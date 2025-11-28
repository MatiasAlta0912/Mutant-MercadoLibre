package com.example.mutant.controller;

import com.example.mutant.dto.DnaRequest;
import com.example.mutant.dto.MutantResponse;
import com.example.mutant.service.MutantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;   // ✅ jakarta

@RestController
@RequestMapping("/mutant")
@RequiredArgsConstructor
public class MutantController {

    private final MutantService mutantService;

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
