# 📚 Biblioteca API - Gerenciamento de Empréstimos

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=springboot)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellij-idea)
![JUnit5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)

Bem-vindo ao **Biblioteca API**, um sistema robusto de backend desenvolvido para gerenciar o fluxo de empréstimos, devoluções e catálogo de livros. Este projeto foi construído seguindo as melhores práticas de desenvolvimento Java moderno, com foco em desacoplamento e regras de negócio consistentes.

---

## 🚀 Tecnologias e Ferramentas

- **Java 21**: Utilização de `Records` para DTOs e Stream API.
- **Spring Boot 3**: Framework base para a construção da API REST.
- **Spring Data JPA**: Abstração da camada de persistência.
- **H2 Database / PostgreSQL**: Banco de dados para desenvolvimento e produção.
- **Lombok**: Redução de código boilerplate.
- **ControllerAdvice**: Tratamento global de exceções customizado.
- **JUnit 5 & Mockito**: Testes unitários no padrão AAA (Arrange, Act, Assert).

---

## 🏗️ Arquitetura do Projeto

O projeto segue o padrão de camadas para garantir manutenção e escalabilidade:

1. **Controller**: Exposição dos endpoints REST e manipulação de DTOs.
2. **Service**: Camada de inteligência onde residem as regras de negócio (cálculo de multas, validação de disponibilidade).
3. **Repository**: Interface de comunicação direta com o banco de dados via JPA.
4. **DTO (Data Transfer Object)**: Uso de `Records` para garantir que as entidades do banco não vazem para a camada de visualização.

---

## 🛡️ Regras de Negócio Implementadas

- **Validação de Disponibilidade**: Um livro não pode ser emprestado se já estiver ocupado.
- **Controle de Devolução**: Impedimento de devoluções duplas para o mesmo registro.
- **Cálculo de Multas**: Sistema inteligente que calcula multas automaticamente para devoluções em atraso.
- **Segurança de Dados**: Uso de DTOs para ocultar informações sensíveis dos usuários nas respostas da API.
- **Relacionamentos Complexos**: Gestão de vínculos entre Autores, Categorias e Obras com integridade referencial garantida pelo JPA.

---

## 📡 Endpoints Principais

### 📖 Livros

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/livros` | Lista todos os livros (campos sensíveis ocultos via DTO) |
| `POST` | `/api/livros` | Cadastra um novo livro |
| `GET` | `/api/livros/{id}` | Busca livro por ID |
| `PUT` | `/api/livros/{id}` | Atualiza dados de um livro |
| `DELETE` | `/api/livros/{id}` | Remove um livro do catálogo |

### 👤 Usuários

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/usuarios` | Lista usuários cadastrados |
| `POST` | `/api/usuarios` | Cadastra um novo usuário |

### 🔄 Empréstimos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/emprestimos` | Registra um novo empréstimo |
| `PUT` | `/api/emprestimos/{id}/devolver` | Realiza a devolução com cálculo de multa automático |
| `GET` | `/api/emprestimos` | Lista todos os empréstimos |

### ✍️ Autores e Categorias

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/autores` | Lista todos os autores cadastrados |
| `POST` | `/api/autores` | Cadastra um novo autor |
| `GET` | `/api/categorias` | Lista todas as categorias |
| `POST` | `/api/categorias` | Cadastra uma nova categoria |

---

## ⚙️ Como Executar

### Pré-requisitos

- **JDK 21** instalado
- **PostgreSQL** instalado (para perfil de produção) **OU** use o H2 para desenvolvimento rápido

### Passo a Passo (Windows + IntelliJ IDEA)

**1. Clone o repositório:**

```bash
git clone https://github.com/esmeraldo-dev/biblioteca_dev.git
cd biblioteca_dev
```

**2. Abra o projeto no IntelliJ:** `File > Open`, selecione a pasta do projeto.

**3. Aguarde o Maven** baixar as dependências automaticamente (acompanhe no canto inferior direito da IDE).

**4. Execute a aplicação** via classe `BibliotecaDevApplication` ou terminal:

```bash
.\mvnw.cmd spring-boot:run
```

**5. Acesse o console H2** (ambiente de dev):

🔗 http://localhost:8080/h2-console

```
JDBC URL: jdbc:h2:mem:testdb
User:     sa
Senha:    (deixe em branco)
```

---

## 🧪 Rodando os Testes

Execute a suite completa de testes via terminal:

```bash
.\mvnw.cmd test
```

Os testes cobrem os seguintes fluxos críticos, seguindo o padrão **AAA (Arrange, Act, Assert)**:

- Validação de disponibilidade de livros (empréstimo de livro já ocupado deve lançar exceção)
- Impedimento de devolução duplicada para o mesmo registro
- Cálculo correto de multas por atraso na devolução
- Isolamento de dados sensíveis via DTO nas respostas

---

## 🔮 Melhorias Futuras (Roadmap)

- [ ] **Autenticação**: Implementar Spring Security + JWT para proteção dos endpoints.
- [ ] **Paginação**: Adicionar paginação e filtros avançados nas listagens.
- [ ] **Swagger/OpenAPI**: Documentação interativa dos endpoints.
- [ ] **CI/CD**: Pipeline de build e testes via GitHub Actions.

---

## 👨‍💻 Autor

**Vinícius Esmeraldo**  
*Desenvolvedor Backend Java*

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/viniciusesmeraldo)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/esmeraldo-dev)

---

*Projeto desenvolvido com foco em Clean Code e Arquitetura Segura.*
