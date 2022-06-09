package com.verint.tasksapi;

import com.verint.tasksapi.model.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class TasksService {

    public List<TaskDTO> getAll() {
        return emptyList();
    }
}
