package com.smartroutine.goal.dto;

import com.smartroutine.goal.entity.GoalStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;

@Getter
public class GoalCreateRequest {

    @NotBlank
    @Size(min = 1, max = 50)
    private String goalName;

    private UUID categoryId;

    private GoalStatus goalStatus;

    private Integer priority;

    private LocalDate startDate;

    private LocalDate endDate;

}
