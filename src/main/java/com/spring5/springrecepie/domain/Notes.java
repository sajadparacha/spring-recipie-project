package com.spring5.springrecepie.domain;

import lombok.*;

import javax.persistence.*;

@Data
/**
 * Commenting below annotation will cause
 * java.lang.StackOverflowError: null
 * 	at com.spring5.springrecepie.domain.Notes.hashCode(Notes.java:7) ~[classes/:na]
 * 	at com.spring5.springrecepie.domain.Recipe.hashCode(Recipe.java:9) ~[classes/:na]
 */
@EqualsAndHashCode(exclude = "recipe")
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Recipe recipe;
    private String recipeNotes;

    public Notes() {
    }



}
