library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity ejercicio1 is 
  port ( F : out std_logic;
         x, y, z : in std_logic);
end entity ejercicio1;

architecture implementacion_1 of ejercicio1 is
begin
  F <= (x or (not y)) and (not (y and z)) and ((x and (not y)) or ((not x) and y));
end architecture implementacion_1;
