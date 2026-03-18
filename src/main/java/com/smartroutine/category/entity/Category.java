package com.smartroutine.category.entity;

import com.smartroutine.common.entity.BaseEntity;
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
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name="categories")
@Getter
@NoArgsConstructor
@SQLRestriction("deleted_at IS NULL")
public class Category extends BaseEntity {

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
        super.create(userId);
    }

    public static Category of(UUID userId, String categoryName, CategoryColor color) {
        return new Category(userId, categoryName, color);
    }

    public void update(String categoryName, CategoryColor color) {
        if (categoryName != null && !categoryName.isBlank()) {
            this.categoryName = categoryName;
        }
        if (color != null) {
            this.color = color;
        }
        super.update(userId);
    }

    public void delete() {
        super.delete(userId);
    }
}
