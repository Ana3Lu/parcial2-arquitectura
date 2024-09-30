# Parcial 2

Ana Lucia Quintero & Juliana Espinel

1) a. Para ejecutar la aplicación localmente, se requiere:

*Tener instalado:
*Docker y Docker Compose 
*Java 17
*Gradle


*Se deben abrir los puertos 8080 y 3306 para que los servicios esten libres y se puedan correr

*Crear un archivo dockercompose.yml de la siguiente manera:

version: '3.8'

services:
  mysql:
    image: mysql:8.0
    container_name: mysql-container
    environment:
      MYSQL_ROOT_PASSWORD: root_password
      MYSQL_DATABASE: nombre_base_datos
      MYSQL_USER: usuario
      MYSQL_PASSWORD: usuario_password
    ports:
      - "3306:3306"
    volumes:
      - mysql-data:/var/lib/mysql

volumes:
  mysql-data:

*Se necesita ejecutar el docker compose para poder levantar la bd o el servicio de mysql
docker-compose up -d

*Se debe configurar el archivo .yml con los parametros de conexion a la base de datos

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/nombre_base_datos
    username: usuario
    password: usuario_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    database-platform: org.hibernate.dialect.MySQL8Dialect

*Se debe compilar el proyecto con gradle:
./gradlew build

*Se debe iniciar la aplicación 
./gradlew bootRun

*Se puede acceder a la aplicación desde el http://localhost:8080.

*Para crear la imagen de docker se requiere del siguiente comando:
docker build -t parcial-2 .

*Para ejecutar la imagen de docker se requiere del siguiente comando:
docker run -p 8080:8080 parcial-2
