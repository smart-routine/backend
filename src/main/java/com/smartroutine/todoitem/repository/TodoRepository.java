package com.smartroutine.todoitem.repository;

import com.smartroutine.todoitem.entity.TodoItem;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem, UUID> {


    List<TodoItem> findAllByUserIdAndScheduledStartAt(UUID userId, LocalDate scheduledStartAt);

    List<TodoItem> findAllByUserIdAndGoalId(UUID userId, UUID goalId);

    Optional<TodoItem> findByTodoIdAndUserId(UUID id,  UUID userId);
}
