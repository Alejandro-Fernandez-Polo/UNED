library IEEE;
use IEEE.std_logic_1164.all;

entity bp_desplazador is
  constant PERIODO : time := 10 ns;
end entity bp_desplazador;

architecture bp_desplazador of bp_desplazador is
  signal q : std_logic_vector (3 downto 0);
  signal d : std_logic_vector (3 downto 0);
  signal clk : std_logic := '0';
  signal load : std_logic ;

  component Desplazador is
    port( q : out std_logic_vector (3 downto 0);
          d : in std_logic_vector (3 downto 0);
          clk : in std_logic;
          load : in std_logic );
  end component Desplazador;
begin 
  uut : component Desplazador port map ( q, d, clk, load);
  clk <= not clk after PERIODO/2;
  main : process
  begin
    d <= "0001";
    load <= '1';
    wait for (PERIODO);
    load <= '0';
    for 0 to 7 loop
      wait for PERIODO;
    end loop;
    d <= "0000";
    load <= '1';
    wait for PERIODO;
    report "Test finalizado";
    wait;
  end process main;
end architecture bp_desplazador;