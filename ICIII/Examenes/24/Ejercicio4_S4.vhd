library IEEE;

use IEEE.std_logic_1164.all;

architecture registro8 of registro8 is
  signal registro : std_logic_vector(7 downto 0) := "00000000";
begin
  process (clk)
  begin
    if (rising_edge(clk)) then
      registro(7 downto 1) <= registro(6 downto 0);
      registro(0) <= Serial_in;
    end if;
  end process;
  Serial_out <= registro(7);
end architecture registro8;