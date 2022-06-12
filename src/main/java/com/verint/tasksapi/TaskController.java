package com.verint.tasksapi;

import com.verint.tasksapi.api.TasksApi;
import com.verint.tasksapi.model.TaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class TaskController implements TasksApi {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ResponseEntity<List<TaskDTO>> getTasks() {
        return ok(taskService.getAll());
    }

    @Override
    public ResponseEntity<TaskDTO> create(TaskDTO body) {
        TaskDTO createdTask = taskService.create(body);
        return ResponseEntity.created(buildLocationURI(createdTask.getId())).body(createdTask);
    }

    private URI buildLocationURI(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}


