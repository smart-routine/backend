package com.smartroutine.todoitem.repository;

import com.smartroutine.todoitem.entity.TodoItem;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem, UUID> {

    List<TodoItem> findAllByUserIdAndGoalId(UUID userId, UUID goalId);

    Optional<TodoItem> findByTodoIdAndUserId(UUID id,  UUID userId);

    @Query("""
      select t
      from TodoItem t
      where t.userId = :userId
         and function('date', t.scheduledStartAt) <= :date
         and function('date', t.scheduledEndAt) >= :date
    """)
    List<TodoItem> findAllByUserIdAndDate(@Param("userId") UUID userId, @Param("date") LocalDate date);
}
