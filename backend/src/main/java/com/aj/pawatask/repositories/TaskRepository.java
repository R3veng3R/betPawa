package com.aj.pawatask.repositories;

import com.aj.pawatask.models.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends BaseRepository<Task, Long> {
}
