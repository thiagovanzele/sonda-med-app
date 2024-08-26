# Sonda Med Project

## Descrição

O projeto Sonda Med é uma aplicação Java desenvolvida com Spring Boot, que simula a rotina de um consultório médico, onde são inserios médicos, pacientes e são possíveis realizar agendamentos e cancelar os mesmos.

## Funcionalidades

- **Gerenciamento de Médicos**: Adicionar, atualizar, inativar e listar médicos.
- **Gerenciamento de Pacientes**: Adicionar, atualizar, inativar e listar pacientes.
- **Gerenciamento de Consultas**: Adicionar e Cancelar consultas.
- **ViaCepApi**: O projeto ultiliza a [API Via Cep](https://viacep.com.br/) para transformar o cep em uma entidade de endereço no BD.
- **Invertexto**: O projeto ultiliza a [API Invertexto](https://api.invertexto.com/api-validador-cpf-cnpj) para autenticar se um CPF é válido ou não. 

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para desenvolvimento do backend.
- **Spring Data JPA**: Para gerenciamento de persistência e banco de dados.
- **Spring Security**: Para gerenciamento e autenticação de requisições.
- **H2 Database**: Banco de dados em memória para testes.
- **PostgreSQL**: Banco de dados para desenvovimento.
- **Maven**: Gerenciador de dependências e build.
- **Swagger**: Para gerar a documentação da API.

## Pré-requisitos

Antes de começar, você precisará ter os seguintes softwares instalados:

- [Java JDK 17 ou superior](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [PostgreSQL](https://www.postgresql.org/download/)

## Instalação

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/thiagovanzele/livraria-app.git
    ```
2. **Navegue até o diretório do projeto:**:

   ```bash
   cd sonda-med
      ```
3. **Compile e execute o projeto:**

   ```bash
   mvn clean install
   mvn spring-boot:run
     ```

## Configuração do Banco de Dados

Antes de iniciar a aplicação, você precisa configurar as propriedades do banco de dados no arquivo `application.properties`.

### Passos para Configuração

1. **Abra o arquivo `application.properties`** localizado em `src/main/resources`.

2. **Atualize as propriedades do banco de dados** com as informações do seu ambiente PostgreSQL. Substitua os valores de `spring.datasource.url`, `spring.datasource.username`, e `spring.datasource.password` conforme suas configurações:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/sonda-med
   spring.datasource.username=postgres
   spring.datasource.password=

   spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true

   spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

3. **Inserir um usuário para autenticação** diretamente em seu banco de dados.
   
4. **Realizar login** através do endpoint localhost:8080/login e inserir o token em cada requisição que for disparada.

## Uso

Após iniciar a aplicação, você pode acessar a API REST através do endpoint:

- **Base URL**: `http://localhost:8080`

### Endpoints

  - `/consultas` - Requisições relacionadas a consultas.
  - `/medicos` - Requisições relacionadas a médicos.
  - `/pacientes` - Requisições relacionadas a pacientes.
 

## Exemplos 

***Requisições e endpoints disponíveis***
- ![Requisições](https://github.com/thiagovanzele/sonda-med-app/blob/main/src/main/resources/templates/imagens/requisicoes.jpg)

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo `LICENSE` para detalhes.

## Contato

Se você tiver alguma dúvida ou sugestão, entre em contato:

**Thiago Vanzele** - thiagovanzele@gmail.com  
[LinkedIn - Thiago Vanzele](https://www.linkedin.com/in/thiagovanzele)



