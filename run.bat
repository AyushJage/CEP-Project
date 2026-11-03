@echo off
chcp 65001 >nul
title JalSathi - Rainwater Harvesting & Water Conservation Monitor
echo ===============================================================================
echo   🌧️ JalSathi - Rainwater Harvesting ^& Water Conservation Monitor
echo   Don Bosco Institute of Technology (DBIT), Dept. of IT
echo ===============================================================================
echo.
echo Choose an option:
echo   [1] Start Backend (Spring Boot on http://localhost:8080)
echo   [2] Start Frontend (Web UI on http://localhost:3000)
echo   [3] Start Full System (Backend + Frontend)
echo   [4] Exit
echo.
set /p choice="Enter choice (1-4) [default: 1]: "

if "%choice%"=="" set choice=1
if "%choice%"=="1" goto run_backend
if "%choice%"=="2" goto run_frontend
if "%choice%"=="3" goto run_both
if "%choice%"=="4" exit /b 0

:run_backend
cd /d "%~dp0backend"
call run.bat
exit /b 0

:run_frontend
cd /d "%~dp0frontend"
call run.bat
exit /b 0

:run_both
echo Starting backend in new window...
start "JalSathi Backend" cmd /c "cd /d "%~dp0backend" && call run.bat"
timeout /t 3 >nul
echo Starting frontend...
cd /d "%~dp0frontend"
call run.bat
exit /b 0
