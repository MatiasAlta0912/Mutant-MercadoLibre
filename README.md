🧬 Mutant Detector — MercadoLibre Challenge

Este proyecto implementa la API para detectar mutantes mediante secuencias de ADN.
Forma parte del desafío técnico basado en el enunciado utilizado por MercadoLibre.

La aplicación está desarrollada con Spring Boot, utiliza H2 como base de datos en memoria,
y está desplegada en Render mediante un contenedor Docker.

👤 Integrante

Matías Altamirano
Legajo: 49928
DNI: 44.537.598

🚀 Tecnologías utilizadas

Java 21

Spring Boot 3

Spring Web MVC

Spring Data JPA

H2 Database

Lombok

Docker

Render (deployment)

Maven

🌐 API Online (Deploy)

El servicio se encuentra desplegado en Render en la siguiente URL:

👉 https://mutant-mercadolibre.onrender.com/stats

📌 Endpoints
🔍 1. Verificar si una persona es mutante

POST /mutant/

Body (JSON):
{
  "dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

Respuestas posibles:
Código	Significado
200 OK	Es mutante
403 Forbidden	No es mutante
📊 2. Obtener estadísticas

GET /stats

Ejemplo de respuesta:
{
  "count_mutant_dna": 10,
  "count_human_dna": 5,
  "ratio": 2.0
}

🧠 Lógica de detección

Una persona es mutante si en su ADN existen dos o más secuencias de 4 letras iguales que pueden estar:

En forma horizontal

En forma vertical

En forma diagonal

Ejemplo de secuencia válida:

AAAA


La matriz debe ser NxN y solo puede contener:
A, T, C, G.

🗂 Estructura del proyecto
src/
 └── main/
      └── java/com/example/mutant/
            ├── controller/
            ├── service/
            ├── repository/
            ├── model/
            └── util/

🧪 Cómo ejecutar localmente
1) Generar el .jar
mvn clean package -DskipTests

2) Ejecutar la aplicación
java -jar target/mutant-0.0.1-SNAPSHOT.jar

3) Probar endpoints

POST → http://localhost:8080/mutant/

GET → http://localhost:8080/stats

🐳 Ejecutar con Docker
Build Docker
docker build -t mutant-app .

Ejecutar contenedor
docker run -p 8080:8080 mutant-app

🗄 Base de datos

Se utiliza H2 en memoria.
Acceso web console:

http://localhost:8080/h2-console


Driver: org.h2.Driver
JDBC URL: jdbc:h2:mem:testdb

✔ Estado del proyecto

✅ Funciona localmente
✅ Funciona en Render
✅ Dockerfile funcional
✅ API online
✅ Entrega lista
