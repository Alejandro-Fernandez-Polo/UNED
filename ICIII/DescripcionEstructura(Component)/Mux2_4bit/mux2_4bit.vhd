-- MUX 2:1 de 1 bit. mux2_4bit.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity Mux2_4bit is
  port ( d0, d1, d2, d3 : out std_logic;
         a0, a1, a2, a3 : in  std_logic;
         b0, b1, b2, b3 : in  std_logic;
         s0 : in  std_logic );
end entity Mux2_4bit;

architecture Mux2_4bit of Mux2_4bit is

  component Mux2_1bit is
    port ( d : out std_logic;
           i0, i1 : in  std_logic;
           s0 : in  std_logic );
  end component Mux2_1bit;

begin
  Mux2_1bit_0 : Mux2_1bit port map ( i0 => a0, i1 => b0, s0 => s0, d => d0 );
  Mux2_1bit_1 : Mux2_1bit port map ( i0 => a1, i1 => b1, s0 => s0, d => d1 );
  Mux2_1bit_2 : Mux2_1bit port map ( i0 => a2, i1 => b2, s0 => s0, d => d2 );
  Mux2_1bit_3 : Mux2_1bit port map ( i0 => a3, i1 => b3, s0 => s0, d => d3 );
end architecture Mux2_4bit;