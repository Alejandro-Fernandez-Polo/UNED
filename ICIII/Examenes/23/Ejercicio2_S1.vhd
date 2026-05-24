library IEEE;
use IEEE.std_logic_1164.all;

architecture flipflopD of flipflopD is
begin
  process (clk)
  begin
    if (rising_edge(clk)) then
      q <= d;
    end if;
  end process;
end architecture flipflopD;

library IEEE;
use IEEE.std_logic_1164.all;

architecture mux2a1 of mux2a1 is
begin
  with sel select
    y <= x1 when '1',
         x0 when others;
end architecture mux2a1;


library IEEE;
use IEEE.std_logic_1164.all;

architecture Desplazador of Desplazador is
  signal u,i: std_logic_vector(3 downto 0);
  component flipflopD is
    port( q : out std_logic;
          d : in std_logic;
          clk : in std_logic );
  end component flipflopD;

  component mux2a1 is
    port( y : out std_logic;
          sel : in std_logic;
          x1, x0 : in std_logic );
  end component mux2a1;
begin
  q <= u;
  genConexion: for k in 3 downto 0 generate
    inicial: if k = 3 generate
      mux3: mux2a1 port map( i(3),load, d(3), u(0));
    end generate inicial;
    intermedio: if k/=3 generate
      muxk: mux2a1 port map (i(k), load, d(k), u(k+1));
    end generate intermedio;
      ffk: flipflopD port map(u(k), i(k), clk);
  end generate genConexion;

end architecture Desplazador;