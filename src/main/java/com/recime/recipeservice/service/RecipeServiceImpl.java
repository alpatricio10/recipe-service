package com.recime.recipeservice.service;

import com.recime.recipeservice.data.DifficultyType;
import com.recime.recipeservice.data.GetRecipeResponse;
import com.recime.recipeservice.data.Recipe;
import com.recime.recipeservice.repository.RecipeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public GetRecipeResponse getRecipes(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Recipe> recipePage = recipeRepository.findByOrderByPositionAsc(pageable);

        GetRecipeResponse response = setGetRecipeResponseFromPage(recipePage);
        return response;
    }

    @Override
    public GetRecipeResponse getRecipesByDifficulty(String difficulty, int page, int limit) {
        DifficultyType difficultyType = DifficultyType.valueOf(difficulty.toUpperCase());

        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Recipe> recipePage = recipeRepository.findByDifficultyOrderByPositionAsc(difficultyType, pageable);

        GetRecipeResponse response = setGetRecipeResponseFromPage(recipePage);
        return response;
    }

    private GetRecipeResponse setGetRecipeResponseFromPage(Page<Recipe> page) {
        List<Recipe> recipes = page.getContent();
        long totalPages = page.getTotalPages();
        int currentPage = page.getNumber() + 1;

        GetRecipeResponse response = new GetRecipeResponse();
        response.setTotalPages(totalPages);
        response.setCurrentPage(currentPage);
        response.setData(recipes);

        return response;
    }

}
