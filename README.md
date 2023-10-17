

<h1 align="center">ToDoList Backend em Java</h1>

<div align="center">
   <img width="300" alt="Coffee is all you need" src="https://github.com/MatheusWAlvarenga/todolist-JavaVersion/assets/94935750/a44700dd-a0ac-4eb5-bc55-9c947f870391">
<div> Este é o repositório do backend do projeto ToDoList em Java da semana Java da Rocketseat. O backend é desenvolvido em Spring Boot e utiliza o PostgreSQL como banco de dados. O Spring Boot Starter Data JPA é usado para interagir com o banco de dados, e a versão do Java utilizada é a 17. Além disso, as bibliotecas Bcrypt e Lombok são usadas para aumentar a segurança e a produtividade.
</div> 

</div>



## Pré-requisitos
Certifique-se de ter o seguinte software instalado em sua máquina:

 - Java 17
 - Maven
 - PostgreSQL

### Configuração
Clone este repositório:
`git clone https://github.com/MatheusWAlvarenga/todolist-JavaVersion`
Crie um banco de dados PostgreSQL com o nome todolist.

### Configure as propriedades do banco de dados no arquivo `src/main/resources/application.properties`:

- spring.datasource.url=jdbc:postgresql://localhost:5432/todolist
- spring.datasource.username=seu-usuario
- spring.datasource.password=sua-senha

### Inicie a aplicação usando o Maven:

`mvn spring-boot:run`


### Endpoints
A API oferece os seguintes endpoints:

- POST /api/users: Cria um usuário.
- POST /api/tasks: Cria uma nova tarefa.
- GET /api/tasks: Retorna a lista de tarefas.
- PUT /api/tasks/{id}: Atualiza os detalhes de uma tarefa.

### Autenticação
A autenticação é implementada usando Bcrypt para armazenar senhas seguramente. Os endpoints que requerem autenticação são protegidos e requerem um token de autenticação válido. Certifique-se de passar um token de autenticação válido no cabeçalho da solicitação ao acessar esses endpoints.

### Contribuição
Sinta-se à vontade para contribuir para este projeto. Se você encontrar algum problema ou tiver alguma melhoria, crie uma "issue" ou envie um "pull request".
