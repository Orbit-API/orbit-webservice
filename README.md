# 🌎 orbit-webservice
![background-orbit-com-slogan](https://user-images.githubusercontent.com/56441318/171164559-775dfbf2-5a23-4b91-b0fb-6318a50cebac.png)


## ⚙ Build do Projeto
- Clone o projeto:

```bash
git clone https://github.com/Orbit-API/orbit-webservice.git
```

- 📂 Navegue para a branch develop:

```bash
git checkout develop
```

- 📂 Navegue até a pasta: 

```bash
cd orbit-webservice
```

- 📥 Instale as **Dependências**:

```bash
sudo apt update
```

```bash
sudo apt install maven
```

```bash
mvn install
```

- 📥 Instale o **Postgres** (Caso não tenha em sua máquina). Basta seguir a documentação oficial: 
https://hub.docker.com/_/postgres

- 📥 Instale o **Docker** (Caso não tenha em sua máquina). Basta seguir a documentação oficial: 
https://docs.docker.com/engine/install/ubuntu/#installation-methods

- ➡ Crie um banco de dados Postgres, com nome *orbit*. Pode-se utilizar o Docker para isso:

```bash
# No lugar de your-password adicione a senha que desejar

docker run --name postgres -e POSTGRES_PASSWORD=your-password -e POSTGRES_DB=orbit -p 5432:5432 -d postgres
```

- 📂 Navegue até a pasta *resources* e acesse o arquivo ***application.properties***

```bash
cd src/main/resources
```

- ➡ Altere as credenciais ***username*** e ***password*** para conectar com o banco de dados criado anteriormente:

```java
spring.datasource.url=jdbc:postgresql://localhost:5432/orbit
spring.datasource.username=postgres
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true

management.endpoints.web.exposure.include=*
management.endpoints.health.show-details=always
```

- ✅ Para iniciar a aplicação, retorne para pasta 📁 *orbitwebservice* e execute:

```bash
mvn spring-boot:run
```
