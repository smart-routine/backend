package com.smartroutine.todoitem.repository;

import com.smartroutine.todoitem.entity.TodoItem;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem, UUID> {

}
