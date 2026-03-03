-- Mux 4:1 de 8 bits. mux_4x1_8bits_archProcessIf.vhd
library IEEE;
use IEEE.std_logic_1169.all;

entity mux4 is
  port ( x          : out std_logic_vector(7 downto 0);
         a, b, c, d : in  std_logic_vector(7 downto 0);
         s          : in  std_logic_vector(1 downto 0) );
end entity mux4;

architecture arch_procIf of mux4 is
begin
  process (a, b, c, d, s)
  begin
    if (s = "00") then
      x <= a;
    elsif (s = "01") then
      x <= b;
    elsif (s = "10") then
      x <= c;
    else
      x <= d;
    end if;
  end process;
end architecture arch_procIf;
