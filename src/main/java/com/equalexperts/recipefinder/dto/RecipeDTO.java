package com.equalexperts.recipefinder.dto;

import java.util.Objects;

public class RecipeDTO {
    private String id;
    private String name;
    private String description;
    private Long servings;
    private Long preparationTimeMin;
    private Long cookingTimeMin;
    private Long estimatedCostDollars;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getServings() {
        return servings;
    }

    public void setServings(Long servings) {
        this.servings = servings;
    }

    public Long getPreparationTimeMin() {
        return preparationTimeMin;
    }

    public void setPreparationTimeMin(Long preparationTimeMin) {
        this.preparationTimeMin = preparationTimeMin;
    }

    public Long getCookingTimeMin() {
        return cookingTimeMin;
    }

    public void setCookingTimeMin(Long cookingTimeMin) {
        this.cookingTimeMin = cookingTimeMin;
    }

    public Long getEstimatedCostDollars() {
        return estimatedCostDollars;
    }

    public void setEstimatedCostDollars(Long estimatedCostDollars) {
        this.estimatedCostDollars = estimatedCostDollars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeDTO recipeDTO = (RecipeDTO) o;
        return Objects.equals(id, recipeDTO.id) && Objects.equals(name, recipeDTO.name) && Objects.equals(description, recipeDTO.description) && Objects.equals(servings, recipeDTO.servings) && Objects.equals(preparationTimeMin, recipeDTO.preparationTimeMin) && Objects.equals(cookingTimeMin, recipeDTO.cookingTimeMin) && Objects.equals(estimatedCostDollars, recipeDTO.estimatedCostDollars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, servings, preparationTimeMin, cookingTimeMin, estimatedCostDollars);
    }

    @Override
    public String toString() {
        return "RecipeDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", servings=" + servings +
                ", preparationTimeMin=" + preparationTimeMin +
                ", cookingTimeMin=" + cookingTimeMin +
                ", estimatedCostDollars=" + estimatedCostDollars +
                '}';
    }
}
