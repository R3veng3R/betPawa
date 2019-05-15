package com.aj.pawatask.utils.dto.mappers;

import com.aj.pawatask.models.Task;
import com.aj.pawatask.utils.dto.TaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "dueDate", target = "dueDate", dateFormat = "dd.MM.yyyy")
    Task taskDTOtoTask(TaskDTO dto);
}
