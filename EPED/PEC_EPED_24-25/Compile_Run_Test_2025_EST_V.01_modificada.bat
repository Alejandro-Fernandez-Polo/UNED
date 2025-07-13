@echo off

REM === Mensaje bienvenida
echo Se va a compilar la practica 2024/2025 con las dependencias y restricciones


REM === Inputs ===
set SRC_DIR=%cd%\src
set BIN_DIR=\bin
set MAIN=es/uned/lsi/eped/pract2024_2025/Main
set JAVA_HOME_JDK="C:\Program Files\Java\jdk-23"
set TMP_FOLDER=%cd%\juego_de_pruebas_2025\tmp

IF %JAVA_HOME_JDK%=="" (

	IF "%JAVA_HOME%" == "" (
	    echo Modifica la variable del archivo bat JAVA_HOME_JDK 
	    pause
	    exit
	) ELSE (
	    set JAVA_HOME_JDK="%JAVA_HOME%"
	)
)


REM === Mostramos variables ===
echo Carpeta codigo = %SRC_DIR%
echo Carpeta bin = %BIN_DIR%
echo Clase principal = %MAIN%
echo JAVA_HOME_JDK = %JAVA_HOME_JDK%
echo. 
echo.


REM === Clean and make temp dir ===
echo Limpiando compilacion anterior 
rd /q /s "%TMP_FOLDER%"
pause
if not exist "%TMP_FOLDER%" mkdir "%TMP_FOLDER%" 
mkdir "%TMP_FOLDER%%BIN_DIR%"
mkdir "%TMP_FOLDER%\src"
mkdir "%TMP_FOLDER%\src\es"
mkdir "%TMP_FOLDER%\src\es\uned"
mkdir "%TMP_FOLDER%\src\es\uned\lsi"
mkdir "%TMP_FOLDER%\src\es\uned\lsi\eped"
mkdir "%TMP_FOLDER%\src\es\uned\lsi\eped\pract2024_2025"
xcopy /s/q "%SRC_DIR%\es\uned\lsi\eped\pract2024_2025" "%TMP_FOLDER%\src\es\uned\lsi\eped\pract2024_2025"
xcopy /s/y/q "juego_de_pruebas_2025\lib\src" "%TMP_FOLDER%\src\es\uned\lsi\eped\pract2024_2025"

echo.
echo.
pause


REM ===

REM ===========================================
REM === Comprobacion uso TAD equipo docente ===
REM ===========================================
echo Comprobando el uso de estructuras de datos del equipo docente
cd "%TMP_FOLDER%\src\es\uned\lsi\eped\pract2024_2025"
find "import" TaskPlannerSequence.java TaskPlannerTree.java | find /v "es.uned.lsi.eped.DataStructures"
cd "../../../../../../../../"
echo Si se muestra algun "import" en el mensaje anterior es posible que no se este haciendo uso de las estructuras de datos del equipo docente
echo. 
echo.
pause


REM ===

REM ===============
REM === Compile ===
REM ===============
echo Compilando en carpeta temporal

%JAVA_HOME_JDK%"\bin\javac" -d "%TMP_FOLDER%%BIN_DIR%" -sourcepath "%TMP_FOLDER%\src" -cp "juego_de_pruebas_2025/lib/TAD_modified.jar" "%TMP_FOLDER%\src\%MAIN%.java"
if errorlevel 1 (
	echo Compilacion con errores
	pause
	exit /B 1
)

echo Compilacion sin errores
echo.
echo.
pause

REM ==============================================================================

