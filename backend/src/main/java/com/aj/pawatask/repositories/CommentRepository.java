package com.aj.pawatask.repositories;

import com.aj.pawatask.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT c.id, c.create_date, c.comment, c.task_id, c.user_name, c.user_lastname FROM " + Comment.TABLE_NAME + " c WHERE c.task_id = :taskId", nativeQuery = true)
    List<Comment> findByTaskId(@Param("taskId") Long taskId);
}
