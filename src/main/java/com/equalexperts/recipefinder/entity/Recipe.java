package com.equalexperts.recipefinder.entity;

import com.equalexperts.recipefinder.dto.DietsDTO;
import com.equalexperts.recipefinder.dto.IngredientsDTO;
import com.equalexperts.recipefinder.dto.MethodDTO;
import com.equalexperts.recipefinder.dto.RecipeDTO;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private Long servings;
    private Long cookingTimeMin;
    private Long prepTimeMin;
    private Long estimatedCostDollars;

    @OneToMany(mappedBy="recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Ingredient> ingredients;

    @OneToMany(mappedBy="recipe", cascade = CascadeType.ALL)
    private Set<Method> method;

    @OneToMany(mappedBy="recipe", cascade = CascadeType.ALL)
    private Set<Diet> diets;

    public Recipe() {

    }
    public Recipe(RecipeDTO dto) {
        this.setName(dto.getName());
        this.setDescription(dto.getDescription());
        this.setCookingTimeMin(dto.getCookingTimeMin());
        this.setEstimatedCostDollars(dto.getEstimatedCostDollars());
        this.setPrepTimeMin(dto.getPreparationTimeMin());
        this.setServings(dto.getServings());
    }

    public Recipe setIngredients(IngredientsDTO dto) {
        this.setIngredients(dto.getIngredients().stream().map(ingredientDTO -> {
            Ingredient i = new Ingredient();
            i.setName(ingredientDTO.getName());
            i.setQuantity(ingredientDTO.getQuantity());
            i.setUnitOfMeasurement(ingredientDTO.getUnitOfMeasurement());
            i.setRecipe(this);
            return i;
        }).collect(Collectors.toSet()));
        return this;
    }

    public Recipe setMethod(MethodDTO dto) {
        this.setMethod(dto.getMethod().stream().map(x -> {
            Method m =new Method();
            m.setRecipe(this);
            m.setDescription(x);
            return m;
        }).collect(Collectors.toSet()));
        return this;
    }

    public Recipe setDiets(DietsDTO dto) {
        this.setDiets(dto.getDiets().stream().map(x -> {
            Diet d = new Diet();
            d.setName(x);
            return d;
        }).collect(Collectors.toSet()));
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<Method> getMethod() {
        return method;
    }

    public void setMethod(Set<Method> method) {
        this.method = method;
    }

    public Set<Diet> getDiets() {
        return diets;
    }

    public void setDiets(Set<Diet> diets) {
        this.diets = diets;
    }

    public Long getPrepTimeMin() {
        return prepTimeMin;
    }

    public void setPrepTimeMin(Long prepTimeMin) {
        this.prepTimeMin = prepTimeMin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id) && Objects.equals(name, recipe.name) && Objects.equals(description, recipe.description) && Objects.equals(servings, recipe.servings) && Objects.equals(cookingTimeMin, recipe.cookingTimeMin) && Objects.equals(prepTimeMin, recipe.prepTimeMin) && Objects.equals(estimatedCostDollars, recipe.estimatedCostDollars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, servings, cookingTimeMin, prepTimeMin, estimatedCostDollars);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", servings=" + servings +
                ", cookingTimeMin=" + cookingTimeMin +
                ", prepTimeMin=" + prepTimeMin +
                ", estimatedCostDollars=" + estimatedCostDollars +
                '}';
    }
}
