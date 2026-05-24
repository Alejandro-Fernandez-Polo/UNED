library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

architecture counter of counter is
  signal dig1_s, dig2_s : unsigned(3 downto 0);
begin
  process (clk, reset)
  begin
    if (reset = '1') then
      dig2_s <= "0000";
      dig1_s <= "0000";
    elsif (rising_edge(clk)) then 
      if (en = '1') then
        if (dig1_s = "1001" and dig2_s = "1001") then
          dig2_s <= "0000";
          dig1_s <= "0000";
        else
          if (dig1_s = "1001") then
            dig2_s <= dig2_s + 1;
            dig1_s <= "0000";
          else
            dig1_s <= dig1_s + 1;
          end if;
        end if;
      end if;
    end if;
  end process;
  digit1 <= std_logic_vector(dig1_s);
  digit2 <= std_logic_vector(dig2_s);
end architecture counter;