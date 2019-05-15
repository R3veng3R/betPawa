package com.aj.pawatask.utils.dto;

import com.aj.pawatask.utils.enums.TaskPriority;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskDTO {
    private String title;
    private String description;
    private TaskPriority priority;
    private Long userId;
    private String comment;
    private Date dueDate;
    private Long id;
}
