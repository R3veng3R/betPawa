package com.aj.pawatask.utils.dto.mappers;

import com.aj.pawatask.models.Task;
import com.aj.pawatask.utils.dto.TaskDTO;
import com.aj.pawatask.utils.enums.TaskPriority;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {
    private TaskMapper mapper = Mappers.getMapper(TaskMapper.class);

    @Test
    public void taskDTOtoTask() {
        TaskDTO dto = new TaskDTO();
        dto.setDescription("testDescription");
        dto.setTitle("testTitle");
        dto.setPriority(TaskPriority.MEDIUM);
        dto.setDueDate(new Date());
        dto.setId(1L);

        Task task = mapper.taskDTOtoTask(dto);

        assertEquals(dto.getDescription(), task.getDescription());
        assertEquals(dto.getTitle(), task.getTitle());
        assertEquals(dto.getPriority(), task.getPriority());
        assertEquals(dto.getDueDate(), task.getDueDate());
    }
}