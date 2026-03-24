library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity ejercicio1_a is 
  port ( F : out std_logic;
         x, y, z : in std_logic);
end entity ejercicio1_a;

architecture comp_funcLog of ejercicio1_a is
  signal and1, and2, and3, or1, or2 : std_logic;
begin
  or1 <= x or (not y) after 1 ns;
  and1 <= x and (not y) after 1 ns;
  and2 <= (not x) and y after 1 ns;
  or2 <= and1 or and2;
  and3 <= not (y and z);
  F <= or1 and or2 and and3;
end architecture comp_funcLog;
