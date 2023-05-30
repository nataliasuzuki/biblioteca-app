# :books: Biblioteca App
Biblioteca App é um pequeno sistema que simula um gerenciamento de uma biblioteca, como o cadastro de livros, reservas e devoluções. Possui autenticação síncrona com o banco de dados, ou seja, é necessário que o usuário esteja previamente cadastrado no banco de dados para conseguir visualizar a página inicial da Biblioteca App. A Biblioteca App é executada no endereço http://localhost:8080.

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
O sistema possui uma única tela (página inicial), sendo apenas possível cadastrar livros, reservar ou devolver. Caso queira executar alguma outra ação listada nas funcionalidades do sistema, como cadastrar livros por exemplo, é possível utilizando a API Postman. No arquivo localizado em **_src/main/resources/templates/Biblioteca App.postman_collection.json_**, é possível visualizar todos os endpoints disponíveis no sistema. O arquivo também pode ser importado no Postman.
![image](https://github.com/nataliasuzuki/biblioteca-app/assets/61856025/24ae3bca-50c7-4ab1-9646-154510b622f9)

### Autenticação
Como já informado no início, o sistema requer autenticação e há dois possíveis métodos de autenticação no sistema:
1. utilizando a autenticação **_Basic Auth_**, que requer um usuário e senha cadastrados previamente no banco de dados,
2. utilizando a autenticação por JWT, o **_Bearer Token_**, que também requer um usuário e senha cadastrados previamente no banco de dados.

#### Basic Auth
Criar um _**usuario**_ para autenticação. Abaixo segue o SQL para criar o usuário **_adm_** com a senha **_adm_** na tabela **_usuario_**:
```sql
INSERT INTO biblioteca_db.usuario(data_criacao, data_nascimento, nome, ativo, documento_identificacao, email, senha, login)
VALUES(null, null, null, 1, null, null, '$2a$12$/3rVvVLUmhfgVzPSmdec0OR5OClQAmXltU0vUPMlQDIN0jsRP94oa', 'adm');
``` 

Tela de autenticação do sistema:
![image](https://github.com/nataliasuzuki/biblioteca-app/assets/61856025/bed056ac-9cc6-4f4b-bcfc-2107762a5885)

#### Bearer Token
Para autenticar no sistema usando o jwt, acessar o endereço http://localhost:8080/login (POST). O retorno será um json como mostrado abaixo:
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYXRhbGlhLnN1enVraSIsImV4cCI6MTY4NTUwNTM2Mn0.wMa83vD66PYH-UZyvrKtOyz5Uv7HHTtyx1f2F0coUBo",
    "validade": "480"
}
```
O token deve ser utilizado na configuração do header do endpoint desejado e tem validade de 8 horas. Esse período pode ser alterado modificando a propriedade _**security.jwt.expiration**_ no arquivo _**application.properties**_. O valor numeral deve ser informado em minutos. Abaixo exemplo usando o endpoint: http://localhost:8080/livro.
![image](https://github.com/nataliasuzuki/biblioteca-app/assets/61856025/26416e67-8aec-4141-aa55-86ad81e4aa7a)

Observação: Também é possível remover essas duas configurações de autenticação, e incluir um outro método mais simples no sistema que não requer validação com o banco de dados, basta adicionar a dependência do próprio spring. Esta autenticação consiste em um usuário padrão e uma senha pré-definida.
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

### Executar Biblioteca App
Para executar o sistema na sua máquina, seguir os seguintes passos:
1. fazer o download deste projeto em algum diretório local,
2. abrir algum terminal de comando da sua máquina (Prompt de Comando, Windows PowerShell)
3. executar o comando: ```mvn clean package```. O arquivo _**bibliotecaApp-0.0.1-SNAPSHOT.jar**_ será gerado na pasta _**target**_ do projeto,

### Página inicial da Biblioteca App
![image](https://github.com/nataliasuzuki/biblioteca-app/assets/61856025/303782c7-5fdf-4cad-b435-02fef539f3cc)
