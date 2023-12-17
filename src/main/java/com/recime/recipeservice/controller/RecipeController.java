package com.recime.recipeservice.controller;

import com.recime.recipeservice.data.GetRecipeResponse;
import com.recime.recipeservice.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("recipe")
    public ResponseEntity<GetRecipeResponse> getRecipes(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit) {

        GetRecipeResponse response = recipeService.getRecipes(page, limit);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("recipeByDifficulty")
    public ResponseEntity<GetRecipeResponse> getRecipesByDifficulty(
            @RequestParam String difficulty,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit) {

        if (difficulty == null || difficulty.isEmpty()) {
            throw new IllegalArgumentException("A difficulty is required for filtering trending recipes");
        }

        GetRecipeResponse response = recipeService.getRecipesByDifficulty(difficulty, page, limit);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
