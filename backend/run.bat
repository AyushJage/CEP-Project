@echo off
chcp 65001 >nul
title Rainwater Harvesting & Water Conservation Monitor - DBIT IT Community Project
echo ===============================================================================
echo   🌧️ Rainwater Harvesting ^& Water Conservation Monitor
echo   Department of Information Technology, Don Bosco Institute of Technology
echo ===============================================================================
echo.

set "PROJECT_DIR=%~dp0"
cd /d "%PROJECT_DIR%"

echo [1/3] Checking Java environment...
where java >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Java is not installed or not in PATH! Please install Java 17+.
    pause
    exit /b 1
)

echo [2/3] Checking MySQL Server...
sc query MySQL80 | find "RUNNING" >nul
if %ERRORLEVEL% EQU 0 (
    echo [OK] MySQL80 service is currently RUNNING.
) else (
    echo [INFO] Ensuring MySQL service is active (or verify your MySQL instance is on port 3306).
)

echo.
echo [3/3] Starting Spring Boot Application on port 8080...
echo   - Web UI: http://localhost:8080
echo   - Swagger API Docs: http://localhost:8080/swagger-ui.html
echo   - Marathi default language with English switch
echo.
echo Press Ctrl+C at any time to stop the application.
echo -------------------------------------------------------------------------------

call mvnw.cmd spring-boot:run
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo [ERROR] Spring Boot application terminated with error code %ERRORLEVEL%.
    pause
)
