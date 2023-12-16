package com.recime.recipeservice.data;

import lombok.Data;

import java.util.List;

@Data
public class GetRecipeResponse {

    private List<Recipe> data;

}
