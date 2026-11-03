# ===================================================================
# JalSathi Backend — Multi-stage Production Dockerfile
# College Community Project — DBIT, Dept. of IT
# ===================================================================

# -------------------------------------------------------------------
# Stage 1: Build JAR with Maven & Eclipse Temurin JDK 17
# -------------------------------------------------------------------
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy pom.xml and cache dependencies
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copy source code and build production JAR
COPY src ./src
RUN mvn clean package -DskipTests -B

# -------------------------------------------------------------------
# Stage 2: Lightweight Runtime Image with Eclipse Temurin JRE 17
# -------------------------------------------------------------------
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Non-root user for container security
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring:spring

# Copy built artifact from builder stage
COPY --from=builder /app/target/*.jar app.jar

ENV PORT=8080 \
    JAVA_OPTS="-Xms256m -Xmx512m -XX:+UseG1GC"

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar --server.port=${PORT:-8080}"]

