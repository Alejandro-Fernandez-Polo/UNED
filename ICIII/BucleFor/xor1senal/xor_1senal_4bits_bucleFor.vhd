-- XOR bit a bit de una señal de 4 bits. xor_1senal_4bits_bucleFor.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity xor_1senal_4bits is
  port ( y : out std_logic_vector(3 downto 0);
         a : in  std_logic_vector(3 downto 0) );
end entity xor_1senal_4bits;

architecture xor_1senal_4bits_bucleFor of xor_1senal_4bits is
  constant WIDTH : integer := 4;
  signal tmp : std_logic_vector(WIDTH-1 downto 0);
begin
  process (a, tmp)
  begin
    tmp(0) <= a(0);
    for i in 1 to WIDTH-1 loop
      tmp(i) <= tmp(i-1) xor a(i);
    end loop;
  end process;
  y <= tmp(WIDTH-1);
end architecture xor_1senal_4bits_bucleFor;