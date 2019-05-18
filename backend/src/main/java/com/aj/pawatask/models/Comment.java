package com.aj.pawatask.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = Comment.TABLE_NAME)
public class Comment extends AbstractModel {

    public static final String TABLE_NAME = "comments";

    @Column(name="comment")
    private String comment;

    @Column(name="create_date")
    @CreationTimestamp
    private Timestamp createDate;

    @Column(name="task_id")
    private Long taskId;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
}
