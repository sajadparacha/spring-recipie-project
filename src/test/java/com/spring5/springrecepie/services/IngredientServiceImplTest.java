package com.spring5.springrecepie.services;

import com.spring5.springrecepie.domain.Ingredients;
import com.spring5.springrecepie.domain.Recipe;
import com.spring5.springrecepie.repositories.IngredientRepository;
import com.spring5.springrecepie.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class IngredientServiceImplTest {
    @Mock
    IngredientRepository ingredientRepository;
    @Mock
    RecipeRepository  recipeRepository;
    IngredientService ingredientService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService= new IngredientServiceImpl(recipeRepository);
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        //Given
        //Prepare the Recipe object with ingredient in it
        //Test if there are ingrdients it should pass

        Long recipeId=1L;
        Long ingredientId=1L;
        Recipe recipe= new Recipe();
        recipe.setId(recipeId);
        Set<Ingredients> ingredientsSet=new HashSet<>();
        Ingredients ingredients=new Ingredients();
        ingredients.setId(ingredientId);
        ingredientsSet.add(ingredients);
        recipe.setIngredients(ingredientsSet);
        //Prepare Mock Interection
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        //When
        Ingredients ingredientFoundById=ingredientService.findByRecipeIdAndIngredientId(recipeId,ingredientId);
        //Then
        assertEquals(ingredientFoundById.getId(),recipe.getId());
        Mockito.verify(recipeRepository,times(1)).findById(anyLong());

    }

    @Test
    void saveIngredient() {
        //Given
        //**Create the recipe Object and add ingredient to it
        Recipe recipe= new Recipe();
        Ingredients ingredients=new Ingredients();
        recipe.setId(1L);
        ingredients.setId(1L);
        ingredients.setRecipe(recipe);
        Set<Ingredients> ingredientsSet=new HashSet<>();
        ingredientsSet.add(ingredients);
        recipe.setIngredients(ingredientsSet);
        Optional<Recipe> recipeOptional=Optional.of(recipe);
        when(recipeRepository.findById(any())).thenReturn(recipeOptional);
       when(recipeRepository.save(any())).thenReturn(recipe);

        //When
        Ingredients ingredientsSaved=ingredientService.saveIngredient(ingredients,1L);
        //Then
        assertEquals(ingredientsSaved.getId(),ingredients.getId());
        Mockito.verify(recipeRepository,times(1)).save(any());
    }
}