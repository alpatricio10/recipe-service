package com.recime.recipeservice.service;

import com.recime.recipeservice.data.GetRecipeResponse;

public interface RecipeService {

    GetRecipeResponse getRecipes(int page, int limit);

    GetRecipeResponse getRecipesByDifficulty(String difficulty, int page, int limit);

}
