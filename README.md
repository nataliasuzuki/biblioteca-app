## biblioteca-app :books:

#### O que é possível fazer nesta API?
- Cadastrar livros, reservas e usuários
- Editar livros, reservas e usuários
- Remover livros, reservas e usuários

#### :computer: Recursos utilizados:

- IDE IntelliJ IDEA 2022.2 (Community Edition)
- Linguagem Java
- Framework Spring
- Banco de dados MySQL
- Postman v10.13.5

#### Autenticação
É possível utilizar a dependência do próprio spring para ter apenas uma autenticação no projeto.
Para isso, adicionar no arquivo *pom.xml*:
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
Ao adicionar a dependência e iniciar o projeto, aparecerá a seguinte tela:
![image](https://github.com/nataliasuzuki/biblioteca-app/assets/61856025/d23a66dd-b5a0-4216-8778-721084753b25)

O usuário padrão é *user* e a senha aparece no console da IDE:
![image](https://github.com/nataliasuzuki/biblioteca-app/assets/61856025/9874e263-abc1-46a4-b79e-f35939fbd9fa)

#### Visualização
![image](https://github.com/nataliasuzuki/biblioteca-app/assets/61856025/9c2e7371-7472-4eea-8574-ab11df7e1cf2)
