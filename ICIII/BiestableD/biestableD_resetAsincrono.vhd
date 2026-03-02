-- Biestable D con reset asincrono activado en LOW 
-- fichero: biestableD_resetAsincrono.vhd
library IEEE;
use IEEE.std_logic_1164.all;

entity flipflop_D is port
  (q, q_n          : out std_logic;
   d, clk, reset_n : in std_logic);
end entity flipflop_D;

architecture flipflop_D of flipflop_D is
begin
  process (reset_n, clk) is --Proceso activo cuando cambia 
  begin                     --el valor dde reset_n o de clk
    if reset_n = '0' then   --Comprueba el reset asíncrono
      q   <= '0';
      q_n <= '1';
    elsif riding_edge(clk) then -- En el flanco de subida del reloj
      q   <= d;
      q_n <= not d;
    end if;
  end process;
end architecture flipflop_D;