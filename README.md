<h1 align="center">Games Key Shop</h1>

## Description.
The project is written in the Java programming language using the Spring Boot framework. PostgreSQL is used as a database. Register and start selling your products!

## About the project.
- Shop implemented with [Java](https://www.oracle.com/java/) and [Spring Boot](https://spring.io/projects/spring-boot) backend framework.
- On frontend you can find an ".ftlh" expansion. With FTLH you can create a java code into html file. More info [HERE](https://websparrow.org/spring/spring-boot-freemarker-example).

## Contributor.
- [vnJ64](https://github.com/vnj64)
## Project setup.
```
# Clone repository.
git clone git@github.com:vnj64/steam_key_shop.git

# Move to the root.
cd steam_key_shop/

# Create docker container with postgreSQL.
docker run --name {container_name} -e POSTGRES_USER={postgres_user} -e POSTGRES_PASSWORD={postgres_password} -p {port}:5432 -d postgres

# Enter a data into src/main/resources/application.properties
spring.datasource.url=jdbc:postgresql://localhost:{port}/{postgres_user}
spring.datasource.username={postgres_user}
spring.datasource.password={postgres_password}

# Run MainApplication file and be happy 😁!
javac MainApplication.java
java MainApplication
```

Good luck ✅!