package com.spring5.springrecepie.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
@Data
/**
 * Having bydirectional relationshps in a n entity and using lambok with it creates problem for hashcoding so we will have to exclude the bidirectional memebers of this class
 * while using lombokl
 * PS: A circular refrence is created
 */
@EqualsAndHashCode(exclude = "recipes")
@Entity
public class Category {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String description;
    //**categories here is the name of the joinTable in the Recipe Class

    @ManyToMany(mappedBy = "categories",fetch = FetchType.EAGER)
    private Set<Recipe> recipes;





}
