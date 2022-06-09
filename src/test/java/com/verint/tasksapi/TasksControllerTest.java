package com.verint.tasksapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
class TasksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TasksService tasksService;

    @Test
    void getAll_noPrograms_returnsEmptyArray() throws Exception {
        when(tasksService.getAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/tasks")).andExpect(status().isOk()).andExpect(content().json("[]"));
    }
}