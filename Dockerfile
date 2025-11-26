# Etapa 1: Construcción del proyecto con Maven Wrapper
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copiamos el proyecto completo
COPY . .

# Damos permiso para ejecutar mvnw por si fuera necesario
RUN chmod +x mvnw

# Compilamos la aplicación
RUN ./mvnw -B -DskipTests clean package


# Etapa 2: Imagen final para ejecutar Spring Boot
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copiamos el JAR construido desde la stage builder
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
