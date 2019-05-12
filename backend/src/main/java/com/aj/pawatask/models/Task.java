package com.aj.pawatask.models;

import com.aj.pawatask.utils.enums.TaskPriority;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="tasks")
public class Task extends AbstractModel {
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
    @JoinColumn(name="user_id", nullable = false)
    private User user;
}
