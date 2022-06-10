package com.verint.tasksapi;

import com.verint.tasksapi.model.TaskDTO;
import org.mapstruct.Mapper;

@Mapper
public interface TasksMapper {
    TaskDTO entityToDTO(Task task);
}
