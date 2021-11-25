# API Rest com Kotlin e Spring Boot

CRUD de um fórum de curso online onde trabalhou-se em três camadas: a camada Web focando na parte dos controllers e serviços, a parte de persistência onde inseriu-se o banco de dados utilizando Spring Data JPA e flyway como ferramente de migrations; e a camada de infraestrutura, segurança, banco de dados, docker e deploy no heroku.


# O que foi utilizado?

- Kotlin;
- Spring Boot;
- Maven;
- Spring Data JPA;
- H2;
- Flyway;
- Spring Security;
- JWT;
- Docker;
- Heroku;
- Postman (para teste da API).

### Como acessar a aplicação:

- Acessar a aplicação por meio do link abaixo.
```
https://frozen-lowlands-13125.herokuapp.com/login
```
- No Postman utilize o POST para realizar o login e obter um token, este token expira em 60s. Este token está visível no HEADERS -> Authorization do Postman, 
```
{
	"username": "ana@email.com",
	"password": "123456"
}
```
- Em seguida, por meio do link abaixo, na guia Authorization do Postman, em Type escolher a opção Bears Token e inserir o token obtido, para assim poder cadastrar um tópico.
```
https://frozen-lowlands-13125.herokuapp.com/topicos
```

### Endpoints:

- **POST** (login): /login
- **POST** (criar tópico): /topicos
- **GETALL**: /topicos
- **GETBYID**: /topicos/{id}
- **PUT**: /topicos/{id}
- **DELETE**: /topicos/{id}
