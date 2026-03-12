package com.smartroutine.category.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="categories")
@Getter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "category_name", nullable = false, length = 50)
    private String categoryName;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = false)
    private CategoryColor color;

    @Builder
    public Category(UUID userId, String categoryName, CategoryColor color) {
        this.userId = userId;
        this.categoryName = categoryName;
        this.color = color == null ? CategoryColor.BLUE : color;
    }

    public static Category of(UUID userId, String categoryName, CategoryColor color) {
        return new Category(userId, categoryName, color);
    }

    public void update(String categoryName, CategoryColor color) {
        if (categoryName != null) {
            this.categoryName = categoryName;
        }
        if (color != null) {
            this.color = color;
        }
    }

}
