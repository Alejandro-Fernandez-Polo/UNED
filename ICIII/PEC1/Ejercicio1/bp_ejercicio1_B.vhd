-- Banco de pruebas del ejercicio1_ab.bp_ejercicio1_B.vhd
library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity bp_ejercicio1_B is
  constant DELAY : time := 10 ns;  -- Retardo entre cambios de entrada
end entity bp_ejercicio1_B;

architecture bp_ejercicio1_B of bp_ejercicio1_B is
	signal F : std_logic;  -- Salidas UUT
	signal x, y ,z    : std_logic;  -- Entradas UUT

	component ejercicio1_b is
		port ( F : out std_logic;
		       x, y, z : in  std_logic  );
	end component ejercicio1_b;

begin
	-- Instanciar y conectar UUT
	uut : component ejercicio1_b port map
		( F, x, y, z);

	vec_test : process
    variable temp : unsigned(2 downto 0);
	begin
    report "Iniciando simulacion";
    -- Generar todas las combinaciones de entradas
    for i in 0 to 7 loop
      temp := to_unsigned(i, 3);
      x <= std_logic(temp(0));
      y <= std_logic(temp(1));
      z <= std_logic(temp(2));
      wait for DELAY;
    end loop;
		report "Final de la simulaci�n";
		wait;
	end process vec_test;

end architecture bp_ejercicio1_B;
