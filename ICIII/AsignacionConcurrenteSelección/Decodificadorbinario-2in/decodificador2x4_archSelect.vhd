-- Decodificador binario 2 a 4. decodificador2x4_archSelec.vhl
library IEEE;
use IEEE.std_logic_1164.all;

entity decodificador4 is
  port ( x : out std_logic_vector(3 downto 0);
         s : in  std_logic_vector(1 downto 0) );
end entity decodificador4;

architecture arch_decSelec of decodificador4 is
begin
  with s select
    x <= "0001" when "00",
         "0010" when "01",
         "0100" when "10",
         "1000" when others;
end architecture arch_decSelec;