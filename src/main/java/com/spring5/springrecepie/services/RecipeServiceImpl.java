package com.spring5.springrecepie.services;

import com.spring5.springrecepie.domain.Ingredients;
import com.spring5.springrecepie.domain.Recipe;
import com.spring5.springrecepie.repositories.IngredientRepository;
import com.spring5.springrecepie.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Lombok annotation which enables logging
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    RecipeRepository recipeRepository;
    IngredientRepository ingredientRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository,IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository=ingredientRepository;
    }

    public List<Recipe> getRecipes(){

        log.debug("Getting recipes from repository");
        return (List<Recipe>) recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(Long aLong) {
        System.out.println("getRecipeById called");
        Optional<Recipe> recipeOptional=recipeRepository.findById(aLong);
        //**Defensive coding , ceck if no recipe found throw exception
        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe not found for Id = "+aLong);
        }
        return recipeOptional.get();
    }

    public Recipe saveRecipe(Recipe recipe){
        recipe = recipeRepository.save(recipe);
        return recipe;
    }
    public void deleteRecipeById(Long recipeId){
        log.debug(">>> RecipeServiceImpl.deleteRecipeById methos is called");
        recipeRepository.deleteById(recipeId);
    }
    public void deleteIngredientByID(Long ingredientId){
        log.debug(">>> RecipeServiceImpl.deleteIngredientByID methos is called");
        ingredientRepository.deleteById(ingredientId);
    }
//
//    @Override
//    public Ingredients findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
//
//        Optional<Recipe> recipeOptional=recipeRepository.findById(recipeId);
//        //**Check if the optional object is not present throw a runtime exception
//        if(!recipeOptional.isPresent()){
//            throw  new RuntimeException("Recipe With id "+recipeId+" Not Found");
//        }
//        //**use Stream and filter
//        Recipe recipe= recipeOptional.get();
//        Optional<Ingredients> ingredientsOptional = recipe.getIngredients().stream()
//                .filter(ingredients -> ingredients.getId().equals(ingredientId)).findFirst();
//        if(!ingredientsOptional.isPresent()){
//            throw new RuntimeException("No Ingredient for Id "+ingredientId+" found");
//        }
//
//        return ingredientsOptional.get();
//    }
//

}
