package com.aj.pawatask.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = Comment.TABLE_NAME)
public class Comment extends AbstractModel {

    public static final String TABLE_NAME = "comments";

    @Column(name="user_name")
    private String userName;

    @Column(name="user_lastname")
    private String userLastname;

    @Column(name="comment")
    private String comment;

    @Column(name="create_date")
    private Timestamp createDate;

    @Column(name="task_id")
    private Long taskId;

    @Column(name="user_id")
    private Long userId;
}
