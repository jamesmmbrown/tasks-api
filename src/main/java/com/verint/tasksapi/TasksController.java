package com.verint.tasksapi;

import com.verint.tasksapi.api.TasksApi;
import com.verint.tasksapi.model.TaskDTO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TasksController implements TasksApi {

    @Override
    public TaskDTO[] getTasks() {
        return new TaskDTO[0];
    }
}