package com.verint.tasksapi.configuration;

import com.verint.tasksapi.TaskController;
import com.verint.tasksapi.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import static org.mockito.Answers.RETURNS_SELF;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = TaskController.class, properties = "tasks.cors.allowed.origins=http://request-origin,http://request-origin-2")
class CORSConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Mock
    private CorsRegistry corsRegistry;

    @Mock(answer = RETURNS_SELF)
    private CorsRegistration corsRegistration;

    @Test
    void checkCorsIsAppliedToAllPaths() {
        when(corsRegistry.addMapping("/**")).thenReturn(corsRegistration);

        new CORSConfiguration(new String[]{"http://origin"}).addCorsMappings(corsRegistry);

        verify(corsRegistry).addMapping("/**");
    }

    @Test
    void checkCorsAllowedOriginsHeaders() throws Exception {
        mockMvc.perform(options("/tasks-api/tasks").header("Origin", "http://request-origin"))
                .andExpect(header().string("Access-Control-Allow-Origin", "http://request-origin"));
    }

    @Test
    void checkCorsAllowedOriginsAcceptsMultipleUrls() throws Exception {
        mockMvc.perform(options("/tasks-api/tasks").header("Origin", "http://request-origin-2"))
                .andExpect(header().string("Access-Control-Allow-Origin", "http://request-origin-2"));
    }

    @Test
    void checkCorsRequestFailsForUnknownOrigin() throws Exception {
        mockMvc.perform(options("/tasks-api/tasks").header("Origin", "http://blah.scot"))
                .andExpect(header().doesNotExist("Access-Control-Allow-Origin"));
    }
}