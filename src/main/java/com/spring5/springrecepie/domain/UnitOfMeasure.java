package com.spring5.springrecepie.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class UnitOfMeasure {
    private String description;
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
//    @OneToOne
//    private Ingredients ingredients;
}
