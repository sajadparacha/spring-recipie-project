package com.spring5.springrecepie.services;

import com.spring5.springrecepie.domain.Recipe;
import com.spring5.springrecepie.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RecepieService {
    RecipeRepository recipeRepository;

    public RecepieService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getRecipes(){
        return (List<Recipe>) recipeRepository.findAll();
    }
}
