-- Banco de pruebas del ejercicio1 .bp_ejercicio1.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity bp_ejercicio1 is
end entity bp_ejercicio1;

architecture bp_ejercicio1 of bp_ejercicio1 is
	signal f     : std_logic;    -- Conectar salida UUT
	signal x, y, z : std_logic;    -- Conectar entradas UUT

	component ejercicio1 is
		port ( f    : out std_logic;
		       x, y, z : in  std_logic  );
	end component ejercicio1;

begin
	-- Instanciar y conectar UUT
	uut : component ejercicio1 port map
		( f => f, x => x, y => y, z => z );

	gen_vec_test : process
	begin
		x <= '0'; y <= '0'; z <= '0';
		wait for 10 ns;
		x <= '0'; y <= '0'; z <= '1';
		wait for 10 ns;
		x <= '0'; y <= '1'; z <= '0';
		wait for 10 ns;
		x <= '0'; y <= '1'; z <= '1';
		wait for 10 ns;
    x <= '1'; y <= '0'; z <= '0';
		wait for 10 ns;
    x <= '1'; y <= '0'; z <= '1';
		wait for 10 ns;
    x <= '1'; y <= '1'; z <= '0';
		wait for 10 ns;
    x <= '1'; y <= '1'; z <= '1';
		wait for 10 ns;
		report "Final de la simulaci�n";
		wait;
	end process gen_vec_test;

end architecture bp_ejercicio1;