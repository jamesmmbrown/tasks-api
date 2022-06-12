package com.verint.tasksapi;

import com.verint.tasksapi.model.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public List<TaskDTO> getAll() {
        return taskRepository.findAll().stream().map(taskMapper::entityToDTO).toList();
    }

    public TaskDTO create(TaskDTO task) {
        return taskMapper.entityToDTO(taskRepository.save(taskMapper.dtoToEntity(task)));
    }
}
