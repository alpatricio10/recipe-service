package com.recime.recipeservice.data;

import lombok.Data;

import java.util.List;

@Data
public class GetRecipeResponse {

    private long totalPages;

    private int currentPage;

    private List<Recipe> data;

}
