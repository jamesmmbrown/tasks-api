package com.verint.tasksapi;

import com.verint.tasksapi.model.TaskDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static com.verint.tasksapi.TaskDTOMatcher.task;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    public static final String TASKS_URL = "/tasks";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    void getAll_noPrograms_returnsEmptyArray() throws Exception {
        when(taskService.getAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/tasks")).andExpect(status().isOk()).andExpect(content().json("[]"));
    }

    @Test
    void post_successfullyCreatesTask_returnsTask() throws Exception {
        ArgumentCaptor<TaskDTO> taskDTOCaptor = ArgumentCaptor.forClass(TaskDTO.class);
        when(taskService.create(any())).thenReturn(TaskBuilder.builder()
                .id(1L)
                .name("task 1")
                .status(false)
                .build());

        mockMvc.perform(post(TASKS_URL).contentType(APPLICATION_JSON)
                        .content("""
                            {
                                "name": "task 1",
                                "status": false
                            }
                            """))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                   {
                       "id": 1,
                       "name": "task 1",
                       "status": false
                   }
                   """))
                .andExpect(header().string(LOCATION, "http://localhost" + TASKS_URL + "/1"));

        verify(taskService).create(taskDTOCaptor.capture());
        assertThat(taskDTOCaptor.getValue(), is(task("task 1", false)));
    }
}