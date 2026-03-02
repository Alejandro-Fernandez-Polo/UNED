-- Mux 4:1 de 8 bits
-- fichero: mux_4x1_8bits_archCondicional.vhd
library IEEE;
use IEEE.std_logic_1169.all;

entity mux4 is
  port ( x          : out std_logic_vector(7 downto 0);
         a, b, c, d : in  std_logic_vector(7 downto 0);
         s          : in  std_logic_vector(1 downto 0) );
end entity mux4;

architecture arch_cond of mux4 is
begin
  x <= a when (s="00") else
       b when (s="01") else
       c when (s="10") else
       d;
end architecture arch_cond;
