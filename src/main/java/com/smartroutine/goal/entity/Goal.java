package com.smartroutine.goal.entity;

import com.smartroutine.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name="goal")
@Getter
@NoArgsConstructor
@SQLRestriction("deleted_at IS NULL")
public class Goal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "goal_id", nullable = false)
    private UUID goalId;

    @Column(name = "goal_name", nullable = false)
    private String goalName;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "category_id",  nullable = false)
    private UUID categoryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "goal_status",  nullable = false)
    private GoalStatus goalStatus;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Builder
    public Goal(UUID goalId, String goalName, UUID userId, UUID categoryId, GoalStatus goalStatus, Integer priority, LocalDate startDate, LocalDate endDate) {
        this.goalId = goalId;
        this.goalName = goalName;
        this.userId = userId;
        this.categoryId = categoryId;
        this.goalStatus = goalStatus;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;

        super.create(userId);
    }

    public void update(String goalName, GoalStatus goalStatus, Integer priority, LocalDate startDate, LocalDate endDate) {
        super.update(userId);
    }

    public void delete() {
        super.delete(userId);
    }

}
