-- Decodificador binario 2 a 4. decodificador2x4_archProcessCase.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity decodificador4 is
  port ( x : out std_logic_vector(3 downto 0);
         s : in  std_logic_vector(1 downto 0) );
end entity decodificador4;

architecture arch_procCase of decodificador4 is
begin
  process (s)
  begin
    case s is
      when "00" =>
        x <= "0001";
      when "01" =>
        x <= "0010";
      when "10" =>
        x <= "0100";
      when others =>
        x <= "1000";
    end case;
  end process;
end architecture arch_procCase;