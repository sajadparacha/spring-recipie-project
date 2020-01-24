package com.spring5.springrecepie.controllers;

import com.spring5.springrecepie.domain.Recipe;
import com.spring5.springrecepie.exceptions.NotFoundException;
import com.spring5.springrecepie.repositories.CategoryRepository;
import com.spring5.springrecepie.repositories.RecipeRepository;
import com.spring5.springrecepie.repositories.UnitOfMeasureRepository;
import com.spring5.springrecepie.services.IngredientService;
import com.spring5.springrecepie.services.RecipeService;
import com.spring5.springrecepie.services.RecipeServiceImpl;
import com.spring5.springrecepie.services.UOMService;
import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecipeControllerTest {

    @Mock
    CategoryRepository categoryRepository;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;
    @Mock
    RecipeServiceImpl recipeService;

    RecipeController recipeController;

    IngredientController ingredientController;
    @Mock
    IngredientService ingredientService;
    @Mock
    UOMService uomService;
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    Model model;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController=new RecipeController(categoryRepository,unitOfMeasureRepository,recipeService);
        ingredientController=new IngredientController(recipeService,ingredientService,uomService);
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
//    @Test
//    void findByIdTest(){
//        //Given
//        String expectedReturnedPage="recipe/show";
//        //When
//        String returnedPageFromController=recipeController.showRecipe("1",model);
//        //Then
//        assertEquals(expectedReturnedPage,returnedPageFromController);
//        verify(recipeService,times(1)).getRecipeById(anyLong());
//
//    }
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
    @Test
    void testGetRecipeNotFound() throws Exception {
        //Given
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(recipeController).build();
        when(recipeService.getRecipeById(anyLong())).thenThrow(NotFoundException .class);

        //When
          mockMvc.perform(
                get("/recipe/1/show"))
                //Then
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));

    }
    @Test
    void testNumberFormatException() throws Exception {
        //Given
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(recipeController).build();
       // when(recipeService.getRecipeById(any())).thenThrow(NumberFormatException .class);

        //When
        mockMvc.perform(
                get("/recipe/1gdgfd/show"))
                //Then
                .andExpect(status().isBadRequest())
                .andExpect(view().name("error"));

    }
    @Test
    void createRecipeTest() throws Exception {
        //Given
        Recipe recipeToBeSaved= new Recipe();
        recipeToBeSaved.setId(10L);
        recipeToBeSaved.setDescription("Recipe To Be Saved");
        //When
        when(recipeService.saveRecipe(any())).thenReturn(recipeToBeSaved);
        //Then
        MockMvc mockMvc=MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(
                post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id","10")
                .param("description","Recipe To Be Saved")

        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/"+recipeToBeSaved.getId()+"/show"))
               ;

    }
    @Test
    void testGetUpdateView() throws Exception {
        //Given
        Recipe updatedRecipe = new Recipe();
        updatedRecipe.setId(1L);
        updatedRecipe.setDescription("Updated Recipe");
        //When
        when(recipeService.getRecipeById(any())).thenReturn(updatedRecipe);
        //Then
        MockMvc mockMvc=MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(get("/recipe/"+updatedRecipe.getId()+"/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeForm"))
                .andExpect(model().attributeExists("recipe"));

    }
    @Test
    void testDeleteRecipeByID() throws Exception {
        //Given
        Long idToBeDeleted=1L;
        //When
        //when(recipeService.deleteRecipeById(any());).thenReturn(updatedRecipe);
        MockMvc mockMvc=MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(get("/recipe/"+idToBeDeleted+"/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index.html"));


        //Then

        verify(recipeService,times(1)).deleteRecipeById(any());

    }


    @Test
    void testRecipieList() throws Exception {
        //given
        Recipe  recipe=new Recipe();
        //when
        when(recipeService.getRecipeById(anyLong())).thenReturn(recipe);
        //then
        MockMvc mockMvc=MockMvcBuilders.standaloneSetup(ingredientController).build();
        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));
        verify(recipeService,times(1)).getRecipeById(any());
    }





}