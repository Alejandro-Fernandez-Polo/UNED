-- Codificador de prioridad 4 a 2
-- fichero: codificadorPrioridad4a2_arcCondicional.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity codificadorPrioridad4a2 is
  port ( codigo : out std_logic_vector(1 downto 0);
         activo : out std_logic;
         x      : in  std_logic_vector(3 downto 0) );
end entity codificadorPrioridad4a2;

architecture codPrior4a2_selec of codificadorPrioridad4a2 is
begin
  with x select
    codigo <= "11" when "1000" | "1001" | "1010" | "1011" | "1100" | "1101" | "1110" | "1111",
              "10" when "0100" | "0101" | "0110" | "0111",
              "01" when "0010" | "0011",
              "00" when others;
  activo <= x(3) or x(2) or x(1) or x(0);
end architecture codPrior4a2_selec;