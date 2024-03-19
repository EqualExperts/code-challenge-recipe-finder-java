package com.equalexperts.recipefinder.repository;

import com.equalexperts.recipefinder.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
