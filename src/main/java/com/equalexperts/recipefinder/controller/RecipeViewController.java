package com.equalexperts.recipefinder.controller;

import com.equalexperts.recipefinder.entity.Recipe;
import com.equalexperts.recipefinder.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller()
public class RecipeViewController {
    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeViewController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/recipe")
    public String view(@RequestParam(name="id", required=false) Long id, Model model) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        model.addAttribute("recipe", recipe.get());
        return "recipe-view";
    }
}
