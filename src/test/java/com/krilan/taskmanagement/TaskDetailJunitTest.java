package com.krilan.taskmanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import com.krilan.taskmanagement.controller.TaskDetailController;
import com.krilan.taskmanagement.entity.Task;
import com.krilan.taskmanagement.service.impl.TaskService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
public class TaskDetailJunitTest {

    @InjectMocks
    private TaskDetailController taskDetailController;

    @Mock
    private TaskService taskService;

    @Mock
    private Model model;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIndex() {
        Task task = new Task();
        task.setId(1L);
        task.setEmail("Test Task");

        when(taskService.getAllTasks()).thenReturn(Arrays.asList(task));

        String viewName = taskDetailController.index(model);

        verify(model, times(1)).addAttribute("tasks", Arrays.asList(task));
        assertEquals("tasks/index", viewName);
    }

    @Test
    public void testShowCreateTaskForm() {
        String viewName = taskDetailController.showCreteTaskForm(model);
//        verify(model, times(1)).addAttribute("task", new Task());
        assertEquals("tasks/create-form", viewName);
    }

    @Test
    public void testCreateNewTask() {
        Task task = new Task();
        task.setEmail("New Task");

        taskDetailController.creteNewTask(task);

        verify(taskService, times(1)).createTask(task);
    }

    @Test
    public void testShowUpdateTaskForm() {
        Task task = new Task();
        task.setId(1L);
        task.setEmail("Existing Task");

        when(taskService.getById(1L)).thenReturn(Optional.of(task));

        String viewName = taskDetailController.showUpdateTaskForm(1L, model);

        verify(model, times(1)).addAttribute("task", task);
        assertEquals("tasks/update-form", viewName);
    }

    @Test
    public void testUpdateTask() {
        Task task = new Task();
        task.setId(1L);
        task.setEmail("Updated Task");

        when(taskService.updateTask(eq(1L), any(Task.class))).thenReturn(task);

        String viewName = taskDetailController.updateTask(1L, task);

        verify(taskService, times(1)).updateTask(eq(1L), any(Task.class));
        assertEquals("redirect:/tasks", viewName);
    }

    @Test
    public void testCompleteTask() {
        Task task = new Task();
        task.setId(1L);
        task.setEmail("Complete Task");
        task.setCompleted(false);

        when(taskService.getById(1L)).thenReturn(Optional.of(task));

        String viewName = taskDetailController.completeTask(1L);

        verify(taskService, times(1)).saveTask(task);
        assertEquals("redirect:/tasks", viewName);
    }

    @Test
    public void testDeleteTask() {
        Task task = new Task();
        task.setId(1L);
        task.setEmail("Task to Delete");

        when(taskService.getById(1L)).thenReturn(Optional.of(task));

        String viewName = taskDetailController.deleteTask(1L, model);

        verify(taskService, times(1)).deleteTask(1L);
        assertEquals("redirect:/tasks", viewName);
    }
}