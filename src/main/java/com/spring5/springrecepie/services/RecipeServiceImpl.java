package com.spring5.springrecepie.services;

import com.spring5.springrecepie.domain.Recipe;
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

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
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
}
