# 🧬 Mutant Detector – MercadoLibre Challenge  
API REST desarrollada en Java + Spring Boot que determina si un ADN pertenece a un mutante según criterios del challenge técnico de MercadoLibre.

Incluye:
- Validación exhaustiva del ADN (nulo, vacío, NxN, caracteres válidos)
- Algoritmo mutante optimizado (horizontal, vertical, diagonal)
- Persistencia en base de datos (H2)
- Estrategia de deduplicación por hash SHA-256
- Cálculo de estadísticas
- Tests unitarios + tests de integración
- Manejo robusto de errores con excepciones personalizadas
- Documentación automática de API mediante Swagger/OpenAPI

---

## 🚀 Tecnologías utilizadas
- Java 17 / 21  
- Spring Boot 3.x  
- Spring Web  
- Spring Data JPA  
- H2 Database  
- Lombok  
- Swagger / OpenAPI  
- Maven  
- JUnit 5  

---

## 📁 Estructura del proyecto

src
├── main
│ ├── java/com/example/mutant
│ │ ├── controller → Endpoints REST
│ │ ├── service → Lógica de negocio
│ │ ├── repository → Persistencia con JPA
│ │ ├── model → Entidades JPA
│ │ ├── dto → Request/Response DTOs
│ │ ├── util → Algoritmo + Hash
│ │ ├── exception → Custom exceptions + handler
│ │ └── config → Swagger/OpenAPI
│ └── resources
│ └── application.properties
└── test
├── controller → Tests de integración
├── util → Tests unitarios
└── MutantApplicationTests


---

# 🧬 **1. Endpoint: POST /mutant**

### ✔ Detecta si un ADN es mutante

### 🔹 **Request (JSON)**

{
  "dna": [
    "ATGCGA",
    "CAGTGC",
    "TTATGT",
    "AGAAGG",
    "CCCCTA",
    "TCACTG"
  ]
}
🔹 Responses
Estado	Descripción
200 OK	El ADN es mutante
403 Forbidden	El ADN es humano
400 Bad Request	Formato inválido (no NxN, caracteres no permitidos, etc.)

📊 2. Endpoint: GET /stats
Retorna las estadísticas almacenadas:

{
  "count_mutant_dna": 40,
  "count_human_dna": 100,
  "ratio": 0.4
}

📚 Documentación Swagger
Disponible al levantar la aplicación:
👉 http://localhost:8080/swagger-ui/index.html
🗄 Base de datos H2
Consola H2:
👉 http://localhost:8080/h2-console
Driver: org.h2.Driver
JDBC URL: jdbc:h2:mem:testdb

🧩 Algoritmo Mutante
El algoritmo escanea la matriz ADN buscando secuencias de 4 caracteres consecutivos iguales de forma:
Horizontal
Vertical
Diagonal (↘)
Diagonal inversa (↗)

Se considera mutante si encuentra al menos 2 secuencias válidas.

🔐 Estrategia de deduplicación
Cada ADN se convierte en un hash SHA-256:
MessageDigest digest = MessageDigest.getInstance("SHA-256");
Evita guardar ADN repetidos y reduce carga del sistema.

🧪 Testing
La suite de tests incluye:
✔ Tests unitarios (MutantDetectorTest)
Mutante
Humano
ADN no válido
Matriz no NxN
Caracter inválido

✔ Tests de integración
POST /mutant 200
POST /mutant 403
GET /stats 200

Todos los tests se ejecutan con:
mvn clean test

📦 Ejecutar el proyecto
Requisitos:
Java 17+
Maven 3.8+

Ejecutar:
mvn spring-boot:run

👨‍🎓 Autor
Matias Altamirano

Proyecto final de Desarrollo de Software
