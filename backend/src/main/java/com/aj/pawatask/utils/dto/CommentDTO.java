package com.aj.pawatask.utils.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private String comment;
    private Long taskId;
    private Long userId;
}
