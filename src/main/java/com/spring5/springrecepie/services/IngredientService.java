package com.spring5.springrecepie.services;

import com.spring5.springrecepie.domain.Ingredients;

public interface IngredientService {
    Ingredients findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    Ingredients saveIngredient(Ingredients ingredients,Long recipeId);
}
