-- AND de 3 entradas .puerta_and_3in.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity puerta_and_3in is
  port (x : out std_logic;
        y0, y1, y2 : in std_logic);
end entity puerta_and_3in;

architecture puerta_and_3in of puerta_and_3in is 
begin
  x <= y0 and y1 and y2;
end architecture puerta_and_3in;
