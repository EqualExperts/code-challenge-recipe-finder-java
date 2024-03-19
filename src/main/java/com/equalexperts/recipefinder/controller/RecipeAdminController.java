package com.equalexperts.recipefinder.controller;

import com.equalexperts.recipefinder.dto.DietsDTO;
import com.equalexperts.recipefinder.dto.IngredientsDTO;
import com.equalexperts.recipefinder.dto.MethodDTO;
import com.equalexperts.recipefinder.dto.RecipeDTO;
import com.equalexperts.recipefinder.entity.Diet;
import com.equalexperts.recipefinder.entity.Ingredient;
import com.equalexperts.recipefinder.entity.Method;
import com.equalexperts.recipefinder.entity.Recipe;
import com.equalexperts.recipefinder.integration.RecipeAPIClient;
import com.equalexperts.recipefinder.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Controller()
public class RecipeAdminController {
    private RecipeAPIClient recipeAPIClient;
    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeAdminController(RecipeAPIClient client, RecipeRepository recipeRepository) {
        this.recipeAPIClient = client;
        this.recipeRepository = recipeRepository;
    }

    @Transactional
    public Recipe persist(Recipe recipe) {
        recipe = this.recipeRepository.save(recipe);
        System.out.printf("Saved Recipe %d%n", recipe.getId());
        return recipe;
    }

    @Async
    protected void enrich(RecipeDTO dto) {
        Recipe recipe = new Recipe();
        recipe.setName(dto.getName());
        recipe.setDescription(dto.getDescription());
        recipe.setServings(dto.getServings());
        recipe.setCookingTimeMin(dto.getCookingTimeMin());
        recipe.setPrepTimeMin(dto.getPreparationTimeMin());
        recipe.setEstimatedCostDollars(dto.getEstimatedCostDollars());

        recipe = persist(recipe);

        System.out.println(dto);

        IngredientsDTO ingredients = this.recipeAPIClient.getIngredients(dto.getId()).block();
        assert ingredients != null;
        Recipe finalRecipe = recipe;
        recipe.setIngredients(ingredients.getIngredients().stream().map(it -> {
            Ingredient ingredient = new Ingredient();
            ingredient.setQuantity(it.getQuantity());
            ingredient.setName(it.getName());
            ingredient.setUnitOfMeasurement(it.getUnitOfMeasurement());
            ingredient.setRecipe(finalRecipe);
            return ingredient;
        }).collect(toSet()));
        System.out.println(ingredients);

        DietsDTO diets = this.recipeAPIClient.getDiets(dto.getId()).block();
        assert diets != null;
        recipe.setDiets(diets.getDiets().stream().map(it -> {
            Diet diet = new Diet();
            diet.setName(it);
            diet.setRecipe(finalRecipe);
            return diet;
        }).collect(toSet()));
        System.out.println(diets);

        MethodDTO method = this.recipeAPIClient.getMethod(dto.getId()).block();
        assert method != null;
        recipe.setMethod(method.getMethod().stream().map(it-> {
            Method m = new Method();
            m.setDescription(it);
            m.setRecipe(finalRecipe);
            return m;
        }).collect(toSet()));

        System.out.println(method);
        System.out.println("=====================================================================================");

        persist(recipe);
    }

    @GetMapping("/admin/import")
    public String downloadRecipes(Model model) {
        List<RecipeDTO> output = this.recipeAPIClient.getRecipeList().block();
        if (output == null) {
            model.addAttribute("numDownloads", 0);
            return "download";
        }
        output.forEach(this::enrich);
        model.addAttribute("numDownloads", output.size());
        return "download";
    }
}
