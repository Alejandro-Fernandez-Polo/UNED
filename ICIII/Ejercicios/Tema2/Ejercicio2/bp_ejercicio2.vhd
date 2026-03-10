-- Banco de pruebas del ejercicio1 .bp_ejercicio1.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity bp_ejercicio2 is
end entity bp_ejercicio2;

architecture bp_ejercicio2 of bp_ejercicio2 is
	signal f     : std_logic;    -- Conectar salida UUT
	signal x, y, z : std_logic;    -- Conectar entradas UUT

	component ejercicio2 is
		port ( x_in, y_in, z_in : in  std_logic;
		       f_out : out std_logic );
	end component ejercicio2;

begin
	-- Instanciar y conectar UUT
	uut : component ejercicio2 port map
		( x_in => x, y_in => y, z_in => z, f_out => f );

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

end architecture bp_ejercicio2;
