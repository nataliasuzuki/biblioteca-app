# :books: Biblioteca App
Biblioteca App é um pequeno sistema que simula um gerenciamento de uma biblioteca, como o cadastro de livros, reservas e devoluções. Possui autenticação síncrona com o banco de dados, ou seja, é necessário que o usuário esteja previamente cadastrado no banco de dados para conseguir visualizar a página inicial da Biblioteca App.

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

### Autenticação
Como já informado no início, o sistema requer autenticação e há dois possíveis métodos de autenticação no sistema:
1. utilizando a autenticação **_Basic Auth_**, que requer um usuário e senha cadastrados previamente no banco de dados,
2. utilizando a autenticação por JWT, o **_Bearer Token_**, que também requer um usuário e senha cadastrados previamente no banco de dados, porém existe um tempo de validade para a autenticação.

#### Basic Auth
Criar um _**usuario**_ para autenticação. Abaixo segue o SQL para criar o usuário **_adm_** com a senha **_adm_** na tabela **_usuario_**:
```sql
INSERT INTO biblioteca_db.usuario(data_criacao, data_nascimento, nome, ativo, documento_identificacao, email, senha, login)
VALUES(null, null, null, 1, null, null, '$2a$12$/3rVvVLUmhfgVzPSmdec0OR5OClQAmXltU0vUPMlQDIN0jsRP94oa', 'adm');
```

#### Bearer Token
Para autenticar no sistema usando o jwt, acessar o endereço http://localhost:8080/login (POST). O retorno será um json como mostrado abaixo:
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYXRhbGlhLnN1enVraSIsImV4cCI6MTY4NTUwNTM2Mn0.wMa83vD66PYH-UZyvrKtOyz5Uv7HHTtyx1f2F0coUBo",
    "validade": "480"
}
```
O token tem validade de 8 horas e esse período pode ser alterado modificando a propriedade _**security.jwt.expiration**_ no arquivo _**application.properties**_. O valor numeral deve ser informado em minutos. Para usar o token com o Postman, adicionar na configuração "Headers" do endpoint desejado:
```
Key: Authorization
Value: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYXRhbGlhLnN1enVraSIsImV4cCI6MTY4NTUwNTM2Mn0.wMa83vD66PYH-UZyvrKtOyz5Uv7HHTtyx1f2F0coUBo
```

#### Autenticação sem validação no banco de dados
Também é possível remover essas duas configurações de autenticação, e incluir um outro método mais simples no sistema que não requer validação com o banco de dados, basta adicionar a dependência do próprio spring. Esta autenticação consiste em um usuário padrão e uma senha pré-definida e já possui interface própria do spring. Para utilizar essa autenticação, basta incluir a seguinte dependência no arquivo *pom.xml*:
```maven
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
O usuário padrão é ***user*** e a senha aparece no console da IDE desta forma: ```Using generated security password: cd2ed133-f34e-4951-85e3-9fccaa8a3e915```

### Executar Biblioteca App usando arquivo jar
Para executar o sistema na sua máquina, seguir os seguintes passos:
1. Fazer o download deste projeto em algum diretório local,
2. Abrir algum terminal de comando da sua máquina (Prompt de Comando, Windows PowerShell),
3. Executar o comando ```mvn clean package```,
4. Executar o comando ```cd target```,
5. Executar o comando ```java -jar bibliotecaApp-0.0.1-SNAPSHOT.jar```,
6. Aguardar pela mensagem ```Started BibliotecaAppApplication in n seconds```,
7. Acessar o endereço http://localhost:8080. Enquanto o terminal de comando estiver aberto, a aplicação ficará em execução, caso contrário, a aplicação será encerrada.
