package com.verint.tasksapi;

import com.verint.tasksapi.model.TaskDTO;
import lombok.Builder;

public class TaskBuilder {

    @Builder
    private static TaskDTO task(Long id, String name, Boolean status) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(id);
        taskDTO.setName(name);
        taskDTO.setStatus(status);
        return taskDTO;
    }
}
