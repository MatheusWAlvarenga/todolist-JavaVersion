package br.com.matheus.todolist.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.matheus.todolist.users.UserModel;
import br.com.matheus.todolist.users.UserRepository;
import br.com.matheus.todolist.utility.CurrentDateTime;

@RestController
@RequestMapping("/users")
public class UserController {

  CurrentDateTime dateTime = new CurrentDateTime();
  String currentDate = dateTime.getFormattedDateTime();

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody UserModel userModel) {

    var user = this.userRepository.findByUsername(userModel.getUsername());

    if (user != null) {
      var message = " O usuário " + userModel.getUsername() + " já existe.";

      System.out.println(currentDate + message);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
    userModel.setPassword(passwordHashred);
    var userCreated = this.userRepository.save(userModel);

    System.out.println(currentDate + " User Created!");
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
  }

}
