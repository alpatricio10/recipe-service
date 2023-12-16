package com.recime.recipeservice.repository;

import com.recime.recipeservice.data.DifficultyType;
import com.recime.recipeservice.data.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByOrderByPositionAsc();

    List<Recipe> findByDifficultyOrderByPositionAsc(DifficultyType difficulty);

}
