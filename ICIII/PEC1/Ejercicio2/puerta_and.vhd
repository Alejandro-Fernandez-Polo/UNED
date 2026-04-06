-- AND de 2 entradas .puerta_and.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity puerta_and is
  port (x : out std_logic;
        y0, y1 : in std_logic);
end entity puerta_and;

architecture puerta_and of puerta_and is 
begin
  x <= y0 and y1;
end architecture puerta_and;
