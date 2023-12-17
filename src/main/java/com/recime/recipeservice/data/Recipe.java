package com.recime.recipeservice.data;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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

    @CreationTimestamp
    @DateTimeFormat(pattern="YYYY-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTimestamp;

    private String createdBy;

    @UpdateTimestamp
    @DateTimeFormat(pattern="YYYY-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTimestamp;

    private String updatedBy;

    public Recipe(String name, String imageUrl, DifficultyType difficulty, int position) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.difficulty = difficulty;
        this.position = position;
    }

}
