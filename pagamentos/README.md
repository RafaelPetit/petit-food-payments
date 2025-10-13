# 💳 Sistema de Pagamentos - Microsserviços com Java e Spring

Este projeto faz parte dos meus estudos sobre **arquitetura de microsserviços** com **Java** e **Spring Boot**.  
Foi desenvolvido com o objetivo de aplicar na prática os principais conceitos da arquitetura distribuída moderna.

---

## 🚀 Objetivo do Projeto

Implementar um **sistema de pagamentos** utilizando a arquitetura de **microsserviços**, aplicando boas práticas de comunicação entre serviços, escalabilidade e resiliência.

---

## 🧩 Tecnologias e Ferramentas

- **Java 17+**
- **Spring Boot**
- **Spring Cloud Netflix (Eureka, OpenFeign)**
- **Spring Cloud Gateway**
- **MySQL**
- **Maven**
- **Postman**
- **Lombok**
- **Docker (opcional)**

---

## 🏗️ Estrutura do Projeto

O sistema é composto por múltiplos microsserviços, cada um com uma responsabilidade clara:

| Serviço | Descrição |
|----------|------------|
| **payment-service** | Responsável por gerenciar transações de payment. Conecta-se ao banco MySQL. |
| **order-service** | Serviço complementar que se comunica com o serviço de payment. |
| **discovery-service (Eureka Server)** | Serviço de registro e descoberta, permitindo que os microsserviços encontrem uns aos outros dinamicamente. |
| **gateway-service** | API Gateway responsável por centralizar e rotear as requisições para os microsserviços. |

---

## 🔄 Conceitos Aplicados

### 1. **Arquitetura de Microsserviços**
Decomposição de uma aplicação monolítica em múltiplos serviços independentes, com comunicação via HTTP e registro dinâmico.

### 2. **Service Discovery com Eureka**
Cada microsserviço se registra no **Eureka Server**, que atua como um **service registry**.  
Com isso, os serviços se descobrem automaticamente e se comunicam sem depender de endereços fixos.

### 3. **API Gateway**
Implementado com **Spring Cloud Gateway**, ele:
- Centraliza as requisições dos clientes;
- Faz roteamento dinâmico para os microsserviços registrados no Eureka;
- Permite autenticação, logging e balanceamento de carga.

### 4. **Comunicação entre Microsserviços**
Implementada com **Spring OpenFeign**, permitindo chamadas HTTP declarativas e simples entre serviços.

### 5. **Resiliência com Circuit Breaker e Fallback**
Aplicação de **circuit breaker** para tratar falhas entre microsserviços e implementar respostas alternativas (**fallbacks**) em caso de erro.

---

## 🗃️ Banco de Dados

O **payment-service** utiliza **MySQL** como banco de dados relacional.  
As migrações são gerenciadas via **Flyway** ou **Liquibase**, garantindo versionamento e consistência do schema.

---

## 🧠 Aprendizados Principais

- Criação e configuração de um microsserviço com **Spring Boot** e **JPA**
- Implementação de **Repository**, **Service** e **Controller** seguindo o padrão **MVC**
- Uso de **DTOs** para transferir dados de forma segura e desacoplada
- Configuração de **Migrations** e testes de integração com **Postman**
- Implementação de **Service Discovery**, **API Gateway** e **Load Balancing**
- Comunicação **síncrona entre microsserviços** via **OpenFeign**
- Implementação de **Circuit Breaker** e **Fallback** para tolerância a falhas

---

### Pré-requisitos
- Java 17+
- Maven
- MySQL em execução
- (Opcional) Docker e Docker Compose
