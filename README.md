# Checkpoint 03 - Java Advanced

Aplicação que permite que administradores registrem e gerenciem clínicas, médicos e pacientes, enquanto pacientes podem marcar consultas e acessar suas informações médicas.

## Tecnologias utilizadas

- Spring Boot
- Lombok
- Spring Dev Tools
- Bean Validation
- H2 Database
- Spring Data JPA
- Swagger
- Spring Security
- Spring HATEOAS
- Spring Profiles

## Executando a aplicação

[Link para acessar a imagem no Docker Hub](https://hub.docker.com/r/techsplinter/appointment-management)

- Executando aplicação com Profile de DEV
```bash
docker run -e SPRING_PROFILES_ACTIVE=dev -p 8080:8080 techsplinter/appointment-management:latest
```

- Executando aplicação com Profile de PROD
```bash
docker run -e SPRING_PROFILES_ACTIVE=prod -p 8080:8090 techsplinter/appointment-management:latest
```

!(img-front)[https://github.com/EduardoShoiti/CP3-java/blob/master/img-front.png]

## Integrantes
- Eduardo (RM98704)
- Nathalia (RM552221)
- Marcela (RM552051)
