-- Codificador de prioridad 4 a 2
-- fichero: codificadorPrioridad4a2_archProcIf.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity codificadorPrioridad4a2 is
  port ( codigo : out std_logic_vector(1 downto 0);
         activo : out std_logic;
         x      : in  std_logic_vector(3 downto 0) );
end entity codificadorPrioridad4a2;

architecture codPrior4a2_procIf of codificadorPrioridad4a2 is
begin
  process (x)
  begin
    if (x(3) = '1') then
      codigo <= "11";
    elsif (x(2) = '1') then
      codigo <= "10";
    elsif (x(1) = '1') then
      codigo <= "01";
    else
      codigo <= "00";
    end if;
    activo <= x(3) or x(2) or x(1) or x(0);
  end process;
end architecture codPrior4a2_procIf;