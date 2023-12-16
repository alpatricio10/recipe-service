package com.recime.recipeservice.controller;

import com.recime.recipeservice.data.DifficultyType;
import com.recime.recipeservice.data.Recipe;
import com.recime.recipeservice.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.xmlunit.diff.Diff;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RecipeController.class)
public class RecipeControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RecipeRepository recipeRepository;

    @Test
    public void testGetRecipes() throws Exception {

        List<Recipe> recipes = Arrays.asList(
                new Recipe("Recipe1", "image1.jpg", DifficultyType.EASY, 1),
                new Recipe("Recipe2", "image2.jpg", DifficultyType.MEDIUM, 2),
                new Recipe("Recipe3", "image3.jpg", DifficultyType.HARD, 3)
        );

        Mockito.when(recipeRepository.findByOrderByPositionAsc()).thenReturn(recipes);

        MockHttpServletRequestBuilder request = get("/api/v1/recipe");

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.data[0].name").value("Recipe1"))
                .andExpect(jsonPath("$.data[1].name").value("Recipe2"))
                .andExpect(jsonPath("$.data[2].name").value("Recipe3"));
    }
}
