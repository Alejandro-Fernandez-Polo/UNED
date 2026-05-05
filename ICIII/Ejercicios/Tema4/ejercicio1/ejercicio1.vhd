library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity ejercicio1 is
  port ( valida : out std_logic;
         codificada : out std_logic_vector(1 downto 0);
         i3, i2, i1, i0 : in std_logic);
end entity ejercicio1;

architecture comp_funcLog of ejercicio1 is
begin
  process(i3, i2, i1, i0) is
  begin
    valida <= i3 or i2 or i1 or i0;

    if (i3 = '1') then
      codificada <= "11";
    elsif (i2 = '1') then
      codificada <= "10";
    elsif (i1 = '1') then
      codificada <= "01";
    else
      codificada <= "00";
    end if;
  end process;
end architecture comp_funcLog;