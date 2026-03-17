package com.smartroutine.goal.dto;

import com.smartroutine.goal.entity.GoalStatus;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class GoalUpdateRequest {

    @Size(min = 1, max = 50)
    private String goalName;

    private GoalStatus goalStatus;

    private Integer priority;

    private LocalDate startDate;

    private LocalDate endDate;

}
