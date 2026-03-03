-- Mux 4:1 de 8 bits. mux_4x1_8bits_archProcessCase.vhd
library IEEE;
use IEEE.std_logic_1169.all;

entity mux4 is
  port ( x          : out std_logic_vector(7 downto 0);
         a, b, c, d : in  std_logic_vector(7 downto 0);
         s          : in  std_logic_vector(1 downto 0) );
end entity mux4;

architecture arch_procCase of mux4 is
begin
  process (a, b, c, d, s)
  begin
    case s is
      when "00" =>
        x <= a;
      when "01" =>
        x <= b;
      when "10" =>
        x <= c;
      when others =>
        x <= d;
    end case;
  end process;
end architecture arch_procCase;
