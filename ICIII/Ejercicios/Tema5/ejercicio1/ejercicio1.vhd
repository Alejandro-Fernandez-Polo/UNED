library IEEE;
use IEEE.std_logic_1164.all;

entity regDesp is 
  port ( Serial_out : out std_logic;
         Serial_in, clk : in std_logic);
end entity regDesp;

architecture regDesp of regDesp is
begin
  process(clk)
    variable R0, R1, R2: std_logic;
  begin
    if (rising_edge(clk)) then 
      Serial_out <= R2;
      R2 := R1;
      R1 := R0;
      R0 := Serial_in;
    end if;
  end process;
end architecture regDesp;