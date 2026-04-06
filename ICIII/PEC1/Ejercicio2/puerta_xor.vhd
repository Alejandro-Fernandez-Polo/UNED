-- XOR de 2 entradas .puerta_xor.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity puerta_xor is
  port (x : out std_logic;
        y0, y1 : in std_logic);
end entity puerta_xor;

architecture puerta_xor of puerta_xor is 
begin
  x <= y0 xor y1;
end architecture puerta_xor;
