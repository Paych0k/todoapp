package edu.school21.todoapp.service;

import edu.school21.todoapp.models.Task;
import edu.school21.todoapp.models.User;
import edu.school21.todoapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(Long id, Task task) {
        return taskRepository.findById(id).map(existingTask -> {
            existingTask.setTitle(task.getTitle());
            existingTask.setInfo(task.getInfo());
            existingTask.setDeadline(task.getDeadline());
            return taskRepository.save(existingTask);
        }).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    public List<Task> getTasksByUser(User user) {
        return taskRepository.findByUser(user);
    }


    public void save(Task newTask) {
        taskRepository.save(newTask);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
