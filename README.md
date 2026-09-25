# Laboratório Aplicação MVC com Spring

Para configurar e executar a aplicação, consulte [EXECUCAO.md](EXECUCAO.md).

Este laboratório guia o aluno na construção de uma aplicação web em **Java** utilizando o **Spring Boot** e o padrão de arquitetura **MVC** (Model-View-Controller), com persistência de dados em um banco **MongoDB** hospedado na nuvem através do **MongoDB Atlas**.

O roteiro foi pensado para quem está tendo o primeiro contato com Java, Spring Boot e bancos de dados NoSQL, por isso cada etapa traz uma breve explicação conceitual antes da parte prática.

## Tecnologias utilizadas

Linguagens, Frameworks e Bibliotecas utilizadas na construção do projeto.

<!-- Link com os badges para inserir abaixo https://devicon.dev/ -->

<div style="display: flex; gap: 10px;">
  <img width="50px" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg">
  <img width="50px" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg">
  <img width="50px" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mongodb/mongodb-original-wordmark.svg">
  <img width="50px" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/html5/html5-original.svg">
  <img width="50px" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/javascript/javascript-plain.svg" />
  <img width="50px" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/bootstrap/bootstrap-original.svg" />
</div>

## Onde Aplicar

Este projeto pode ser aplicado em diversas situações:

- Como base de estudo para o padrão de arquitetura MVC (Model-View-Controller).
- Como ponto de partida para aplicações web em Java com Spring Boot.
- Como exemplo de integração entre uma aplicação Java e um banco de dados NoSQL em nuvem (MongoDB Atlas).
- Como base para a construção de APIs REST que sirvam tanto uma aplicação front end separada quanto páginas renderizadas no próprio servidor (Thymeleaf).

# Sumário

