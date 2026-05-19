library IEEE;
use IEEE.std_logic_1164.all;

architecture gcc_2 of gcc_2 is

  signal count_reg : std_logic_vector(1 downto 0);
  signal last_count : std_logic_vector(1 downto 0);

begin
  process (reset_n, clk) is
  begin
    if (reset_n = '0') then
      count_reg      <= "00";
      last_count_reg <= "00"
    if (count_reg = "00") then 
        count_reg <= "01";
      elsif (count_reg = "10") then
        count_reg <= "11";
      elsif (count_reg = "01" ) then
        if (last_count_reg = "00") then
          count_reg <= "11";
        else
          count_reg <= "00";
        end if;
      elsif (count_reg = "11") then 
        if (last_count_reg = "01") then
          count_reg <= "10";
        else
          count_reg <= "01";
        end if;
      end if;
    end if;
  end process;

  count <= count_reg;
end architecture gcc_2;