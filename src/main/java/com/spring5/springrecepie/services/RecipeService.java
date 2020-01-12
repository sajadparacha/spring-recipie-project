package com.spring5.springrecepie.services;

import com.spring5.springrecepie.domain.Ingredients;
import com.spring5.springrecepie.domain.Recipe;

import java.util.List;

public interface RecipeService {
    public List<Recipe> getRecipes();
    public Recipe saveRecipe(Recipe recipe);
    Recipe getRecipeById(Long aLong);
    void deleteRecipeById(Long recipeId);

    void deleteIngredientByID(Long ingredientId);

}
