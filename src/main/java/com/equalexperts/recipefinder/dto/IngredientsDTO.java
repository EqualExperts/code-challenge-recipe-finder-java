package com.equalexperts.recipefinder.dto;

import java.util.List;
import java.util.Objects;

public class IngredientsDTO {
    private List<IngredientDTO> ingredients;

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientsDTO that = (IngredientsDTO) o;
        return Objects.equals(ingredients, that.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredients);
    }

    @Override
    public String toString() {
        return "IngredientsDTO{" +
                "ingredients=" + ingredients +
                '}';
    }
}
