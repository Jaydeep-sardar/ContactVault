@echo off
echo Compiling Contact Vault...
javac *.java

if %errorlevel% equ 0 (
    echo.
    echo Compilation successful! Starting Contact Vault...
    echo.
    java ContactVault
) else (
    echo.
    echo Compilation failed! Please check your Java files for errors.
    pause
)
