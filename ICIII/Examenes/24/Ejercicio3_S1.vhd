library IEEE;
use IEEE.std_logic_1164.all;

entity bp_gcc_2 is
  constant PERIODO : time := 100 ns;
end entity bp_gcc_2;

architecture bp_gcc_2 of bp_gcc_2 is
  signal count : std_logic_vector(1 downto 0);
  signal reset_n : std_logic := '1';
  signal clk : std_logic := '0';

  component gcc_2 is
    port ( count : out std_logic_vector(1 downto 0);
           reset_n, clk : in std_logic);
  end component gcc_2;
  
begin
  uut : component gcc_2 port map (count, reset_n, clk);
  
  main : process is
  begin
    wait for 25 ns;
    reset_n <= '0';
    wait for 100 ns;
    reset_n <= '1';
    for i in 1 to 6 loop
      wait for (PERIODO/2);
      clk <= '1';     -- Flanco de subida
      wait for (PERIODO/2);
      clk <= '0';     -- Flanco de bajada
    end loop;
    report "Simulacion finalizada";
    wait;
  end process main;
end architecture bp_gcc_2;