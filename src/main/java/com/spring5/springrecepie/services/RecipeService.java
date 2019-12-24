package com.spring5.springrecepie.services;

import com.spring5.springrecepie.domain.Recipe;

import java.util.List;

public interface RecipeService {
    public List<Recipe> getRecipes();

    Recipe getRecipeById(Long aLong);
}
