package com.spring5.springrecepie.controllers;

import com.spring5.springrecepie.domain.Category;
import com.spring5.springrecepie.domain.UnitOfMeasure;
import com.spring5.springrecepie.repositories.CategoryRepository;
import com.spring5.springrecepie.repositories.UnitOfMeasureRepository;
import com.spring5.springrecepie.services.RecepieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class RecepieController {
    CategoryRepository categoryRepository;
    UnitOfMeasureRepository unitOfMeasureRepository;
    RecepieService recepieService;

    public RecepieController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository,RecepieService recepieService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recepieService=recepieService;
    }

    @RequestMapping({"/","/index","/index.html"})
    public String showIndex(Model model){
         Optional<Category> category = categoryRepository.findByDescription("American");
         Optional<UnitOfMeasure> unitOfMeasure=unitOfMeasureRepository.findByDescription("Teaspoon");
        System.out.println("Category find by description = "+category.get().getId());

        model.addAttribute("recipies",recepieService.getRecipes());

        System.out.println("UnitOfMeasure find by description "+unitOfMeasure.get().getId());

        return "index";
    }
}
