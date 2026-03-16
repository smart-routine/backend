package com.smartroutine.goal.repository;

import com.smartroutine.goal.entity.Goal;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, UUID> {

    boolean existsByUserIdAndCategoryIdAndGoalName(UUID userId, UUID categoryId, String goalName);

    List<Goal> findAllByUserId(UUID userId);

}
