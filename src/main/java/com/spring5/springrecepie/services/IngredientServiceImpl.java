package com.spring5.springrecepie.services;

import com.spring5.springrecepie.domain.Ingredients;
import com.spring5.springrecepie.domain.Recipe;
import com.spring5.springrecepie.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {
    RecipeRepository recipeRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Ingredients findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional=recipeRepository.findById(recipeId);
        //**Check if the optional object is not present throw a runtime exception
        if(!recipeOptional.isPresent()){
            throw  new RuntimeException("Recipe With id "+recipeId+" Not Found");
        }
        //**use Stream and filter
        Recipe recipe= recipeOptional.get();
        Optional<Ingredients> ingredientsOptional = recipe.getIngredients().stream()
                .filter(ingredients -> ingredients.getId().equals(ingredientId)).findFirst();
        if(!ingredientsOptional.isPresent()){
            throw new RuntimeException("No Ingredient for Id "+ingredientId+" found");
        }

        return ingredientsOptional.get();
    }

    @Override
    public Ingredients saveIngredient(Ingredients ingredients,Long recipeId) {

        //**Get the Recipe Object
        Optional<Recipe> recipeOptional= recipeRepository.findById(recipeId);
        if(!recipeOptional.isPresent()){
            //**Write defensive code
            log.debug("No Recipe found");
            throw  new RuntimeException("No Recipe Found");
        }else{
            Recipe recipe=recipeOptional.get();
            //**Check if it's a new igredient or exisitng
            Optional<Ingredients> ingredientsOptional=recipe.getIngredients().stream()
                    .filter(ingredients1 -> ingredients1.getId().equals(ingredients.getId()))
                    .findFirst();
            if(ingredientsOptional.isPresent()){
                //**We need to update this ingridient
                Ingredients ingredientsToUpdate=ingredientsOptional.get();
                ingredientsToUpdate.setAmount(ingredients.getAmount());
                ingredientsToUpdate.setDescription(ingredients.getDescription());
                //**TODO set UOM here latter

            }else{
                //**We need to add this ingridient
                recipe.addIngrediant(ingredients);
            }
            //**Setting parent to child is important in case case a new ingridient is being saved
            //** As later when we try to get the recipe id from this object it will not result in NullPointer Exception
            ingredients.setRecipe(recipe);
            Recipe savedRecipe = recipeRepository.save(recipe);

            return savedRecipe.getIngredients().stream().
                    filter(ingredients1 -> ingredients1.getRecipeId().equals(recipeId)).findFirst().get();
        }

    }

}
