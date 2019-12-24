package com.spring5.springrecepie.controllers;

import com.spring5.springrecepie.domain.Category;
import com.spring5.springrecepie.domain.UnitOfMeasure;
import com.spring5.springrecepie.repositories.CategoryRepository;
import com.spring5.springrecepie.repositories.UnitOfMeasureRepository;
import com.spring5.springrecepie.services.RecipeService;
import com.spring5.springrecepie.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
@Slf4j
@Controller
public class RecipeController {
    CategoryRepository categoryRepository;
    UnitOfMeasureRepository unitOfMeasureRepository;
    RecipeService recipeService;

    public RecipeController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeServiceImpl recepieService) {
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

    @RequestMapping("/recipe/{id}/show")
    public String showRecipe(@PathVariable String id, Model model){
        System.out.println("showRecipe called");
        model.addAttribute("recipie",recipeService.getRecipeById(new Long(id)));
        return "recipe/show";
    }

}
