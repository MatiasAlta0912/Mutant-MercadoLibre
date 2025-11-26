# ========================================
# ETAPA 1: BUILD (Compilación)
# ========================================
# Imagen base con Maven + OpenJDK 17 para compilar el proyecto
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Crear directorio de trabajo
WORKDIR /app

# Copiar pom.xml primero para cache del sistema de dependencias
COPY pom.xml .

# Descargar dependencias para acelerar build
RUN mvn dependency:go-offline -B

# Copiar TODO el código fuente
COPY src ./src

# Compilar el proyecto y generar el .jar
RUN mvn clean package -DskipTests -B

# ========================================
# ETAPA 2: RUNTIME (Ejecución)
# ========================================
# Imagen ligera con JRE 17 (sin herramientas de desarrollo)
FROM eclipse-temurin:17-jre

# Directorio donde se ejecutará la app
WORKDIR /app

# Copiar el JAR construido en la etapa "build"
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto que usa Spring Boot
EXPOSE 8080

# Comando de inicio de la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
