package com.smartroutine.goal.entity;

import com.smartroutine.common.entity.BaseEntity;
import com.smartroutine.goal.exception.GoalAccessDeniedException;
import com.smartroutine.goal.exception.GoalAlreadyDeletedException;
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
@Table(name="goals")
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
    public Goal(String goalName, UUID userId, UUID categoryId, GoalStatus goalStatus, Integer priority, LocalDate startDate, LocalDate endDate) {
        this.goalName = goalName;
        this.userId = userId;
        this.categoryId = categoryId;
        this.goalStatus = goalStatus; // default 처리 필요
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;

        super.create(userId);
    }

    public void update(String goalName, GoalStatus goalStatus, Integer priority, LocalDate startDate, LocalDate endDate) {
        if(goalName != null && !goalName.isEmpty()) this.goalName = goalName;
        if(goalStatus != null) this.goalStatus = goalStatus;
        if(priority != null) this.priority = priority;
        if(startDate != null) this.startDate = startDate;
        if(endDate != null) this.endDate = endDate;

        super.update(userId);
    }

    public void delete(UUID userId) {
        if(this.getDeletedAt() != null) {
            throw new GoalAlreadyDeletedException(goalId);
        }
        super.delete(userId);
    }

    public void validateOwner (UUID userId) {
        if(!userId.equals(this.userId)) {
            throw new GoalAccessDeniedException(goalId, userId);
        }
    }

}
