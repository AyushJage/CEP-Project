# ===================================================================
# JalSathi Backend — Multi-stage Production Dockerfile
# College Community Project — DBIT, Dept. of IT
# ===================================================================

# -------------------------------------------------------------------
# Stage 1: Build JAR with Maven & Eclipse Temurin JDK 17
# -------------------------------------------------------------------
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn clean package -DskipTests -B

# -------------------------------------------------------------------
# Stage 2: Lightweight Runtime Image with Eclipse Temurin JRE 17
# -------------------------------------------------------------------
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Create dedicated non-root user and ensure runtime data & model directories exist with correct permissions
RUN addgroup --system spring && adduser --system --ingroup spring spring && \
    mkdir -p /app/data /app/models && \
    chown -R spring:spring /app

USER spring:spring

# Copy built artifact from builder stage with correct ownership
COPY --chown=spring:spring --from=builder /app/target/*.jar app.jar

# Tuned for Render.com free tier (512 MB memory limit)
ENV PORT=8080 \
    JAVA_OPTS="-Xms128m -Xmx384m -XX:+UseG1GC -XX:+ExitOnOutOfMemoryError"

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar --server.port=${PORT:-8080}"]
