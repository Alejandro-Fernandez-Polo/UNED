-- NOT de 1 entrada .puerta_not.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity puerta_not is
  port (x : out std_logic;
        y : in std_logic);
end entity puerta_not;

architecture puerta_not of puerta_not is 
begin
  x <= not y;
end architecture puerta_not;
