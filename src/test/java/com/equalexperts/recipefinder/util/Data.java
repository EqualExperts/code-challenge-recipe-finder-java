package com.equalexperts.recipefinder.util;

import com.equalexperts.recipefinder.dto.*;
import com.equalexperts.recipefinder.entity.Recipe;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static RecipeDTO avoOnToastRecipeDTO() {
        final RecipeDTO fakeRecipe = new RecipeDTO();
        fakeRecipe.setName("Avo on Toast");
        fakeRecipe.setDescription("Delicious easy and scrumptious breakfast.");
        fakeRecipe.setId("AVO_TOAST");
        fakeRecipe.setServings(1L);
        fakeRecipe.setCookingTimeMin(5L);
        fakeRecipe.setPreparationTimeMin(5L);
        fakeRecipe.setEstimatedCostDollars(5L);
        return fakeRecipe;
    }

    public static RecipeDTO burntToastRecipeDTO() {
        final RecipeDTO fakeRecipe = new RecipeDTO();
        fakeRecipe.setName("Burnt Toast");
        fakeRecipe.setDescription("Carcinogenic feast");
        fakeRecipe.setId("BURNT_TOAST");
        fakeRecipe.setServings(1L);
        fakeRecipe.setCookingTimeMin(50L);
        fakeRecipe.setPreparationTimeMin(0L);
        fakeRecipe.setEstimatedCostDollars(1L);
        return fakeRecipe;
    }

    public static MethodDTO avoOnToastMethod() {
        final MethodDTO method = new MethodDTO();
        method.setMethod(List.of(
                "Slice two pieces of bread",
                "Pop in toaster until golden brown",
                "Slice Avo and place on toast",
                "Salt, Pepper, Coriander to taste",
                "Sprinkle some chives",
                "Enjoy"
        ));
        return method;
    }

    public static MethodDTO burntToastMethod() {
        final MethodDTO method = new MethodDTO();
        method.setMethod(List.of(
                "Slice two pieces of bread",
                "Pop in toaster until charcoal, about 50 minutes"
        ));
        return method;
    }

    public static DietsDTO avoOnToastDiets() {
        final DietsDTO diets = new DietsDTO();
        diets.setDiets(List.of("Healthy", "Vegan"));
        return diets;
    }

    public static DietsDTO burntToastDiets() {
        final DietsDTO diets = new DietsDTO();
        diets.setDiets(List.of("Unhealthy", "Deadly"));
        return diets;
    }

    public static IngredientsDTO avoOnToastIngredients() {
        final IngredientsDTO ingredients = new IngredientsDTO();
        List<IngredientDTO> ingredientsList = new ArrayList<>();
        IngredientDTO bread = new IngredientDTO();
            bread.setUnitOfMeasurement("slice");
            bread.setQuantity(2L);
            bread.setName("Bread");
            ingredientsList.add(bread);
        IngredientDTO avo = new IngredientDTO();
            avo.setQuantity(1L);
            avo.setName("Avocado");
            ingredientsList.add(avo);
            ingredients.setIngredients(ingredientsList);
        return ingredients;
    }

    public static IngredientsDTO burntToastIngredients() {
        final IngredientsDTO ingredients = new IngredientsDTO();
        List<IngredientDTO> ingredientsList = new ArrayList<>();
        IngredientDTO bread = new IngredientDTO();
        bread.setUnitOfMeasurement("slice");
        bread.setQuantity(2L);
        bread.setName("Bread");
        ingredientsList.add(bread);
        ingredients.setIngredients(ingredientsList);
        return ingredients;
    }
}
