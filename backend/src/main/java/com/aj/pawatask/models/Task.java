package com.aj.pawatask.models;

import com.aj.pawatask.utils.enums.TaskPriority;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = Task.TABLE_NAME)
public class Task extends AbstractModel {

    public static final String TABLE_NAME = "tasks";
    public static final String COLUMN_LIST_SORT = "createDate";

    public Task() { super(); }

    public Task(String title, String description, Date dueDate, TaskPriority priority, User user) {
        super();
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.user = user;
    }

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="due_date")
    private Date dueDate;

    @Column(name="create_date")
    @CreationTimestamp
    private Timestamp createDate;

    @Enumerated(EnumType.STRING)
    @Column(name="priority")
    private TaskPriority priority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
