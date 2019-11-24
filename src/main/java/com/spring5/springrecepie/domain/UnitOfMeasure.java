package com.spring5.springrecepie.domain;

import javax.persistence.*;

@Entity
public class UnitOfMeasure {
    private String uom;
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
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
