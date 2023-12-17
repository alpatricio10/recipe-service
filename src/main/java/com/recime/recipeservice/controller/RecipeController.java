package com.recime.recipeservice.controller;

import com.recime.recipeservice.data.DifficultyType;
import com.recime.recipeservice.data.GetRecipeResponse;
import com.recime.recipeservice.data.Recipe;
import com.recime.recipeservice.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RecipeController {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("recipe")
    public ResponseEntity<GetRecipeResponse> getRecipes() {

        List<Recipe> data = recipeRepository.findByOrderByPositionAsc();

        GetRecipeResponse response = new GetRecipeResponse();
        response.setData(data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("recipeByDifficulty")
    public ResponseEntity<GetRecipeResponse> getRecipesByDifficulty(@RequestParam String difficulty) {

        if (difficulty == null || difficulty.isEmpty()) {
            throw new IllegalArgumentException("A difficulty is required for filtering trending recipes");
        }

        List<Recipe> data = recipeRepository.findByDifficultyOrderByPositionAsc(DifficultyType.valueOf(difficulty.toUpperCase()));

        GetRecipeResponse response = new GetRecipeResponse();
        response.setData(data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
