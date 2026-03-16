-- Banco de pruebas del ejercicio5 .bp_ejercicio5.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity bp_ejercicio5 is
end entity bp_ejercicio5;

architecture bp_ejercicio5 of bp_ejercicio5 is
	signal y     : std_logic;    -- Conectar salida UUT
	signal x : std_logic;    -- Conectar entradas UUT

	component ejercicio5 is
		port ( y    : out std_logic;
		       x : in  std_logic  );
	end component ejercicio5;

begin
	-- Instanciar y conectar UUT
	uut : component ejercicio5 port map
		( y => y, x => x );

	gen_vec_test : process
	begin
		x <= '0',
    '1' after 5 ns,
    '0' after 20 ns,
    '1' after 30 ns,
    '0' after 35 ns;
		report "Final de la simulacion";
		wait;
	end process gen_vec_test;

end architecture bp_ejercicio5;
