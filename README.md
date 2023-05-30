# :books: Biblioteca App
Biblioteca App é um pequeno sistema que simula um gerenciamento de uma biblioteca, como o cadastro de livros, reservas e devoluções. Possui autenticação síncrona com o banco de dados, ou seja, é necessário que o usuário esteja previamente cadastrado no banco de dados para conseguir visualizar a página inicial da Biblioteca App. A Biblioteca App é executada no endereço http://localhost.

### Funcionalidades do sistema
- Cadastrar livros, reservas e usuários
- Editar livros, reservas e usuários
- Remover livros, reservas e usuários
- Devolver livros

### Recursos utilizados
- IDE IntelliJ IDEA 2022.2 (Community Edition)
- Linguagem Java
- Framework Spring
- Banco de dados MySQL
- Postman v10.13.5

### Endpoints
O sistema possui uma única tela (página inicial), sendo apenas possível cadastrar livros, reservar ou devolver. Caso queira alterar as informações de um livro já cadastrado por exemplo, é possível fazer esta ação via endpoint usando a API Postman. No arquivo localizado em **_src/main/resources/templates/Biblioteca App.postman_collection.json_**, é possível visualizar todos os endpoints.
![image](https://github.com/nataliasuzuki/biblioteca-app/assets/61856025/24ae3bca-50c7-4ab1-9646-154510b622f9)

### Autenticação
Como já informado no início, o sistema requer autenticação. Abaixo segue o SQL para criar o usuário **_adm_** com a senha **_adm_** na tabela **_usuario_**:
```sql
INSERT INTO biblioteca_db.usuario(data_criacao, data_nascimento, nome, ativo, documento_identificacao, email, senha, login)
VALUES(null, null, null, 1, null, null, '$2a$12$/3rVvVLUmhfgVzPSmdec0OR5OClQAmXltU0vUPMlQDIN0jsRP94oa', 'adm');
``` 
Tela de autenticação do sistema:
![image](https://github.com/nataliasuzuki/biblioteca-app/assets/61856025/bed056ac-9cc6-4f4b-bcfc-2107762a5885)

É possível utilizar um outro método de autenticação mais simples no sistema, basta adicionar a dependência do próprio spring. Esta autenticação consiste em um usuário padrão e uma senha pré-definida.
Para utilizar essa autenticação, basta incluir a seguinte dependência no arquivo *pom.xml*:
```maven
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
Tela do método de autenticação do próprio spring:
![image](https://github.com/nataliasuzuki/biblioteca-app/assets/61856025/d23a66dd-b5a0-4216-8778-721084753b25)

O usuário padrão é ***user*** e a senha aparece no console da IDE:
![image](https://github.com/nataliasuzuki/biblioteca-app/assets/61856025/9874e263-abc1-46a4-b79e-f35939fbd9fa)

### Página inicial da Biblioteca App
![image](https://github.com/nataliasuzuki/biblioteca-app/assets/61856025/303782c7-5fdf-4cad-b435-02fef539f3cc)
