package com.smartroutine.goal.repository;

import com.smartroutine.goal.entity.Goal;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, UUID> {

    boolean existsByUserIdAndCategoryIdAndGoalName(UUID userId, UUID categoryId, String goalName);

    List<Goal> findAllByUserId(UUID userId);

    boolean existsByUserIdAndCategoryIdAndGoalNameAndGoalIdNot(UUID userId, UUID categoryId,
        @Size(min = 1, max = 50) String goalName, UUID goalId);

}
