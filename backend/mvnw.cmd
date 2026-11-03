@REM ----------------------------------------------------------------------------
@REM Maven Wrapper for Windows (CMD / PowerShell)
@REM ----------------------------------------------------------------------------
@echo off
setlocal

set "DIR=%~dp0"
set "WRAPPER_JAR=%DIR%.mvn\wrapper\maven-wrapper.jar"
set "WRAPPER_PROPERTIES=%DIR%.mvn\wrapper\maven-wrapper.properties"

if not exist "%WRAPPER_JAR%" (
    echo Downloading Maven Wrapper...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar' -OutFile '%WRAPPER_JAR%'"
)

set "JAVA_EXE="
if exist "%JAVA_HOME%\bin\java.exe" (
    set "JAVA_EXE=%JAVA_HOME%\bin\java.exe"
) else if exist "%JAVA_HOME%\java.exe" (
    set "JAVA_EXE=%JAVA_HOME%\java.exe"
) else (
    set "JAVA_EXE=java.exe"
)

"%JAVA_EXE%" "-Dmaven.multiModuleProjectDirectory=%DIR:~0,-1%" -cp "%WRAPPER_JAR%" org.apache.maven.wrapper.MavenWrapperMain %*
exit /b %ERRORLEVEL%
