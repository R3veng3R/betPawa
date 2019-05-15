package com.aj.pawatask.models;

import com.aj.pawatask.utils.enums.TaskPriority;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = Task.TABLE_NAME)
public class Task extends AbstractModel {

    public static final String TABLE_NAME = "tasks";

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="due_date")
    private Date dueDate;

    @Column(name="create_date")
    private Timestamp createDate;

    @Enumerated(EnumType.STRING)
    @Column(name="priority")
    private TaskPriority priority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taskId")
    private List<Comment> comments;
}
