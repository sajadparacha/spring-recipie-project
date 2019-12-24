package com.spring5.springrecepie.controllers;

import com.spring5.springrecepie.domain.Recipe;
import com.spring5.springrecepie.repositories.CategoryRepository;
import com.spring5.springrecepie.repositories.UnitOfMeasureRepository;
import com.spring5.springrecepie.services.RecipeService;
import com.spring5.springrecepie.services.RecipeServiceImpl;
import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecipeControllerTest {

    @Mock
    CategoryRepository categoryRepository;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;
    @Mock
    RecipeServiceImpl recipeService;
    @Mock
    RecipeController recipeController;
    @Mock
    Model model;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController=new RecipeController(categoryRepository,unitOfMeasureRepository,recipeService);
    }

    @Test
    void showAPage() {
        String viewName=recipeController.showAPage(model);
        //Given
        Set<Recipe> recipes= new HashSet<>();
        Recipe recipe1=new Recipe();
        recipes.add(recipe1);

        Recipe recipe2 = new Recipe();
        recipe2.setId(5L);
        recipes.add(recipe2);
        //When

        ArgumentCaptor<Set> setArgumentCaptor=ArgumentCaptor.forClass(Set.class);
        //Then


        //assertEquals("index",viewName);
    }
    @Test
    void findByIdTest(){
        //Given
        String expectedReturnedPage="recipe/show";
        //When
        String returnedPageFromController=recipeController.showRecipe("1",model);
        //Then
        assertEquals(expectedReturnedPage,returnedPageFromController);
        verify(recipeService,times(1)).getRecipeById(anyLong());

    }
    @Test
    void testGetRecipe() throws Exception {
        //Given
        Recipe recipe=new Recipe();
        recipe.setId(1L);
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(recipeController).build();
        //When
        when(recipeService.getRecipeById(anyLong())).thenReturn(recipe);
        mockMvc.perform(
                get("/recipe/1/show"))
        //Then
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipie"));
    }

}