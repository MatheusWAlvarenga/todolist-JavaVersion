package br.com.matheus.todolist.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheus.todolist.tasks.TaskModel;
import br.com.matheus.todolist.tasks.TaskRepository;
import br.com.matheus.todolist.utility.CurrentDateTime;
import br.com.matheus.todolist.utility.Utility;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TasksController {

  CurrentDateTime dateTime = new CurrentDateTime();
  String currentDate = dateTime.getFormattedDateTime();

  @Autowired
  private TaskRepository taskRepository;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    var idUserCurrente = request.getAttribute("idUser");
    taskModel.setUserId((UUID) idUserCurrente);

    var currentDate = LocalDateTime.now();

    if (currentDate.isAfter(taskModel.getStart_task())) {
      var message = " The start date must be greater than the current date.";
      System.out.println(currentDate + message);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    if (taskModel.getEnd_task().isBefore(taskModel.getStart_task())) {
      var message = " The end date must be greater than the start date.";
      System.out.println(currentDate + message);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    var taskCreated = this.taskRepository.save(taskModel);

    System.out.println(currentDate + " Task created!");
    return ResponseEntity.status(HttpStatus.CREATED).body(taskCreated);
  }

  @GetMapping("/")
  public List<TaskModel> list(HttpServletRequest request) {
    var idUserCurrente = request.getAttribute("idUser");
    var tasks = this.taskRepository.findByUserId((UUID) idUserCurrente);
    return tasks;
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody TaskModel taskModel, @PathVariable UUID id,
      HttpServletRequest request) {

    var task = this.taskRepository.findById(id).orElse(null);
    var idUserCurrente = request.getAttribute("idUser");

    if (task == null) {
      var message = " Task not found.";
      System.out.println(currentDate + message);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    if (task.getUserId().equals(idUserCurrente)) {
      var message = " The user does not have permission to change this task.";
      System.out.println(currentDate + message);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    Utility.copyNonNullProperties(taskModel, task);

    var updated = this.taskRepository.save(task);

    System.out.println(currentDate + " Task created!");
    return ResponseEntity.status(HttpStatus.OK).body(updated);
  }

}
