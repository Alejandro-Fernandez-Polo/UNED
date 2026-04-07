-- Banco de pruebas del ejercicio1.bp_ejercicio1.vhd
LIBRARY IEEE;
USE IEEE.std_logic_1164.ALL;
USE IEEE.numeric_std.ALL;

ENTITY bp_ejercicio2 IS
	CONSTANT DELAY : TIME := 10 ns; -- Retardo entre cambios de entrada
END ENTITY bp_ejercicio2;

ARCHITECTURE bp_ejercicio2 OF bp_ejercicio2 IS
	SIGNAL d : STD_LOGIC_VECTOR(2 DOWNTO 0); -- Salidas UUT
	SIGNAL x, y : STD_LOGIC_VECTOR(1 DOWNTO 0); -- Entradas UUT

	COMPONENT ejercicio2 IS
		PORT (
			d : OUT STD_LOGIC_VECTOR(2 DOWNTO 0);
			x, y : IN STD_LOGIC_VECTOR(1 DOWNTO 0));
	END COMPONENT ejercicio2;

BEGIN
	-- Instanciar y conectar UUT
	uut : COMPONENT ejercicio2 PORT MAP
		(d, x, y);

	vec_test : PROCESS
    VARIABLE temp : unsigned(3 DOWNTO 0);
    VARIABLE errores : integer := 0; -- Contador
    VARIABLE expected_val : integer;
  BEGIN
    REPORT "Iniciando simulacion";
    
    -- Generar todas las combinaciones de entradas
    FOR i IN 0 TO 15 LOOP
      temp := to_unsigned(i, 4); 
      
      x(0) <= STD_LOGIC(temp(0));
      x(1) <= STD_LOGIC(temp(1));
      y(0) <= STD_LOGIC(temp(2));
      y(1) <= STD_LOGIC(temp(3));
     
      WAIT FOR DELAY;
      
      -- Calculo valor esperado
      expected_val := to_integer(temp(1 DOWNTO 0)) - to_integer(temp(3 DOWNTO 2));
      
      IF signed(d) /= to_signed(expected_val, 3) THEN
          errores := errores + 1;
      END IF;
      
    END LOOP;
    
    REPORT "Final de la simulacion. Numero total de errores producidos: " & integer'image(errores) SEVERITY NOTE;
    WAIT;
  END PROCESS vec_test;

END ARCHITECTURE bp_ejercicio2;