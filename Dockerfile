FROM maven:3.9.6-eclipse-temurin-17 AS dev
WORKDIR /app

# Copiamos el descriptor primero para aprovechar caché de dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código fuente
COPY src ./src

# Exponemos el puerto del servidor
EXPOSE 8080

# Ejecutamos la app directamente desde el código fuente
# Maven la compila y corre en un solo paso
CMD ["mvn", "spring-boot:run"]