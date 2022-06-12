package com.verint.tasksapi;

import com.verint.tasksapi.model.TaskDTO;
import org.mapstruct.Mapper;

@Mapper
public interface TaskMapper {
    TaskDTO entityToDTO(Task task);
    Task dtoToEntity(TaskDTO taskDTO);
}
