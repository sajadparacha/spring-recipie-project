package com.spring5.springrecepie.controllers;

import com.spring5.springrecepie.domain.Category;
import com.spring5.springrecepie.domain.Recipe;
import com.spring5.springrecepie.domain.UnitOfMeasure;
import com.spring5.springrecepie.exceptions.NotFoundException;
import com.spring5.springrecepie.repositories.CategoryRepository;
import com.spring5.springrecepie.repositories.UnitOfMeasureRepository;
import com.spring5.springrecepie.services.RecipeService;
import com.spring5.springrecepie.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;
@Slf4j
@Controller
public class RecipeController {
    CategoryRepository categoryRepository;
    UnitOfMeasureRepository unitOfMeasureRepository;
    RecipeService recipeService;
    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";
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
    public String createRecipe(@Valid @ModelAttribute Recipe recipe, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return RECIPE_RECIPEFORM_URL;
        }
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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)

    public ModelAndView handleNotFound(Exception e){
            log.error("Handeling NOt Found Exception");
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.setViewName("404error");
            modelAndView.addObject("exception",e);
            return modelAndView;
    }



}
