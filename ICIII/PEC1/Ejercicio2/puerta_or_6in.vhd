-- OR de 6 entradas .puerta_or_6in.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity puerta_or_6in is
  port (x : out std_logic;
        y0, y1, y2, y3, y4, y5 : in std_logic);
end entity puerta_or_6in;

architecture puerta_or_6in of puerta_or_6in is 
begin
  x <= y0 or y1 or y2 or y3 or y4 or y5;
end architecture puerta_or_6in;
