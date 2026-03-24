package com.smartroutine.todoitem.entity;

import com.smartroutine.common.entity.BaseEntity;
import com.smartroutine.todoitem.exception.TodoAlreadyDeletedException;
import com.smartroutine.todoitem.exception.TodoNameInvalidException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "todo_items")
@Getter
@NoArgsConstructor
@SQLRestriction("DELETED_AT IS NULL")
public class TodoItem extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID todoId;

    @Column(name = "todo_name", nullable = false)
    @Size(min = 1, max = 50)
    private String todoName;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "goal_id")
    private UUID goalId;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "status", nullable = false)
    private TodoStatus  status;

    @Column(name = "scheduled_start_at")
    private LocalDate scheduledStartAt;

    @Column(name = "scheduled_end_at")
    private LocalDate scheduledEndAt;

    @Column(name = "is_ai", nullable = false)
    private Boolean isAi;

    @Builder
    public TodoItem(UUID todoId, String todoName, UUID userId, UUID goalId, Integer duration,
        TodoStatus status, LocalDate scheduledStartAt, LocalDate scheduledEndAt, Boolean isAi){
        this.todoId = todoId;

        if(todoName == null || todoName.isBlank()){
            throw new TodoNameInvalidException(todoName);
        }
        this.todoName = todoName;

        this.userId = userId;
        this.goalId = goalId;
        this.duration = duration;
        this.status = status != null ? status : TodoStatus.IN_PROGRESS;
        this.scheduledStartAt = scheduledStartAt;
        this.scheduledEndAt = scheduledEndAt;
        this.isAi = isAi;

        super.create(userId);
    }

    public void updateTodo(UUID userId, String todoName, Integer duration, LocalDate scheduledStartAt){
        if(todoName != null && !todoName.isBlank()) this.todoName = todoName;
        if(duration != null) this.duration = duration;
        if(scheduledStartAt != null) this.scheduledStartAt = scheduledStartAt;

        super.update(userId);
    }

    public void updateStatus(TodoStatus status){
        if(status != null) this.status = status;

        else if(this.status == TodoStatus.IN_PROGRESS) this.status = TodoStatus.PENDING;
        else if(this.status == TodoStatus.PENDING) this.status = TodoStatus.COMPLETED;
        else if(this.status == TodoStatus.COMPLETED) this.status = TodoStatus.PENDING;
    }

    public void delete(){
        if(this.getDeletedAt() != null) throw new TodoAlreadyDeletedException(todoId);

        super.delete(userId);
    }

}
