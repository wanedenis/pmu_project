package com.pmu.pmu_project.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Partant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer numero;

    private String nom;

    @ManyToOne
    @JoinColumn(name="id_course", nullable=false)
    @JsonBackReference
    private Course course;

    public Partant(Integer numero, String nom, Course course) {
        this.numero = numero;
        this.nom = nom;
        this.course = course;
    }

    public Partant() {

    }
}
