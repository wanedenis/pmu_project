package com.pmu.pmu_project.models.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartantDto {

    @NotBlank(message = "Numero invalide: Le numéro est absent")
    @NotNull(message = "Invalid Number: Le numéro est NUL")
    private int numero;

    @NotBlank(message = "Nom invalide : Le nom est vide")
    @NotNull(message = "Nom Invalide: Le nom est NUL")
    private String nom;

}
