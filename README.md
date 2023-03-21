## 💻 Sobre o projeto

Voll.med é uma clínica médica fictícia que precisa de um aplicativo para gestão de consultas. O aplicativo deve possuir funcionalidades que permitam o cadastro de médicos e de pacientes, e também o agendamento e cancelamento de consultas.

Enquanto um time de desenvolvimento será responsável pelo aplicativo mobile, o nosso será responsável pelo desenvolvimento da API Rest desse projeto.

---

## ⚙️ Funcionalidades

- [x] CRUD de médicos;
- [x] CRUD de pacientes;
- [x] Agendamento de consultas(em breve);
- [x] Cancelamento de consultas(em breve);

---

## 🎨 Layout

O layout da aplicação mobile está disponível neste link: <a href="https://www.figma.com/file/N4CgpJqsg7gjbKuDmra3EV/Voll.med">Figma</a>

---

## 📄 Documentação

A documentação das funcionalidades da aplicação pode ser acessada neste link: <a href="https://trello.com/b/O0lGCsKb/api-voll-med">Trello</a>

---

## 🛠 Tecnologias

As seguintes tecnologias foram utilizadas no desenvolvimento da API Rest do projeto:

- **[Java 17](https://www.oracle.com/java)**
- **[Spring Boot 3](https://spring.io/projects/spring-boot)**
- **[Maven](https://maven.apache.org)**
- **[MySQL](https://www.mysql.com)**
- **[Hibernate](https://hibernate.org)**
- **[Flyway](https://flywaydb.org)**
- **[Lombok](https://projectlombok.org)**
- **[Docker](https://www.docker.com/)**

---

## 📝 Licença

Projeto desenvolvido por [Alura](https://www.alura.com.br) e utilizado nos cursos de Spring Boot.

Instrutor: [Rodrigo Ferreira](https://cursos.alura.com.br/user/rodrigo-ferreira) 

## 🚀 Executando

Para executar o projeto execute o comando abaixo em seu terminal

```

kind create cluster --config kind.config.yaml
kind apply -f ingress.yaml

Aguarde a execução do comando dar metch 
kubectl wait --namespace ingress-nginx --for=condition=ready pod --selector=app.kubernetes.io/component=controller --timeout=90s

kubectl apply -f kubernetes/database/svc-mysql8.yaml
kubectl apply -f kubernetes/database/configMap-mysql8.yaml 
kubectl apply -f kubernetes/database/deploy-mysql8.yaml 

kubectl apply -f kubernetes/api/svc-api_medvoll.yaml 
kubectl apply -f kubernetes/api/deploy-api_medvoll.yaml

kubectl apply -f kubernetes/ingress/ingress_apimedvoll.yaml


```

### Observação

Altere o arquivo hosst ( windows || Linux ) e inclua o seu ip local, para
o domínio que determinamos no ingress, ou seja:

em hosts inclua

192.168.1.110 api.medicos 

Feito isso você poderá a api localmente no endereço abaixo:

ULR: http://api.medicos/swagger-ui/index.html#

---
