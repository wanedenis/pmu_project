package com.pmu.pmu_project.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {

    @NotNull(message = "Date Invalide: La date est NULLE")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date invalide: Le format de la date est invalide")
    private String date;

    @NotNull(message = "Le numéro est NUL")
    private int numero;

    @NotBlank(message = "Nom invalide : Le nom est vide")
    @NotNull(message = "Nom Invalide: Le nom est NUL")
    private String nom;

    @NotNull(message = "La liste des partants est nulle")
    @Size(min = 3, message = "Une course doit avoir au minimum 3 partants")
    private List<PartantDto> partants;

}
