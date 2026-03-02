-- Mux 4:1 de 8 bits. mux_4x1_8bits_archSelect.vhd
library IEEE;
use IEEE.std_logic_1169.all;

entity mux4 is
  port ( x          : out std_logic_vector(7 downto 0);
         a, b, c, d : in  std_logic_vector(7 downto 0);
         s          : in  std_logic_vector(1 downto 0) );
end entity mux4;

architecture arch_select of mux4 is
begin
  with s select
    x <= a when "00",
         b when "01",
         c when "10",
         d when others;
end architecture arch_select;