REM =========================================
REM === Run Prueba Estudiantes 1 prueba add 100 ===
REM =========================================
echo Ejecutando el programa con prueba 1 prueba add 100 SEQUENCE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2025/pruebasmias/add_100.txt" "juego_de_pruebas_2025/salida/Salida_EST_SEQ_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba 1 prueba add 1000 SEQUENCE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2025/pruebasmias/add_1000.txt" "juego_de_pruebas_2025/salida/Salida_EST_SEQ_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba 1 prueba add 10000 SEQUENCE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2025/pruebasmias/add_10000.txt" "juego_de_pruebas_2025/salida/Salida_EST_SEQ_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba 1 prueba add 10000 SEQUENCE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2025/pruebasmias/add_100000.txt" "juego_de_pruebas_2025/salida/Salida_EST_SEQ_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba 1 prueba add 1000000 SEQUENCE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2025/pruebasmias/add_1000000.txt" "juego_de_pruebas_2025/salida/Salida_EST_SEQ_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba 1 prueba delete 100 SEQUENCE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2025/pruebasmias/delete_100.txt" "juego_de_pruebas_2025/salida/Salida_EST_SEQ_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba 1 prueba delete 1000 SEQUENCE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2025/pruebasmias/delete_1000.txt" "juego_de_pruebas_2025/salida/Salida_EST_SEQ_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba 1 prueba delete 10000 SEQUENCE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2025/pruebasmias/delete_10000.txt" "juego_de_pruebas_2025/salida/Salida_EST_SEQ_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba 1 prueba delete 100000 SEQUENCE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2025/pruebasmias/delete_100000.txt" "juego_de_pruebas_2025/salida/Salida_EST_SEQ_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba 1 prueba delete 1000000 SEQUENCE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2025/pruebasmias/delete_1000000.txt" "juego_de_pruebas_2025/salida/Salida_EST_SEQ_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba 1 prueba move 100 SEQUENCE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2025/pruebasmias/move_100.txt" "juego_de_pruebas_2025/salida/Salida_EST_SEQ_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba 1 prueba move 1000 SEQUENCE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2025/pruebasmias/move_1000.txt" "juego_de_pruebas_2025/salida/Salida_EST_SEQ_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba 1 prueba move 10000 SEQUENCE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2025/pruebasmias/move_10000.txt" "juego_de_pruebas_2025/salida/Salida_EST_SEQ_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba 1 prueba move 100000 SEQUENCE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2025/pruebasmias/move_100000.txt" "juego_de_pruebas_2025/salida/Salida_EST_SEQ_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba 1 prueba move 1000000 SEQUENCE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2025/pruebasmias/move_1000000.txt" "juego_de_pruebas_2025/salida/Salida_EST_SEQ_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba add 100 TREE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" TREE "juego_de_pruebas_2025/pruebasmias/add_100.txt" "juego_de_pruebas_2025/salida/Salida_EST_TREE_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba add 1000 TREE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" TREE "juego_de_pruebas_2025/pruebasmias/add_1000.txt" "juego_de_pruebas_2025/salida/Salida_EST_TREE_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba add 10000 TREE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" TREE "juego_de_pruebas_2025/pruebasmias/add_10000.txt" "juego_de_pruebas_2025/salida/Salida_EST_TREE_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba add 100000 TREE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" TREE "juego_de_pruebas_2025/pruebasmias/add_100000.txt" "juego_de_pruebas_2025/salida/Salida_EST_TREE_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba add 1000000 TREE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" TREE "juego_de_pruebas_2025/pruebasmias/add_1000000.txt" "juego_de_pruebas_2025/salida/Salida_EST_TREE_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba delete 100 TREE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" TREE "juego_de_pruebas_2025/pruebasmias/delete_100.txt" "juego_de_pruebas_2025/salida/Salida_EST_TREE_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba delete 1000 TREE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" TREE "juego_de_pruebas_2025/pruebasmias/delete_1000.txt" "juego_de_pruebas_2025/salida/Salida_EST_TREE_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba delete 10000 TREE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" TREE "juego_de_pruebas_2025/pruebasmias/delete_10000.txt" "juego_de_pruebas_2025/salida/Salida_EST_TREE_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba delete 100000 TREE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" TREE "juego_de_pruebas_2025/pruebasmias/delete_100000.txt" "juego_de_pruebas_2025/salida/Salida_EST_TREE_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba delete 1000000 TREE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" TREE "juego_de_pruebas_2025/pruebasmias/delete_1000000.txt" "juego_de_pruebas_2025/salida/Salida_EST_TREE_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba move 100 TREE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" TREE "juego_de_pruebas_2025/pruebasmias/move_100.txt" "juego_de_pruebas_2025/salida/Salida_EST_TREE_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba move 1000 TREE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" TREE "juego_de_pruebas_2025/pruebasmias/move_1000.txt" "juego_de_pruebas_2025/salida/Salida_EST_TREE_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba move 10000 TREE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" TREE "juego_de_pruebas_2025/pruebasmias/move_10000.txt" "juego_de_pruebas_2025/salida/Salida_EST_TREE_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba move 100000 TREE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" TREE "juego_de_pruebas_2025/pruebasmias/move_100000.txt" "juego_de_pruebas_2025/salida/Salida_EST_TREE_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause

echo Ejecutando el programa con prueba move 1000000 TREE

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2025/lib/TAD_modified.jar" "%MAIN%" TREE "juego_de_pruebas_2025/pruebasmias/move_1000000.txt" "juego_de_pruebas_2025/salida/Salida_EST_TREE_1.tsv"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo.
echo.
pause