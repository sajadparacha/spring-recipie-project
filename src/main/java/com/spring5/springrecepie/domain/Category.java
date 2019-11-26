package com.spring5.springrecepie.domain;

import javax.persistence.*;
import java.util.Set;
@Entity
public class Category {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String description;
    //**categories here is the name of the joinTable in the Recipe Class
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}
