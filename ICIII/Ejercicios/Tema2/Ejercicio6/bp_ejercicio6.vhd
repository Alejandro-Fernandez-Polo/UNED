  -- Banco de pruebas del ejercicio6 .bp_ejercicio6.vhd
library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity bp_ejercicio6 is
  constant DELAY : time := 10 ns;  -- Retardo entre cambios de entrada
end entity bp_ejercicio6;

architecture bp_ejercicio6 of bp_ejercicio6 is
	signal d0, d1, d2, d3 : std_logic;  -- Salidas UUT
	signal i0, i1, en     : std_logic;  -- Entradas UUT

	component ejercicio6 is
		port ( d0, d1, d2, d3 : out std_logic;
		       i0, i1, en : in  std_logic  );
	end component ejercicio6;

begin
	-- Instanciar y conectar UUT
	uut : component ejercicio6 port map
		( d0, d1, d2, d3, i0, i1, en);

	vec_test : process
    variable temp : unsigned(2 downto 0);
	begin
    report "Iniciando simulacion";
    -- Generar todas las combinaciones de entradas
    for i in 0 to 7 loop
      temp := to_unsigned(i, 3);
      i0 <= std_logic(temp(0));
      i1 <= std_logic(temp(1));
      en <= std_logic(temp(2));
      wait for DELAY;
    end loop;
		report "Final de la simulaci�n";
		wait;
	end process vec_test;

end architecture bp_ejercicio6;
