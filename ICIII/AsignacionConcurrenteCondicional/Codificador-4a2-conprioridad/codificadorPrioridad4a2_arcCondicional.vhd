-- Codificador de prioridad 4 a 2
-- fichero: codificadorPrioridad4a2_arcCondicional.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity codificadorPrioridad4a2 is
  port ( codigo : out std_logic_vector(1 downto 0);
         activo : out std_logic;
         x      : in  std_logic_vector(3 downto 0) );
end entity codificadorPrioridad4a2;

architecture codPrioridad4a2 of codificadorPrioridad4a2 is
begin
  codigo <= "11" when ( x(3) = '1' ) else
            "10" when ( x(2) = '1' ) else
            "01" when ( x(1) = '1' ) else
            "00";
  activo <= x(3) or x(2) or x(1) or x(0);
end architecture codPrioridad4a2;