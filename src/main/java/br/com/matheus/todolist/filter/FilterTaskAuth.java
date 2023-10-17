package br.com.matheus.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.matheus.todolist.users.UserRepository;
import br.com.matheus.todolist.utility.CurrentDateTime;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

  CurrentDateTime dateTime = new CurrentDateTime();
  String currentDate = dateTime.getFormattedDateTime();

  @Autowired
  private UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    var servletPath = request.getServletPath();

    if (servletPath.startsWith("/tasks/")) {

      var authorization = request.getHeader("Authorization");

      var authEncoded = authorization.substring("Basic".length()).trim();

      byte[] authDecode = Base64.getDecoder().decode(authEncoded);

      var auth = new String(authDecode);

      var username = auth.split(":")[0];
      var password = auth.split(":")[1];

      var user = this.userRepository.findByUsername(username);

      if (user == null) {
        var message = " Usuário " + username + " não encontrado.";
        System.out.println(currentDate + message);
        response.sendError(401, message);

      } else {

        var passwordHashred = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

        if (passwordHashred.verified) {
          request.setAttribute("idUser", user.getId());
          filterChain.doFilter(request, response);
        } else {
          var message = " Dados incorretos.";
          System.out.println(currentDate + message);
          response.sendError(401, message);
        }
      }
    } else {
      filterChain.doFilter(request, response);
    }
  }

}
