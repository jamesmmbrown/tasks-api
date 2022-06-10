package com.verint.tasksapi;

import com.verint.tasksapi.model.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksService {

    private final TasksRepository tasksRepository;
    private final TasksMapper tasksMapper;

    @Autowired
    public TasksService(TasksRepository tasksRepository, TasksMapper tasksMapper) {
        this.tasksRepository = tasksRepository;
        this.tasksMapper = tasksMapper;
    }

    public List<TaskDTO> getAll() {
        return tasksRepository.findAll().stream().map(tasksMapper::entityToDTO).toList();
    }
}
