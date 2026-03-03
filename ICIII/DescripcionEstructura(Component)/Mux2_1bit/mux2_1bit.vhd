-- MUX 2:1 de 1 bit. mux2_1bit.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity Mux2_1bit is
  port ( d : out std_logic;
         i0, i1 : in  std_logic;
         s0 : in  std_logic );
end entity Mux2_1bit;

architecture Mux2_1bit of Mux2_1bit is

  component not1 is
    port ( y0 : out std_logic;
           x0 : in  std_logic );
  end component not1;

  component or2 is
    port ( y0 : out std_logic;
           x0, x1 : in  std_logic );
  end component or2;

  component and2 is
    port ( y0 : out std_logic;
           x0, x1 : in  std_logic );
  end component and2;

  signal n1, n2, n3 : std_logic;
begin
  Inv_1 : not1 port map ( x0 => s0, y0 => n1 );
  And2_1 : and2 port map ( x0 => i0, x1 => n1, y0 => n2 );
  And2_2 : and2 port map ( x0 => i1, x1 => s0, y0 => n3 );
  Or2_1 : or2 port map ( x0 => n2, x1 => n3, y0 => d );
end architecture Mux2_1bit;