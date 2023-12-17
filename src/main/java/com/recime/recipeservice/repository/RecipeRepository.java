package com.recime.recipeservice.repository;

import com.recime.recipeservice.data.DifficultyType;
import com.recime.recipeservice.data.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Page<Recipe> findByOrderByPositionAsc(Pageable pageable);

    Page<Recipe> findByDifficultyOrderByPositionAsc(DifficultyType difficulty, Pageable pageable);

}
