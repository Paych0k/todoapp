package edu.school21.todoapp.controllers;

import edu.school21.todoapp.components.SecurityUtils;
import edu.school21.todoapp.models.Task;
import edu.school21.todoapp.models.User;
import edu.school21.todoapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {
    private final TaskService taskService;
    private final SecurityUtils securityUtils;


    public TaskController(TaskService taskService, SecurityUtils securityUtils) {
        this.taskService = taskService;
        this.securityUtils = securityUtils;
    }

    @GetMapping("/todo")
    public String getTasks(Model model) {
        User currentUser = securityUtils.getCurrentUser();
        if (currentUser != null) {
            List<Task> tasks = taskService.getTasksByUser(currentUser);
            model.addAttribute("tasks", tasks);
        }
        return "todo";
    }

    @PostMapping("/tasks")
    public String addTask(@RequestParam String title, @RequestParam(required = false) String deadline) {
        User currentUser = securityUtils.getCurrentUser();
        if (currentUser != null) {
            LocalDateTime deadlineTime = (deadline != null && !deadline.isEmpty()) ? LocalDateTime.parse(deadline) : null;
            Task newTask = Task.builder()
                    .title(title)
                    .createdOn(LocalDateTime.now())
                    .deadline(deadlineTime)
                    .user(currentUser)
                    .build();
            taskService.save(newTask);
        }
        return "redirect:/todo";
    }

    @PutMapping("/tasks/{id}")
    public String updateTask(@PathVariable Long id, @RequestParam String title) {
        Optional<Task> task = taskService.  findById(id);
        if (task.isPresent()) {
            task.get().setTitle(title);
            taskService.save(task.orElse(null));
        }
        return "redirect:/todo";
    }

    @DeleteMapping("/tasks/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return "redirect:/todo";
    }
}
