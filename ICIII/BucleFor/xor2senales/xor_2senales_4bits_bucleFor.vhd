-- XOR bit a bit de 2 señales de 4 bits. xor_2senales_4bits_bucleFor.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity xor_2senales_4bits is
  port ( y : out std_logic_vector(3 downto 0);
         a, b : in  std_logic_vector(3 downto 0) );
end entity xor_2senales_4bits;

architecture xor_2senales_4bits_bucleFor of xor_2senales_4bits is
  constant WIDTH : integer := 4;
begin
  process (a, b)
  begin
    -- se podria hacer solo con y <= a xor b; pero se hace con un bucle for para mostrar su uso
    for i in 0 to WIDTH-1 loop
      y(i) <= a(i) xor b(i);
    end loop;
  end process;
end architecture xor_2senales_4bits_bucleFor;