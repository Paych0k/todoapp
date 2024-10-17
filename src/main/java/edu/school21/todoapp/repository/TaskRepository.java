package edu.school21.todoapp.repository;

import edu.school21.todoapp.models.Task;
import edu.school21.todoapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
