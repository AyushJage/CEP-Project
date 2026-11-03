@echo off
chcp 65001 >nul
title JalSathi - Frontend Web Server
echo ===============================================================================
echo   🌧️ JalSathi Frontend UI
echo   Rainwater Harvesting ^& Water Conservation Monitor
echo ===============================================================================
echo.

cd /d "%~dp0"

echo Checking available web servers...

where npx >nul 2>nul
if %ERRORLEVEL% EQU 0 (
    echo [OK] Node/npx detected. Starting local server on http://localhost:3000 ...
    echo Opening browser...
    start http://localhost:3000/index.html
    npx -y serve -l 3000 .
    exit /b 0
)

where python >nul 2>nul
if %ERRORLEVEL% EQU 0 (
    echo [OK] Python detected. Starting local server on http://localhost:3000 ...
    echo Opening browser...
    start http://localhost:3000/index.html
    python -m http.server 3000
    exit /b 0
)

echo [INFO] Python or Node not found in PATH. Opening index.html in default browser...
start index.html
pause
