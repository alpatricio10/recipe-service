package com.recime.recipeservice.data;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class Recipe {

    private String name;

    private String description;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private DifficultyType difficulty;

    private int position;

}
