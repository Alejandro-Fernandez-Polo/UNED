-- Decodificador binario 2 a 4
-- fichero: decodificador2x4_archCondicional.vhl
library IEEE;
use IEEE.std_logic_1164.all;

entity decodificador4 is
  port ( x : out std_logic_vector(3 downto 0);
         s : in  std_logic_vector(1 downto 0) );
end entity decodificador4;

architecture arch_decodificador4 of decodificador4 is
begin
  x <= "0001" when (s="00") else
       "0010" when (s="01") else
       "0100" when (s="10") else
       "1000";
end architecture arch_decodificador4;