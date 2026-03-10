-- Ejercicio 2 .ejercicio2.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity ejercicio2 is
  port (
    x_in, y_in, z_in : in std_logic;
    f_out : out std_logic
  );
end entity ejercicio2;

architecture comp_funcLog of ejercicio2 is

  component puerta_or is 
    port (x : out std_logic;
          y0, y1 : in std_logic);
  end component puerta_or;

  component puerta_not is
    port (x : out std_logic;
          y : in std_logic);
  end component puerta_not;

  signal or1, or2 : std_logic;
begin
  unit1 : puerta_or port map ( y0 => x_in, y1 => y_in, x => or1 );
  unit2 : puerta_or port map ( y0 => or1, y1 => z_in, x => or2 );
  unit3 : puerta_not port map ( y => or2, x => f_out );
end architecture comp_funcLog;
