package com.spring5.springrecepie.domain;

import javax.persistence.*;

@Entity
public class UnitOfMeasure {
    private String description;
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
//    @OneToOne
//    private Ingredients ingredients;
}
