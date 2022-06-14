package com.verint.tasksapi;

import com.verint.tasksapi.model.TaskDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.verint.tasksapi.TaskDTOMatcher.task;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.mapstruct.factory.Mappers.getMapper;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TasksServiceTest {

    @Mock
    private TaskRepository taskRepository;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService(taskRepository, getMapper(TaskMapper.class));
    }

    @Test
    void getAll_shouldReturnAllTasks() {
        when(taskRepository.findAll()).thenReturn(asList(new Task(1L, "task 1", false), new Task(2L, "task 2", true)));

        List<TaskDTO> allTasks = taskService.getAll();

        assertThat(allTasks, contains(task(1L, "task 1", false), task(2L, "task 2", true)));
    }
}