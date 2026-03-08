-- Ejercicio 1 .ejercicio1.vhd
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity ejercicio1 is 
  port ( f : out std_logic;
         x, y, z : in std_logic);
end entity ejercicio1;

architecture comp_funcLog of ejercicio1 is
  signal or1, or2 : std_logic;
begin
  or1 <= x or y;
  or2 <= or1 or z;
  f <= not or2;
end architecture comp_funcLog;