package com.spring5.springrecepie.controllers;

import com.spring5.springrecepie.domain.Ingredients;
import com.spring5.springrecepie.domain.Recipe;
import com.spring5.springrecepie.services.IngredientService;
import com.spring5.springrecepie.services.RecipeService;
import com.spring5.springrecepie.services.UOMService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@Slf4j
class IngredientControllerTest {



    @Mock
    RecipeService recipeService;
    @Mock
    IngredientService  ingredientService;
    @Mock
    UOMService uomService;
    @Mock
    RecipeController recipeController;
    @Mock
    Model model;
    MockMvc mockMvc;
    IngredientController ingredientController;
    @Test
    void getRecipeIngrediants() {
    }
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientController = new IngredientController( recipeService,ingredientService,uomService );
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController)
                //**Test will fail without addind the below Adice for this controller
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }
    @Test
    void testShowIngredient() throws Exception {
       //Given
        Long recipeId=1L;
        Long ingredientId=1L;

        //When
        mockMvc.perform(
                get("/recipe/"+recipeId+"/ingredient/"+ingredientId+"/show"))
//        get("/recipe/1/ingredient/2/show"))
        //Then
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                //.andExpect(model().attributeExists("ingredient"))
        ;

        //Then

        Mockito.verify(ingredientService, Mockito.times(1)).findByRecipeIdAndIngredientId(any(),any());

    }
    @Test
    void testDeleteIngredientByID() throws Exception {
        //Given
        Long idToBeDeleted=1L;
        Long recipeId=1L;
        //When
        //when(recipeService.deleteRecipeById(any());).thenReturn(updatedRecipe);
        MockMvc mockMvc=MockMvcBuilders.standaloneSetup(ingredientController).build();
        //mockMvc.perform(get("/recipe/1/ingredients"))

        mockMvc.perform(get("/recipe/"+recipeId+"/ingredient/"+idToBeDeleted+"/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/"+recipeId+"/ingredients"))
        ;


        //Then

        Mockito.verify(recipeService,times(1)).deleteIngredientByID(anyLong());


    }


    @Test
    void showIngredient() throws Exception {
        //Given
        Ingredients ingredients=new Ingredients();
        //When
        //when(ingredientService.)

        mockMvc.perform(get("/recipe/1/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingridientForm"))
                .andExpect(model().attributeExists("ingredient"))
        .andExpect(model().attributeExists("recipeId"));
        //Then
    }

    @Test
    void editIngredient() throws Exception {
        log.debug(">>>>>> Running editIngredient test");
        //Given
        Long recipeId=1L;
        Long ingredientId=1L;
        Ingredients ingredients=new Ingredients();
        ingredients.setId(ingredientId);
        when(ingredientService.findByRecipeIdAndIngredientId(any(),any())).thenReturn(ingredients);
        //When
        mockMvc.perform(get("/recipe/"+recipeId+"/ingredient/"+ingredientId+"/update"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("recipeId"))
                .andExpect(model().attributeExists("uomList"))
                .andExpect(view().name("recipe/ingredient/ingridientForm"));
        //Then

    }

    @Test
    void saveOrUpdateIngredient() throws Exception {
        //Given
        Recipe recipe=new Recipe();
        recipe.setId(1L);
        Ingredients ingredients=new Ingredients();
        ingredients.setId(1L);
        ingredients.setRecipe(recipe);
        when(ingredientService.saveIngredient(any(),any())).thenReturn(ingredients);

        //When
        mockMvc.perform(get("/recipe/1/ingredient"))
        //Then
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("ingredients"))
                .andExpect(view().name("redirect:/recipe/1/ingredient/"+ingredients.getId()+"/show"));


    }
}