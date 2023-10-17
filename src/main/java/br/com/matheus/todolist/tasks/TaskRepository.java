package br.com.matheus.todolist.tasks;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
  public List<TaskModel> findByUserId(UUID idUser);
}
