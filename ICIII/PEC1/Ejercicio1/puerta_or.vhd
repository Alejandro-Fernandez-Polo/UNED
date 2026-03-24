-- OR de 2 entradas .puerta_or.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity puerta_or is
  port (x : out std_logic;
        y0, y1 : in std_logic);
end entity puerta_or;

architecture puerta_or of puerta_or is 
begin
  x <= y0 or y1;
end architecture puerta_or;