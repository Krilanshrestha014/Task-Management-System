package com.krilan.taskmanagement;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import com.krilan.taskmanagement.controller.TaskDetailController;
import com.krilan.taskmanagement.entity.Task;
import com.krilan.taskmanagement.service.impl.TaskService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TaskDetailController.class)
public class TaskDetailControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @InjectMocks
    private TaskDetailController taskDetailController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser // Simulating an authenticated user
    public void testIndex() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setEmail("Test Task");
        
        when(taskService.getAllTasks()).thenReturn(Arrays.asList(task));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(view().name("tasks/index"))
                .andExpect(model().attributeExists("tasks"));
    }

    @Test
    @WithMockUser
    public void testShowCreateTaskForm() throws Exception {
        mockMvc.perform(get("/tasks/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("tasks/create-form"))
                .andExpect(model().attributeExists("task"));
    }

    @Test
    @WithMockUser
    public void testCreateNewTask() throws Exception {
        Task task = new Task();
        task.setEmail("New Task");

        mockMvc.perform(post("/tasks/store")
                .with(csrf()) // Include CSRF token if CSRF protection is enabled
                .param("name", task.getEmail()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tasks"));

        verify(taskService, times(1)).createTask(any(Task.class));
    }

    @Test
    @WithMockUser
    public void testShowUpdateTaskForm() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setEmail("Existing Task");

        when(taskService.getById(1L)).thenReturn(Optional.of(task));

        mockMvc.perform(get("/tasks/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("tasks/update-form"))
                .andExpect(model().attributeExists("task"));
    }

    @Test
    @WithMockUser
    public void testUpdateTask() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setEmail("Updated Task");

        when(taskService.updateTask(eq(1L), any(Task.class))).thenReturn(task);

        mockMvc.perform(post("/tasks/edit/1")
                .with(csrf())
                .param("name", task.getEmail()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tasks"));

        verify(taskService, times(1)).updateTask(eq(1L), any(Task.class));
    }

    @Test
    @WithMockUser
    public void testCompleteTask() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setEmail("Complete Task");
        task.setCompleted(false);

        when(taskService.getById(1L)).thenReturn(Optional.of(task));

        mockMvc.perform(get("/tasks/complete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tasks"));

        verify(taskService, times(1)).saveTask(task);
    }

    @Test
    @WithMockUser
    public void testDeleteTask() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setEmail("Task to Delete");

        when(taskService.getById(1L)).thenReturn(Optional.of(task));

        mockMvc.perform(get("/tasks/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tasks"));

        verify(taskService, times(1)).deleteTask(1L);
    }
}