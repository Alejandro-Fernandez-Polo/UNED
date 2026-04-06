  -- OR de 3 entradas .puerta_or_3in.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity puerta_or_3in is
  port (x : out std_logic;
        y0, y1, y2 : in std_logic);
end entity puerta_or_3in;

architecture puerta_or_3in of puerta_or_3in is 
begin
  x <= y0 or y1 or y2;
end architecture puerta_or_3in;
