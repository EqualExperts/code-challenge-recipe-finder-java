package com.equalexperts.recipefinder.controller;

import com.equalexperts.recipefinder.dto.RecipeDTO;
import com.equalexperts.recipefinder.entity.Diet;
import com.equalexperts.recipefinder.entity.Ingredient;
import com.equalexperts.recipefinder.entity.Method;
import com.equalexperts.recipefinder.entity.Recipe;
import com.equalexperts.recipefinder.integration.RecipeAPIClient;
import com.equalexperts.recipefinder.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Controller()
public class RecipeSearchController {
    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeSearchController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/recipes")
    public String search(@RequestParam(name="query", required=false) String query, Model model) {
        List<Recipe> recipes = recipeRepository.findAll();
        model.addAttribute("recipes", recipes.stream().filter(recipe -> {
            if (query == null) {
                return true;
            }
            if (recipe.getName().toLowerCase().contains(query.toLowerCase())) return true;
            if (recipe.getDescription().toLowerCase().contains(query.toLowerCase())) return true;
            long count = 0L;
            for (Ingredient x : recipe.getIngredients()) {
                if (x.getName().toLowerCase().contains(query.toLowerCase())) {
                    count++;
                }
            }
            if (count > 0) return true;
            count = 0;
            for (Method m : recipe.getMethod()) {
                if (m.getDescription().toLowerCase().contains(query.toLowerCase())) {
                    count++;
                }
            }
            if (count > 0) return true;
            count = 0;
            for (Diet diet : recipe.getDiets()) {
                if (diet.getName().toLowerCase().contains(query.toLowerCase())) {
                    count++;
                }
            }
            if (count > 0) return true;
            count = 0;

            return false;
        }).collect(toList()));

        return "recipe-search";
    }
}
