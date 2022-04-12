# orbit-webservice
![background-orbit_Prancheta 1](https://user-images.githubusercontent.com/56441318/160112708-193a18fe-2241-427c-8fe0-2dc23324b48a.png)

Este repositório é destinado à API do produto OneCar, seu site de vendas de carros.

## Build do Projeto
- Clone o projeto:

```bash
git clone https://github.com/Orbit-API/orbit-webservice.git
```

- Navegue para a branch develop:

```bash
git checkout develop
```

- Instale as dependências:

```bash
cd orbit-webservice
```

```bash
sudo apt update
```

```bash
sudo apt install maven
```

```bash
mvn install
```

- Crie um banco de dados Postgres, com nome *orbit*.
- Pode-se utilizar o Docker para isso:

```bash
# No lugar de your-password adicione a senha que desejar

docker run --name postgres -e POSTGRES_PASSWORD=your-password -e POSTGRES_DB=orbit -p 5432:5432 -d postgres
```

- Navegue até a pasta *resources* e acesse o arquivo ***application.properties***

```bash
cd src/main/resources
```

- Altere as credenciais ***username*** e ***password*** para conectar com o banco de dados criado anteriormente:

```java
spring.datasource.url=jdbc:postgresql://localhost:5432/orbit
spring.datasource.username=postgres
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true

management.endpoints.web.exposure.include=*
management.endpoints.health.show-details=always
```

- Para iniciar a aplicação, navegue até a pasta *orbitwebservice* e execute o comando:

```bash
cd ../java/com/api/orbitwebservice

```
