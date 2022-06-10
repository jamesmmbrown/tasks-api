package com.verint.tasksapi;

import com.verint.tasksapi.api.TasksApi;
import com.verint.tasksapi.model.TaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class TasksController implements TasksApi {

    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @Override
    public ResponseEntity<List<TaskDTO>> getTasks() {
        return ok(tasksService.getAll());
    }
}


