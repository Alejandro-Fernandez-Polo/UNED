-- Ejercicio 6 .ejercicio6.vhd
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity ejercicio6 is 
  port ( d0, d1, d2, d3 : out std_logic;
         i0, i1, en : in std_logic);
end entity ejercicio6;

architecture comp_funcLog of ejercicio6 is
begin
  process (i0, i1, en)
  begin
    if en = '0' then 
      d0 <= '0'; d1 <= '0'; d2 <= '0'; d3 <= '0';
    else
      case std_logic_vector'(i1 & i0) is 
        when "00" =>
          d0 <= '1'; d1 <= '0'; d2 <= '0'; d3 <= '0';
        when "01" =>
          d0 <= '0'; d1 <= '1'; d2 <= '0'; d3 <= '0';
        when "10" =>
          d0 <= '0'; d1 <= '0'; d2 <= '1'; d3 <= '0';
        when "11" =>
          d0 <= '0'; d1 <= '0'; d2 <= '0'; d3 <= '1';
        when others =>
          null; 
      end case;
    end if;
  end process;
end architecture comp_funcLog;