- [Laboratório Aplicação MVC com Spring](#laboratório-aplicação-mvc-com-spring)
  - [Tecnologias utilizadas](#tecnologias-utilizadas)
  - [Onde Aplicar](#onde-aplicar)
- [Sumário](#sumário)
  - [O padrão MVC e o Spring Boot](#o-padrão-mvc-e-o-spring-boot)
  - [Instalações](#instalações)
    - [Pré-Requisitos](#pré-requisitos)
    - [Recursos adicionais](#recursos-adicionais)
  - [Roadmap](#roadmap)
    - [Etapa 1: Inicialização do projeto com o Spring Initializr](#etapa-1-inicialização-do-projeto-com-o-spring-initializr)
      - [Passo a Passo:](#passo-a-passo)
    - [Etapa 2: Preparação e conexão com o MongoDB Atlas](#etapa-2-preparação-e-conexão-com-o-mongodb-atlas)
      - [Passo a Passo:](#passo-a-passo-1)
    - [Etapa 3: Implementação de Controllers para Views](#etapa-3-implementação-de-controllers-para-views)
      - [Passo a Passo:](#passo-a-passo-2)
    - [Etapa 4: Implementação de Controllers e Repositories para a API REST](#etapa-4-implementação-de-controllers-e-repositories-para-a-api-rest)
      - [Passo a Passo:](#passo-a-passo-3)
        - [Implementação das classes Model e Repository](#implementação-das-classes-model-e-repository)
        - [Implementação dos Controllers para endpoints de uma API Rest](#implementação-dos-controllers-para-endpoints-de-uma-api-rest)
    - [Etapa 5: Publicando a aplicação (opcional)](#etapa-5-publicando-a-aplicação-opcional)
  - [Contato](#contato)
  - [License](#license)

## O padrão MVC e o Spring Boot

Antes de colocar a mão no código, vale entender o que estamos construindo.

**MVC (Model-View-Controller)** é um padrão de arquitetura que separa uma aplicação em três camadas com responsabilidades bem definidas:

- **Model (Modelo):** representa os dados e as regras de negócio da aplicação. No nosso projeto, são as classes que descrevem as entidades (por exemplo, `Asset`, `User`) e que serão persistidas no banco de dados.
- **View (Visão):** é a camada responsável por apresentar os dados ao usuário. No Spring Boot, isso normalmente é feito com o motor de templates **Thymeleaf**, que gera páginas HTML dinâmicas.
- **Controller (Controlador):** recebe as requisições HTTP feitas pelo usuário (ou por outro sistema), aciona a lógica necessária (geralmente delegando para uma camada de **Service**) e decide o que devolver: uma página (View) ou dados em formato JSON (API REST).

No Spring Boot, esse fluxo funciona assim:

```
Requisição HTTP → Controller → Service → Repository → Banco de Dados
                       ↓
                     View (Thymeleaf) ou JSON (API REST)
```

Além das três camadas clássicas do MVC, o projeto também utiliza duas camadas auxiliares muito comuns em aplicações Spring:

- **Service:** concentra a lógica de negócio da aplicação, mantendo os Controllers simples e focados apenas em lidar com requisições/respostas HTTP.
- **Repository:** isola o acesso ao banco de dados. No nosso caso, usaremos o **Spring Data MongoDB**, que fornece interfaces prontas para operações de CRUD (Create, Read, Update, Delete) sobre o MongoDB, sem que precisemos escrever consultas manualmente.

Com esses conceitos em mente, vamos construir a aplicação passo a passo.

## Instalações

Siga com precisão as orientações de configuração do ambiente para assegurar eficácia consistente no desenvolvimento do projeto.

### Pré-Requisitos

- **[JDK 17 (Eclipse Temurin)](https://adoptium.net/temurin/releases/?version=17)** — kit de desenvolvimento Java, versão 17 ou superior, necessário para compilar e executar a aplicação.
- **[Apache Maven](https://maven.apache.org/download.cgi)** — ferramenta de build e gerenciamento de dependências do projeto. *Observação: o projeto já inclui o Maven Wrapper (`mvnw`/`mvnw.cmd`), então não é obrigatório instalar o Maven manualmente — basta usar `./mvnw` no lugar de `mvn` nos comandos deste roteiro.*
- **[Git](https://git-scm.com/downloads)** — sistema de controle de versão utilizado para clonar e versionar o projeto.
- **[IDE Java](https://www.jetbrains.com/idea/download/) (IntelliJ IDEA, Eclipse ou VS Code com extensões Java)** — ambiente de desenvolvimento para editar, executar e depurar o código.
- **Conta gratuita no [MongoDB Atlas](https://www.mongodb.com/cloud/atlas/register)** — necessária para criar o cluster (servidor) de banco de dados MongoDB utilizado pela aplicação.

### Recursos adicionais

- **[Spring Initializr](https://start.spring.io/)** — ferramenta online para gerar a estrutura inicial (boilerplate) do projeto Spring Boot.
- **[MongoDB Atlas](https://www.mongodb.com/cloud/atlas)** — serviço de banco de dados MongoDB gerenciado na nuvem, utilizado como banco de dados da aplicação.
- **[MongoDB Compass](https://www.mongodb.com/products/tools/compass)** — interface gráfica (opcional) para visualizar e consultar os dados armazenados no cluster do Atlas.
- **[Postman](https://www.postman.com/) ou [Insomnia](https://insomnia.rest/)** — ferramentas para testar manualmente os endpoints da API REST criada durante o roteiro.
- **[Render](https://render.com/)** — plataforma de hospedagem na nuvem, utilizada na etapa opcional de publicação da aplicação.

## Roadmap

Vamos dividir o roteiro em etapas claras e progressivas para facilitar o aprendizado do aluno. Aqui estão as etapas propostas:

### Etapa 1: Inicialização do projeto com o Spring Initializr

**Descrição:** Nesta etapa, vamos utilizar o Spring Initializr para gerar os arquivos de projeto básico (boilerplate) para a nossa aplicação Spring Boot. O Spring Initializr é uma ferramenta online que simplifica o processo de inicialização de projetos Spring Boot, permitindo-nos selecionar as dependências necessárias e configurar o projeto de acordo com nossas necessidades.

**Conhecimentos Fundamentais:**

- Spring Framework: Conjunto de bibliotecas que facilitam o desenvolvimento de aplicações Java empresariais.
- Spring Boot: Framework baseado no Spring Framework que simplifica o processo de configuração e desenvolvimento de aplicações Spring.
- MVC pattern: Padrão de arquitetura de software que separa os componentes de uma aplicação em Model, View e Controller (ver seção [O padrão MVC e o Spring Boot](#o-padrão-mvc-e-o-spring-boot)).

**Produto Esperado:**
Estrutura de projeto Spring Boot configurada com pacotes separados para controllers, models, repositories e services.

---

#### Passo a Passo:

1. Acesse o [Spring Initializr](https://start.spring.io/).
2. Configure o projeto selecionando as seguintes opções:
   - **Project:** Maven Project
   - **Language:** Java
   - **Spring Boot:** Escolha a versão mais recente disponível na série 3.2.x.
   - **Project Metadata:** Preencha o Group, Artifact e Name conforme desejar.
   - **Packaging:** Jar
   - **Java:** 17
   - **Dependencies:** Selecione:
     - `Spring Web` (suporte a MVC e criação de APIs REST)
     - `Thymeleaf` (motor de templates para as Views)
     - `Spring Data MongoDB` (integração com o MongoDB)
3. Clique em "Generate" para baixar o arquivo ZIP contendo o projeto gerado pelo Spring Initializr.
4. Extraia o conteúdo do arquivo ZIP em um diretório de sua escolha.
5. Abra o projeto em sua IDE de desenvolvimento Java (IntelliJ, Eclipse, VS Code, etc.).

![1713745564388](image/roteiro/1713745564388.png)

**Criar um controller inicial:**

```java
package network.webtech.labspringmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorld {

    @GetMapping("/")
    public String index() {
        return "Hello, World!";
    }
}
```

**Executar a aplicação:**
Para executar a aplicação Spring Boot, você pode usar o Maven Wrapper para compilar e executar o projeto. Na raiz do projeto, execute o seguinte comando:

```bash
./mvnw spring-boot:run
```

No Windows, utilize `mvnw.cmd spring-boot:run`.

Após iniciar, acesse [http://localhost:8080](http://localhost:8080) no navegador para ver a aplicação em execução.

**Exemplo de Estrutura de Projeto:**

```
meu-projeto
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── network
│   │   │   │       └── webtech
│   │   │   │           └── labspringmvc
│   │   │   │               ├── controllers
│   │   │   │               │   └── HelloWorld.java
│   │   │   │               ├── models
│   │   │   │               ├── repositories
│   │   │   │               └── services
│   │   │   └── resources
│   │   │       ├── static
│   │   │       ├── templates
│   │   │       └── application.properties
│   │   └── test
│   │       └── java
│   └── pom.xml
```

Nesta estrutura de projeto, os pacotes estão organizados da seguinte forma:

- **controllers:** Contém as classes responsáveis por receber as requisições HTTP e direcioná-las para os serviços apropriados.
- **models:** Contém as classes que representam as entidades de negócio da aplicação (o "M" do MVC), que serão salvas como documentos no MongoDB.
- **repositories:** Contém as interfaces de repositório para acessar os dados do banco.
- **services:** Contém as classes de serviço responsáveis por implementar a lógica de negócio da aplicação.

Com esta estrutura básica de projeto configurada, estamos prontos para avançar para as próximas etapas do desenvolvimento da nossa aplicação Spring Boot.

### Etapa 2: Preparação e conexão com o MongoDB Atlas

**Descrição:** Nesta etapa, vamos criar um cluster gratuito no MongoDB Atlas — o serviço de banco de dados MongoDB hospedado na nuvem — e conectar a nossa aplicação Spring Boot a ele. Diferente de um banco de dados relacional (como o PostgreSQL), o MongoDB armazena os dados em **documentos** (parecidos com objetos JSON) organizados em **coleções**, ao invés de tabelas e linhas.

**Conhecimentos Fundamentais:**

- MongoDB: Banco de dados NoSQL orientado a documentos, que armazena registros em formato semelhante a JSON (chamado BSON).
- MongoDB Atlas: Serviço de nuvem gerenciado pela própria MongoDB Inc. para hospedar clusters MongoDB, com um plano gratuito (M0) suficiente para este laboratório.
- Connection String: URL que descreve como a aplicação deve se conectar ao banco de dados (host, credenciais e banco a ser utilizado).
- Spring Data MongoDB: Módulo do Spring que facilita a integração entre a aplicação Java e o MongoDB.

**Produto Esperado:**
Cluster MongoDB Atlas criado e configurado, e aplicação Spring Boot conectada a esse cluster através do Spring Data MongoDB.

---

#### Passo a Passo:

1. **Criação da conta e do cluster no MongoDB Atlas:**

   a. Acesse o [MongoDB Atlas](https://www.mongodb.com/cloud/atlas/register) e crie uma conta gratuita (ou faça login se já possuir uma).

   b. Ao ser solicitado, crie um novo projeto (Project) e, em seguida, um novo cluster (Database), escolhendo a opção gratuita **M0 (Free Shared)**.

   c. Escolha um provedor de nuvem (AWS, Azure ou GCP) e uma região próxima a você — qualquer uma das opções gratuitas funciona bem para este laboratório.

   d. Aguarde alguns minutos até que o cluster seja provisionado (o status mudará para "Active" ou aparecerá um ícone verde).
2. **Configuração do acesso ao cluster:**

   a. **Criar um usuário de banco de dados:** no menu lateral, acesse *Database Access* → *Add New Database User*. Defina um nome de usuário e uma senha (evite caracteres especiais como `@` ou `/` na senha, pois eles precisam de tratamento especial na connection string) e conceda o papel `Read and write to any database`.

   b. **Liberar o acesso de rede:** no menu lateral, acesse *Network Access* → *Add IP Address*. Para fins didáticos, você pode clicar em *Allow Access from Anywhere* (`0.0.0.0/0`) — em um ambiente de produção real, restrinja o acesso apenas aos IPs necessários.
3. **Obter a Connection String:**

   a. No painel do cluster, clique em *Connect* → *Drivers*.

   b. Selecione **Java** como driver e a versão mais recente disponível.

   c. Copie a *Connection String* exibida, semelhante a:

   ```
   mongodb+srv://<username>:<password>@<cluster>.mongodb.net/?retryWrites=true&w=majority
   ```

   d. Substitua `<username>` e `<password>` pelas credenciais criadas no passo anterior, e adicione o nome do banco de dados que a aplicação irá utilizar (por exemplo, `lab-mvc`) logo após o `/`:

   ```
   mongodb+srv://<username>:<password>@<cluster>.mongodb.net/lab-mvc?retryWrites=true&w=majority
   ```
4. **Adicionar a dependência do Spring Data MongoDB:**

   Se você não selecionou a dependência ao gerar o projeto na Etapa 1, adicione-a manualmente no `pom.xml`:

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-mongodb</artifactId>
   </dependency>
   ```
5. **Configurar a conexão no `application.properties`:**

   Abra o arquivo `src/main/resources/application.properties` e adicione as propriedades de conexão, usando a Connection String obtida no passo 3:

   ```properties
   spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster>.mongodb.net/lab-mvc?retryWrites=true&w=majority
   spring.data.mongodb.database=lab-mvc
   ```

   > ⚠️ **Nunca** faça commit de credenciais reais em um repositório público. Em projetos reais, prefira ler essas informações a partir de variáveis de ambiente.
   >
6. **Testar a conexão:**

   Execute a aplicação novamente:

   ```bash
   ./mvnw spring-boot:run
   ```

   Se não houver erros de conexão no console, a aplicação já está integrada ao seu cluster MongoDB Atlas. Você também pode conferir a conexão abrindo o cluster pelo [MongoDB Compass](https://www.mongodb.com/products/tools/compass) usando a mesma Connection String.

Com isso, você terá provisionado um banco de dados MongoDB na nuvem e conectado sua aplicação Spring Boot a ele, sem a necessidade de instalar ou configurar nenhum banco de dados localmente.

### Etapa 3: Implementação de Controllers para Views

**Descrição:** Nesta etapa, vamos implementar controllers para lidar com as requisições HTTP voltadas para a obtenção de Views do padrão MVC. Utilizaremos o Spring MVC junto com o Thymeleaf para renderizar páginas HTML dinâmicas no servidor.

**Conhecimentos Fundamentais:**

- Spring MVC: Framework que facilita o desenvolvimento de aplicações web seguindo o padrão Model-View-Controller.
- Thymeleaf: Motor de templates usado pelo Spring Boot para gerar páginas HTML dinâmicas a partir de dados fornecidos pelo Controller.

**Produto Esperado:**
Controller implementado com suporte a views baseadas em templates Thymeleaf.

---

#### Passo a Passo:

**Implementação dos Controllers para Views**

Para montar nossa camada de views, vamos utilizar o mecanismo de templates do Spring Boot chamado Thymeleaf. Ele promove a separação da camada de interface da lógica do sistema.

Caso ainda não tenha adicionado, inclua no `pom.xml` a dependência do Thymeleaf:

```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

Agora, crie um controller para lidar com as requisições relacionadas à interface de usuário (Views).

```java
package network.webtech.labspringmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloWorld {

    // Requisição simples
    @GetMapping("/")
    public String index(Model model) {
        // Prepara um modelo com os dados a serem utilizados pelo template
        model.addAttribute("message", "Bem vindo ao Lab Spring MVC!");

        // Informa qual template será utilizado (home.html)
        return "home";
    }

    // Passando parâmetros no path da URL
    @GetMapping("/message/{msg}")
    public String message(@PathVariable(value = "msg") String msg, Model model) {
        model.addAttribute("message", msg);
        return "home";
    }

}
```

Crie um template Thymeleaf (por exemplo, `home.html`) na pasta `src/main/resources/templates` para renderizar a view.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Lab Spring MVC</title>
</head>
<body>
    <h1 th:text="${message}"></h1>
</body>
</html>
```

Execute a aplicação e acesse [http://localhost:8080](http://localhost:8080) e [http://localhost:8080/message/Ola](http://localhost:8080/message/Ola) para ver a página sendo renderizada com dados diferentes.

### Etapa 4: Implementação de Controllers e Repositories para a API REST

**Descrição:** Nesta etapa, vamos implementar controllers para lidar com as requisições HTTP voltadas para acesso a endpoints REST para as operações CRUD, persistindo os dados no MongoDB através do Spring Data MongoDB.

**Conhecimentos Fundamentais:**

* Spring MVC: Framework para desenvolvimento de aplicações web.
* APIs REST: Conjunto de padrões de arquitetura para comunicação entre sistemas, utilizando o protocolo HTTP.
* Spring Data MongoDB: Módulo do Spring que fornece interfaces de repositório prontas para operações de CRUD sobre coleções do MongoDB.
* Documentos e Coleções: No MongoDB, um *documento* equivale a um registro (como uma linha em uma tabela relacional) e uma *coleção* equivale a um conjunto de documentos (como uma tabela).

**Produto Esperado:**
Model, Repository e Controller implementados, fornecendo uma API REST completa para operações de CRUD com uma entidade da aplicação, persistida no MongoDB Atlas.

---

#### Passo a Passo:

##### Implementação das classes Model e Repository

Vamos criar uma entidade `Produto` para exemplificar o mapeamento de uma classe Java para um documento do MongoDB, usando as anotações do Spring Data MongoDB:

```java
package network.webtech.labspringmvc.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// @Document indica que essa classe será salva na coleção "produtos"
@Document(collection = "produtos")
public class Produto {

    // @Id indica que este campo é o identificador único do documento no MongoDB
    @Id
    private String id;

    private String nome;

    private double preco;

    // getters e setters
}
```

Agora, criamos uma interface de repositório para essa entidade. Ao estender `MongoRepository`, o Spring Data MongoDB gera automaticamente a implementação de métodos como salvar, buscar, atualizar e remover documentos — sem que precisemos escrever nenhuma consulta manualmente:

```java
package network.webtech.labspringmvc.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import network.webtech.labspringmvc.models.Produto;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
    // Métodos de CRUD (save, findById, findAll, deleteById...) já vêm prontos.
    // Métodos de consulta personalizados podem ser adicionados aqui, se necessário.
}
```

Em seguida, criamos a classe de serviço responsável por concentrar a lógica de negócio e delegar o acesso a dados para o repositório:

```java
package network.webtech.labspringmvc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import network.webtech.labspringmvc.models.Produto;
import network.webtech.labspringmvc.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto adicionarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }
}
```

##### Implementação dos Controllers para endpoints de uma API Rest

Nesse momento vamos montar uma API REST fornecendo acesso a um recurso de produtos. Para isso, vamos criar um novo controller REST, anotado com `@RestController`, para lidar com as requisições relacionadas à API.

```java
package network.webtech.labspringmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import network.webtech.labspringmvc.models.Produto;
import network.webtech.labspringmvc.services.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class Produtos {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @PostMapping
    public Produto adicionarProduto(@RequestBody Produto produto) {
        return produtoService.adicionarProduto(produto);
    }

    // Implemente métodos para atualizar, deletar e buscar produtos por id...
}
```

Execute a aplicação e utilize o Postman (ou Insomnia) para testar os endpoints:

- `GET http://localhost:8080/api/produtos` — lista todos os produtos cadastrados.
- `POST http://localhost:8080/api/produtos` — cria um novo produto, enviando um corpo JSON como `{"nome": "Notebook", "preco": 3500.00}`.

Se preferir, você também pode conferir os documentos criados diretamente no MongoDB Atlas (aba *Browse Collections* do seu cluster) ou pelo MongoDB Compass.

Com isso, você terá implementado uma camada completa de Model, Repository, Service e Controller, persistindo dados reais em um banco MongoDB na nuvem. Essa etapa é fundamental para entender como as camadas do MVC se conectam ao banco de dados.

### Etapa 5: Publicando a aplicação (opcional)

**Descrição:** Etapa opcional para publicar a aplicação em um ambiente de nuvem, sem a necessidade de Docker, já que o MongoDB Atlas cuida da hospedagem do banco de dados separadamente.

**Passo a Passo:**

1. Faça o commit e o push do código-fonte para um repositório no GitHub.
2. Crie uma conta no [Render](https://render.com/) e crie um novo *Web Service*, escolhendo o deploy a partir de uma conexão com o repositório do GitHub.
3. Configure o serviço como um ambiente **Native/Java**, informando:
   - **Build Command:** `./mvnw clean package -DskipTests`
   - **Start Command:** `java -jar target/lab-mvc-0.0.1-SNAPSHOT.jar`
4. Em *Environment Variables*, adicione a variável `SPRING_DATA_MONGODB_URI` com a Connection String do seu cluster no MongoDB Atlas (a mesma configurada na Etapa 2), evitando expor a credencial diretamente no código.
5. Finalize a criação do serviço e aguarde o build e o deploy serem concluídos. Sua aplicação estará disponível em uma URL fornecida pelo Render, já conectada ao mesmo banco MongoDB Atlas utilizado localmente.

## Contato

Rommel Carneiro - [rommelcarneiro@gmail.com](mailto:rommelcarneiro@gmail.com).

GitHub: [github.com/rommelcarneiro](https://github.com/rommelcarneiro)

## License

Este projeto é licenciado sob a [Nome da Licença](URL da Licença) - veja o arquivo [LICENSE.md](LICENSE.md) para mais detalhes.
