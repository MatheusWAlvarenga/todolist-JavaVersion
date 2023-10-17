package br.com.matheus.todolist.tasks;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  private UUID userId;

  private String description;

  @Column(length = 50)
  private String title;

  private String priority;

  private LocalDateTime start_task;

  private LocalDateTime end_task;

  @CreationTimestamp
  private LocalDateTime created_at;

  public void setTitle(String title) throws Exception {
    if (title.length() > 50) {
      throw new Exception("The 50-character limit has been exceeded.");
    }
    this.title = title;
  }
}
