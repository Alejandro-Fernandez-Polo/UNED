-- AND de 4 entradas .puerta_and_4in.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity puerta_and_4in is
  port (x : out std_logic;
        y0, y1, y2, y3 : in std_logic);
end entity puerta_and_4in;

architecture puerta_and_4in of puerta_and_4in is 
begin
  x <= y0 and y1 and y2 and y3;
end architecture puerta_and_4in;
