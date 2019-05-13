package com.aj.pawatask.repositories;

import com.aj.pawatask.models.Comment;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CommentRepository extends BaseRepository<Comment, Long> {

    List<Comment> findByTaskId(@NotNull Long taskId);
}
