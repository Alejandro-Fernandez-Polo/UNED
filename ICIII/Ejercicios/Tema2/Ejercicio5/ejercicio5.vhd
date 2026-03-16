-- Ejercicio 5 .ejercicio5.vhd
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity ejercicio5 is 
  port ( y : out std_logic;
         x : in std_logic);
end entity ejercicio5;

architecture comp_funcLog of ejercicio5 is
  signal not1, not2, not3 : std_logic;
begin
  not1 <= not x after 1 ns;
  not2 <= not not1 after 1 ns;
  not3 <= not not2 after 1 ns;
  y <= x and not3;
end architecture comp_funcLog;
