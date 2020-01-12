package com.spring5.springrecepie.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
@Data
//@EqualsAndHashCode(exclude = {"recipe","unitOfMeasure"})
@Entity
public class Ingredients {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String description;
    private BigDecimal amount;
    @ManyToOne
    private Recipe recipe;
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    public Long getRecipeId(){
        return this.recipe.getId();
    }
}
