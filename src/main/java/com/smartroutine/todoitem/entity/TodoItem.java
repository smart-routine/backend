package com.smartroutine.todoitem.entity;

import com.smartroutine.common.entity.BaseEntity;
import com.smartroutine.todoitem.exception.TodoAlreadyDeletedException;
import com.smartroutine.todoitem.exception.TodoNameInvalidException;
import com.smartroutine.todoitem.exception.TodoStatusValidException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
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
    @GeneratedValue(strategy = GenerationType.UUID)
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TodoStatus  status;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "scheduled_start_at")
    private LocalDateTime scheduledStartAt;

    @Column(name = "scheduled_end_at")
    private LocalDateTime scheduledEndAt;

    @Column(name = "is_ai", nullable = false)
    private Boolean isAi;

    @Builder
    public TodoItem(UUID todoId, String todoName, UUID userId, UUID goalId, Integer duration,
        TodoStatus status, LocalDateTime scheduledStartAt, LocalDateTime scheduledEndAt, Boolean isAi){
        this.todoId = todoId;

        if(todoName == null || todoName.isBlank()){
            throw new TodoNameInvalidException(todoName);
        }
        this.todoName = todoName;

        this.userId = userId;
        this.goalId = goalId;
        this.duration = duration;
        this.status = status != null ? status : TodoStatus.PENDING;
        this.scheduledStartAt = scheduledStartAt;
        this.scheduledEndAt = scheduledEndAt;
        this.isAi = isAi != null ? isAi : false;

        super.create(userId);
    }

    public void updateTodo(UUID userId, String todoName, Integer duration, LocalDateTime scheduledStartAt, LocalDateTime scheduledEndAt){
        if(todoName != null && !todoName.isBlank()) this.todoName = todoName;
        if(duration != null) this.duration = duration;
        if(scheduledStartAt != null) this.scheduledStartAt = scheduledStartAt;
        if(scheduledEndAt != null) this.scheduledEndAt = scheduledEndAt;

        super.update(userId);
    }

    public void updateStatus(TodoStatus status){
        if(status == null)  throw new TodoStatusValidException();

        this.status = status;

        if(status == TodoStatus.COMPLETED) this.completedAt = LocalDateTime.now();
        else this.completedAt = null;

        super.update(userId);
    }

    public void delete(){
        if(this.getDeletedAt() != null) throw new TodoAlreadyDeletedException(todoId);

        super.delete(userId);
    }

}
