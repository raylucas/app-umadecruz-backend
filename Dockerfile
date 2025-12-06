# =========================
# 1) Etapa de Build
# =========================
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# Copia configuração do Maven
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Dá permissão para executar o mvnw
RUN chmod +x mvnw

# Baixa dependências antes para aproveitar cache
RUN ./mvnw dependency:go-offline

# Copia o código-fonte
COPY src src

# Compila a aplicação (gera .jar)
RUN ./mvnw clean package -DskipTests


# =========================
# 2) Etapa de Runtime (imagem leve)
# =========================
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia o jar compilado na imagem final
COPY --from=builder /app/target/*.jar app.jar

# Porta usada pelo Spring Boot no Render
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
