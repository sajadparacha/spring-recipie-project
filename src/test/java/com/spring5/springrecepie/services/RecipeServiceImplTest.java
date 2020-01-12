package com.spring5.springrecepie.services;

import com.spring5.springrecepie.domain.Recipe;
import com.spring5.springrecepie.repositories.IngredientRepository;
import com.spring5.springrecepie.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    /**
     * The line below will create a Mock object for rge RecipeRepository
     */
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    IngredientRepository ingredientRepository;
    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        recipeService= new RecipeServiceImpl(recipeRepository,ingredientRepository);
    }

    @Test
    void getRecipes() {

        List<Recipe> recipes=recipeService.getRecipes();
        assertEquals(recipes.size(),0);

    }
    @Test
    void getRecipeByIDTest(){
        //Given , we have below recipie
        Recipe recipe=new Recipe();
        recipe.setId(1L);
        //**Create an optional object
        Optional<Recipe> recipeOptional=Optional.of(recipe);
        //when
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //then
        Recipe recipeReturned=recipeService.getRecipeById(1L);
        //recipeRepository.findAll();
        //**Not null check
        assertNotNull(recipeReturned);
        //**check that the service method is only called once
        verify(recipeRepository,times(1)).findById(anyLong());
        //**check that findall was never called
        verify(recipeRepository,never()).findAll();
        //**check that the found recipe is what we were expecting
        assertEquals(1L,recipeReturned.getId());
    }

    @Test
    void testDeleteRecipeById(){
        //Given
        Long idToBeDeleted=1L;
        //When , since the delete method returns void we cant have when here but
        // verify that the delete method was actially called
        recipeService.deleteRecipeById(idToBeDeleted);
        //when(recipeService.deleteRecipeById(recipeId)).thenReturn(idToBeDeleted);

        //Then
        verify(recipeRepository,times(1)).deleteById(any());
    }
    @Test
    void testDeleteIngredientByID (){
        Long idToBeDeleted=1L;

        recipeService.deleteIngredientByID(idToBeDeleted);
        verify(ingredientRepository,times(1)).deleteById(any());
    }
}