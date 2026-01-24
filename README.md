# 📚 Biblioteca API - Gerenciamento de Empréstimos

[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellij-idea)](https://www.jetbrains.com/idea/)

Bem-vindo ao **Biblioteca API**, um sistema robusto de backend desenvolvido para gerenciar o fluxo de empréstimos, devoluções e catálogo de livros. Este projeto foi construído seguindo as melhores práticas de desenvolvimento Java moderno, com foco em desacoplamento e regras de negócio consistentes.

## 🚀 Tecnologias e Ferramentas

* **Java 21**: Utilização de `Records` para DTOs e Stream API.
* **Spring Boot 3**: Framework base para a construção da API REST.
* **Spring Data JPA**: Abstração da camada de persistência.
* **H2 Database / PostgreSQL**: Banco de dados para desenvolvimento e produção.
* **Lombok**: Redução de código boilerplate.
* **ControllerAdvice**: Tratamento global de exceções.

## 🏗️ Arquitetura do Projeto

O projeto segue o padrão de camadas para garantir a manutenção e escalabilidade:

1.  **Controller**: Exposição dos endpoints REST e manipulação de DTOs.
2.  **Service**: Camada de inteligência onde residem as regras de negócio (cálculo de multas, validação de disponibilidade).
3.  **Repository**: Interface de comunicação direta com o banco de dados via JPA.
4.  **DTO (Data Transfer Object)**: Uso de `Records` para garantir que as entidades do banco não vazem para a camada de visualização.

## 🛡️ Regras de Negócio Implementadas

* **Validação de Disponibilidade**: Um livro não pode ser emprestado se já estiver ocupado.
* **Controle de Devolução**: Impedimento de devoluções duplas para o mesmo registro.
* **Cálculo de Multas**: Sistema inteligente que calcula multas automaticamente para devoluções em atraso.
* **Segurança de Dados**: Uso de DTOs para ocultar informações sensíveis dos usuários nas respostas da API.

## 🛠️ Como Executar

1.  Certifique-se de ter o **JDK 21** instalado.
2.  Clone o repositório:
    ```bash
    git clone [https://github.com/esmeraldo-dev/biblioteca_dev.git](https://github.com/esmeraldo-dev/biblioteca_dev.git)
    ```
3.  Abra o projeto no **IntelliJ IDEA**.
4.  Execute a aplicação através da classe `BibliotecaDevApplication` ou via terminal:
    ```bash
    ./mvnw spring-boot:run
    ```
5.  Acesse o console do banco H2 (em desenvolvimento) em: `http://localhost:8080/h2-console`

## 📡 Endpoints Principais

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/api/livros` | Lista todos os livros (Limpa via DTO) |
| `POST` | `/api/emprestimos` | Registra um novo empréstimo |
| `PUT` | `/api/emprestimos/{id}/devolver` | Realiza a devolução com cálculo de multa |
| `GET` | `/api/usuarios` | Lista usuários cadastrados |

---
Desenhado com ☕ e Java por [Vinícius Esmeraldo](https://github.com/esmeraldo-dev)
