package com.recime.recipeservice.controller;

import com.recime.recipeservice.data.DifficultyType;
import com.recime.recipeservice.data.GetRecipeResponse;
import com.recime.recipeservice.data.Recipe;
import com.recime.recipeservice.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RecipeController.class)
public class RecipeControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RecipeService recipeService;

    @Test
    public void testGetRecipes() throws Exception {

        List<Recipe> recipes = Arrays.asList(
                new Recipe("Recipe1", "image1.jpg", DifficultyType.EASY, 1),
                new Recipe("Recipe2", "image2.jpg", DifficultyType.MEDIUM, 2),
                new Recipe("Recipe3", "image3.jpg", DifficultyType.HARD, 3)
        );

        GetRecipeResponse response = new GetRecipeResponse();
        response.setCurrentPage(1);
        response.setTotalPages(1);
        response.setData(recipes);

        Mockito.when(recipeService.getRecipes(anyInt(), anyInt())).thenReturn(response);

        MockHttpServletRequestBuilder request = get("/api/v1/recipe");

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.currentPage").value(1))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.data[0].name").value("Recipe1"))
                .andExpect(jsonPath("$.data[1].name").value("Recipe2"))
                .andExpect(jsonPath("$.data[2].name").value("Recipe3"));
    }

    @Test
    public void testGetRecipesByDifficulty() throws Exception {

        List<Recipe> recipes = Arrays.asList(
                new Recipe("Recipe1", "image1.jpg", DifficultyType.EASY, 1),
                new Recipe("Recipe2", "image2.jpg", DifficultyType.EASY, 2)
        );

        GetRecipeResponse response = new GetRecipeResponse();
        response.setCurrentPage(1);
        response.setTotalPages(1);
        response.setData(recipes);

        Mockito.when(recipeService.getRecipesByDifficulty(eq("easy"), anyInt(), anyInt())).thenReturn(response);

        MockHttpServletRequestBuilder request = get("/api/v1/recipeByDifficulty?difficulty=easy");

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.currentPage").value(1))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.data[0].name").value("Recipe1"))
                .andExpect(jsonPath("$.data[1].name").value("Recipe2"));
    }

    @Test
    public void testGetRecipesByDifficulty_noDifficulty() throws Exception {

        MockHttpServletRequestBuilder request = get("/api/v1/recipeByDifficulty?difficulty=");

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.code").value("ValidationError"))
                .andExpect(jsonPath("$.message").value("A difficulty is required for filtering trending recipes"));
    }
}
