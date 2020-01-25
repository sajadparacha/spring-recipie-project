package com.spring5.springrecepie.controllers;

import com.spring5.springrecepie.domain.Ingredients;
import com.spring5.springrecepie.domain.Recipe;
import com.spring5.springrecepie.services.IngredientService;
import com.spring5.springrecepie.services.RecipeService;
import com.spring5.springrecepie.services.UOMService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {
    RecipeService recipeService;
    IngredientService ingredientService;
    UOMService uomService;
    public IngredientController(RecipeService recipeService,IngredientService ingredientService,UOMService uomService) {
        this.recipeService = recipeService;
        this.ingredientService=ingredientService;
        this.uomService=uomService;
    }
    @RequestMapping("recipe/{id}/ingredients")
    public String getRecipeIngrediants(@PathVariable String id, Model model){
        System.out.println("getRecipeIngridiants called");
        model.addAttribute("recipe",recipeService.getRecipeById(new Long(id)));
        return "recipe/ingredient/list";
    }
    @RequestMapping("recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String  recipeId,Model model){

        model.addAttribute("ingredient",new Ingredients());
        model.addAttribute("recipeId",recipeId);
        model.addAttribute("uomList",uomService.listAllUoms());
        return "recipe/ingredient/ingridientForm";
    }
    @RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String editIngredient(@PathVariable String recipeId,@PathVariable String ingredientId,Model model){
       log.debug(">>>>>> editIngredient is called");
        Ingredients ingredient = ingredientService.findByRecipeIdAndIngredientId(new Long(recipeId),new Long(ingredientId));
        model.addAttribute("ingredient",ingredient);
        model.addAttribute("recipeId",recipeId);
        model.addAttribute("uomList",uomService.listAllUoms());

        return "recipe/ingredient/ingridientForm";
    }
    @RequestMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdateIngredient(@PathVariable String recipeId,@ModelAttribute Ingredients ingredient,Model model){
        Ingredients ingredients = ingredientService.saveIngredient(ingredient,new Long(recipeId));
        model.addAttribute("ingredients",ingredients);
       // model.addAttribute("uomList",uomService.listAllUoms());
        //return"/recipe/"+ingredients.getId()+"/show";
        String redirectString="/recipe/"+ingredients.getRecipe().getId()+"/ingredient/"+ingredients.getId()+"/show";
        return "redirect:"+redirectString;

    }
    @RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredientByID(@PathVariable String recipeId,@PathVariable String ingredientId){
        recipeService.deleteIngredientByID(new Long(ingredientId));
        return "redirect:/recipe/"+recipeId+"/ingredients";
    }
    @RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable String recipeId,@PathVariable String ingredientId){
        ingredientService.findByRecipeIdAndIngredientId(new Long(recipeId),new Long(ingredientId));
        return "recipe/ingredient/show";
    }


}
