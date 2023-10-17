package br.com.matheus.todolist.users;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

  UserModel findByUsername(String username);

  UserModel findByPassword(String password);

}
