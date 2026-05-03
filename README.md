# 📧 Sistema de Cadastro e Notificações (Microsserviços)

Este repositório contém o projeto prático desenvolvido durante os estudos no **Java10x**. O objetivo central deste aprendizado é construir e compreender uma arquitetura baseada em microsserviços com comunicação assíncrona orientada a eventos (*Event-Driven*).

## 🎯 Sobre o Projeto

A arquitetura do sistema é dividida em dois microsserviços que trabalham em conjunto:

1.  **Microsserviço de Usuários:** Responsável por receber os dados de novos usuários e registrar o cadastro na plataforma.
2.  **Microsserviço de E-mail (Este repositório):** Um serviço dedicado exclusivamente para o envio de e-mails de confirmação de cadastro.

**A Ponte de Comunicação:** Em vez de um serviço chamar o outro diretamente (o que poderia causar lentidão se o servidor de e-mail caísse), nós utilizamos o **RabbitMQ** como um *Message Broker* (Corretor de Mensagens). O serviço de usuários apenas avisa à fila: *"Temos um novo usuário!"*. O serviço de e-mails, no seu próprio tempo, lê essa mensagem e faz o envio.

---

## 🛠️ Tecnologias e Dependências

De acordo com a configuração do nosso `pom.xml`, o projeto utiliza o ecossistema Spring Boot na versão **4.0.6** rodando no **Java 21**.

As principais dependências (*Dependencies*) utilizadas são:

* **Linguagem e Framework Base:**
    * Java 21
    * Spring Boot Starter Web MVC (Para criação dos *endpoints* / rotas da API REST)
* **Mensageria e Integração:**
    * `spring-boot-starter-amqp`: Fornece a integração com o RabbitMQ (Advanced Message Queuing Protocol) para envio e escuta das filas.
    * `spring-boot-starter-mail`: Biblioteca interna do Spring com as ferramentas para conexão SMTP e envio de e-mails.
* **Persistência e Banco de Dados:**
    * `spring-boot-starter-data-jpa`: Ferramenta (Hibernate/JPA) para mapeamento objeto-relacional, facilitando a interação com o banco de dados sem escrever SQL puro.
    * `postgresql`: O *Driver* oficial para conectar nossa aplicação ao banco de dados relacional PostgreSQL.
* **Utilitários:**
    * `spring-boot-starter-validation`: Validação de dados de entrada (como verificar se um e-mail é válido antes de processar).
    * `lombok`: Biblioteca para redução de código repetitivo (*boilerplate*), gerando automaticamente *Getters*, *Setters* e construtores.
* **Testes:**
    * Pacotes `*-test` para AMQP, JPA, Mail e WebMVC, garantindo a qualidade de cada módulo do sistema.

---

## 📚 Conteúdo do Curso e Trilha de Aprendizado

Abaixo estão os módulos e aulas abordados na construção deste projeto:

1. Microserviços - Configuração inicial do projeto
2. Rabbit Listener - Configurando Filas
3. Monolitos x Microserviços - Quando usar?
4. Rotas de usuários - Introdução à arquitetura
5. Arquitetura Kafka x RabbitMQ [Aula extra]
6. @Transactional - Garantia de rollback
7. Rabbit template - Lendo a doc na prática
8. Rabbit template + Routing key
9. Refactor + Debug + Broker
10. Configurando o Copilot
11. Trabalhando com Copilot e Schemas
12. Payloads e configurações de email SMTP
13. Refatorando com Github Copilot
14. Corrigindo bugs

---

### 💡 Dica de Estudo
Ao analisar o código fonte deste projeto, preste atenção especial nas classes anotadas com `@RabbitListener`. Elas representam os "ouvintes" da nossa aplicação, que ficam aguardando novas mensagens chegarem na fila do RabbitMQ para disparar a lógica de envio de e-mails.