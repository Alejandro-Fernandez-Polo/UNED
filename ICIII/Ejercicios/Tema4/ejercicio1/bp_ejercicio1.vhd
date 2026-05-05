library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity bp_ejercicio1 is 
  constant DELAY : time := 10 ns; 
end entity bp_ejercicio1;

architecture bp_ejercicio1 of bp_ejercicio1 is
  signal valida : std_logic; -- Salida
  signal codificada : std_logic_vector(1 downto 0);
  signal i3, i2, i1, i0 : std_logic;

  component ejercicio1 is
    port ( valida : out std_logic;
           codificada : out std_logic_vector(1 downto 0);
           i3, i2, i1, i0 : in std_logic);
  end component ejercicio1;

begin
  uut : component ejercicio1 port map
    (valida, codificada, i3, i2, i1, i0);

  vec_test : process
    variable temp : unsigned(3 downto 0);
  begin
    report "Iniciando simulación";
    for i in 0 to 15 loop
      temp := to_unsigned(i,4);
      i0 <= std_logic(temp(0));
      i1 <= std_logic(temp(1));
      i2 <= std_logic(temp(2));
      i3 <= std_logic(temp(3));
      wait for DELAY;
    end loop;
    report "Final de la simulación";
    wait;
  end process vec_test;

end architecture bp_ejercicio1;