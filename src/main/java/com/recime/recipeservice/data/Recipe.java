package com.recime.recipeservice.data;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private DifficultyType difficulty;

    private int position;

    public Recipe(String name, String imageUrl, DifficultyType difficulty, int position) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.difficulty = difficulty;
        this.position = position;
    }

}
