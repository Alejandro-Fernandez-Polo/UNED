-- Decodificador binario 2 a 4. decodificador2x4_archProcessIf.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity decodificador4 is
  port ( x : out std_logic_vector(3 downto 0);
         s : in  std_logic_vector(1 downto 0) );
end entity decodificador4;

architecture arch_procIf of decodificador4 is
begin
  process (s)
  begin
    if (s = "00") then
      x <= "0001";
    elsif (s = "01") then
      x <= "0010";
    elsif (s = "10") then
      x <= "0100";
    else
      x <= "1000";
    end if;
  end process;
end architecture arch_procIf;