package com.recime.recipeservice.service;

import com.recime.recipeservice.data.DifficultyType;
import com.recime.recipeservice.data.GetRecipeResponse;
import com.recime.recipeservice.data.Recipe;
import com.recime.recipeservice.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(SpringExtension.class)
public class RecipeServiceTests {

    @InjectMocks
    RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Test
    public void testGetRecipes() throws Exception {

        List<Recipe> mockRecipes = Arrays.asList(
                new Recipe("Recipe1", "image1.jpg", DifficultyType.EASY, 1),
                new Recipe("Recipe2", "image2.jpg", DifficultyType.MEDIUM, 2)
        );

        Page<Recipe> mockPage = new PageImpl<>(mockRecipes);

        GetRecipeResponse expectedResponse = new GetRecipeResponse();
        expectedResponse.setCurrentPage(1);
        expectedResponse.setTotalPages(1);
        expectedResponse.setData(mockRecipes);

        Mockito.when(recipeRepository.findByOrderByPositionAsc(any(Pageable.class))).thenReturn(mockPage);

        GetRecipeResponse response = recipeService.getRecipes(1, 10);

        assertEquals(expectedResponse, response, "Values should be equal");
    }

    @Test
    public void testGetRecipesByDifficulty() throws Exception {
        List<Recipe> mockRecipes = Arrays.asList(
                new Recipe("Recipe1", "image1.jpg", DifficultyType.EASY, 1),
                new Recipe("Recipe2", "image2.jpg", DifficultyType.EASY, 2)
        );

        Page<Recipe> mockPage = new PageImpl<>(mockRecipes);

        GetRecipeResponse expectedResponse = new GetRecipeResponse();
        expectedResponse.setCurrentPage(1);
        expectedResponse.setTotalPages(1);
        expectedResponse.setData(mockRecipes);

        Mockito.when(recipeRepository.findByDifficultyOrderByPositionAsc(eq(DifficultyType.EASY), any(Pageable.class))).thenReturn(mockPage);

        GetRecipeResponse response = recipeService.getRecipesByDifficulty("easy", 1, 10);

        assertEquals(expectedResponse, response, "Values should be equal");
    }

}
