# Etapa 1: construir el JAR con Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar el archivo pom.xml y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Compilar el proyecto y empaquetar en un JAR
RUN mvn clean package -DskipTests

# Etapa 2: imagen ligera para ejecutar la app
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copiar el JAR generado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto de la aplicación (coincide con server.port)
EXPOSE 8090

# Comando para ejecutar el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]
