library IEEE;
use IEEE.std_logic_1164.all;

entity bp_regDesp is
end entity bp_regDesp;

architecture bp_regDesp of bp_regDesp is
  constant PERIODO : time := 200ns;
  signal Serial_out : std_logic;
  signal clk : std_logic := '0';
  signal Serial_in : std_logic;

  component regDesp is
    port ( Serial_out : out std_logic;
           clk : in std_logic;
           Serial_in : in std_logic);
  end component regDesp;
begin
  uut : component regDesp port map (Serial_out, clk, Serial_in);

  clk <= not clk after (PERIODO/2);

  gen_vec_test : process is
  begin
    report "Comienza la solución";
    Serial_in<='0'; wait until falling_edge(clk);
    Serial_in<='1'; wait until falling_edge(clk);
    Serial_in<='1'; wait until falling_edge(clk);
    Serial_in<='0'; wait until falling_edge(clk);
    report "Finaliza la simulación";
    wait;
  end process gen_vec_test;

end architecture bp_regDesp;