package com.example.mutant.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DnaRequest {

    @NotNull(message = "El campo dna no puede ser nulo")
    @NotEmpty(message = "El campo dna no puede estar vacío")
    @Size(min = 4, message = "El ADN debe tener al menos 4 filas")
    private List<
            @NotBlank(message = "Cada fila del ADN no puede estar vacía")
            @Pattern(regexp = "[ATCG]+", message = "Cada fila solo puede contener A, T, C, G")
                    String
            > dna;
}
