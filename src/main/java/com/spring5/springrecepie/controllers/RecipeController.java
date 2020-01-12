package com.spring5.springrecepie.controllers;

import com.spring5.springrecepie.domain.Category;
import com.spring5.springrecepie.domain.Recipe;
import com.spring5.springrecepie.domain.UnitOfMeasure;
import com.spring5.springrecepie.repositories.CategoryRepository;
import com.spring5.springrecepie.repositories.UnitOfMeasureRepository;
import com.spring5.springrecepie.services.RecipeService;
import com.spring5.springrecepie.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Slf4j
@Controller
public class RecipeController {
    CategoryRepository categoryRepository;
    UnitOfMeasureRepository unitOfMeasureRepository;
    RecipeService recipeService;

    public RecipeController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recepieService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService =recepieService;
    }

    @RequestMapping({"/","/index","/index.html"})
    public String showIndex(Model model){
         Optional<Category> category = categoryRepository.findByDescription("American");
         Optional<UnitOfMeasure> unitOfMeasure=unitOfMeasureRepository.findByDescription("Teaspoon");
        log.debug("Category find by description = "+category.get().getId());

        model.addAttribute("recipies", recipeService.getRecipes());

        log.debug("UnitOfMeasure find by description "+unitOfMeasure.get().getId());

        return "index";
    }

    @RequestMapping("/showAPage")
    public String showAPage(Model model){
//**bjbjhbj
        return "index";

    }

    /**
     * A good example of restful URL here i.e controllername/id/verb like show which smean show me the recipie of this id
     * @param id
     * @param model
     * @return
     */

    @RequestMapping("/recipe/{id}/show")
    public String showRecipe(@PathVariable String id, Model model){
        System.out.println("showRecipe called");
        model.addAttribute("recipie",recipeService.getRecipeById(new Long(id)));
        return "recipe/show";
    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new Recipe());
       return "recipe/new";
    }
    @PostMapping("recipe")
    public String createRecipe(@ModelAttribute Recipe recipe){
        recipe=recipeService.saveRecipe(recipe);
        return "redirect:/recipe/"+recipe.getId()+"/show";
    }
    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        System.out.println("updateRecipe called");
        model.addAttribute("recipe",recipeService.getRecipeById(new Long(id)));
        return "recipe/recipeForm";
    }
    @RequestMapping("/recipe/{id}/delete")
    public String deleteRecipeByID(@PathVariable String id){
        recipeService.deleteRecipeById(new Long(id));
        return "redirect:/index.html";
    }







}
