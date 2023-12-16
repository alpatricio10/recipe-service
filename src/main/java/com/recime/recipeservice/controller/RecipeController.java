package com.recime.recipeservice.controller;

import com.recime.recipeservice.data.GetRecipeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RecipeController {

    @Autowired
    public RecipeController() {}

    @GetMapping("recipe")
    public ResponseEntity<GetRecipeResponse> getRecipes() {

        return new ResponseEntity<>(new GetRecipeResponse(), HttpStatus.OK);
    }

}
