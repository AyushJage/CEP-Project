# JalSathi — Backend (Spring Boot REST API)

Spring Boot 3.x REST API & analytical engine for the Rainwater Harvesting & Water Conservation Monitor.

---

## 🚀 How to Open in IntelliJ IDEA

1. **Launch IntelliJ IDEA**.
2. Click **Open** (or go to **File** &rarr; **Open...**).
3. Browse to and select the **`backend`** folder:
   ```
   C:\Users\ayush\OneDrive\Desktop\Community Project\backend
   ```
4. Click **OK** / **Open as Project**.
5. IntelliJ IDEA will automatically recognize `pom.xml` at the root and import all Maven dependencies.
6. Once indexing completes:
   - Navigate to `src/main/java/com/dbit/rwh/RwhMonitorApplication.java`.
   - Click the green **Run** arrow (▶) next to `public static void main(String[] args)` (or press `Shift + F10`).
7. Your backend will start on **`http://localhost:8080`**.

---

## 🛠️ How to Run from Command Line / Terminal

### Using `run.bat`:
Simply double-click `run.bat` in this folder.

### Using Maven Wrapper:
```powershell
.\mvnw.cmd spring-boot:run
```

---

## 📁 Backend Directory Structure

```
backend/
├── pom.xml                                  # Maven Project Object Model (Root)
├── mvnw, mvnw.cmd, .mvn/                    # Maven Wrapper (no separate Maven install required)
├── run.bat                                  # Startup batch script
├── data/                                    # Runtime database directory
└── src/
    ├── main/
    │   ├── java/com/dbit/rwh/
    │   │   ├── config/                      # SecurityConfig, CorsConfig, WebConfig, SwaggerConfig
    │   │   ├── controller/                  # REST API Controllers (Auth, Tank, Harvest, Fertility, AI, etc.)
    │   │   ├── dto/                         # Request and Response Data Transfer Objects
    │   │   ├── entity/                      # JPA Entities (User, Tank, SoilReport, Rainfall, etc.)
    │   │   ├── repository/                  # Spring Data JPA Repositories
    │   │   ├── security/                    # JWT Authentication Filter & Token Provider
    │   │   ├── service/                     # Business logic & Kaggle/Weka ML engines
    │   │   └── RwhMonitorApplication.java   # Spring Boot Main Entry Point
    │   └── resources/
    │       ├── application.properties       # Default profile (H2 database + auto schema)
    │       ├── application-mysql.properties # Production MySQL 8.0 profile
    │       ├── schema.sql, data.sql         # Database initialization scripts
    │       └── data/                        # 8 bundled agricultural & climate CSV datasets
    └── test/
        ├── java/com/dbit/rwh/service/       # Unit & Integration test suite
        └── resources/                       # Test configuration
```

---

## 🌐 Endpoints & Documentation

- **Web UI:** `http://localhost:8080/` *(served seamlessly from `../frontend`)*
- **Swagger UI API Docs:** `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON Spec:** `http://localhost:8080/v3/api-docs`
- **H2 Database Console:** `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:file:./data/rwh_db`)

---

## 🧪 Running Tests
In IntelliJ IDEA, right-click `src/test/java` &rarr; **Run 'All Tests'**, or execute:
```powershell
.\mvnw.cmd test
```
All 14 tests will execute and pass.
