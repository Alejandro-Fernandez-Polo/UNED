entity bp_contador is
end entity;

architecture bp_contador of bp_contador is
  constant PERIODO : time := 100ns;
  signal q : std_logic_vector(3 downto 0);
  signal clk : std_logic := '0';
  -- Inicializamos las señales de control para evitar estados 'U'
  signal reset : std_logic := '0';
  signal sync_clr, en, load : std_logic := '0';
  signal d : std_logic_vector(3 downto 0) := "0000";

  component contador IS
    port ( q : out std_logic_vector(3 downto 0);
      clk, reset : in std_logic;
      sync_clr, en, load : in std_logic;
      d : in std_logic_vector(3 downto 0) );
  end component contador;
begin
  uut : component contador port map (q, clk, reset, sync_clr, en, load, d);

  clk <= not clk after(PERIODO/2);

  vect_test : process is
  begin
    reset <= '1';
    wait for 125 ns;
    reset <= '0';
    d <= "0010";
    load <= '1';
    wait until rising_edge(clk);
    load <= '0';
    en <= '1';
    wait until rising_edge(clk);
    wait until rising_edge(clk);
    en <= '0';
    wait;
  end process vect_test;
end architecture bp_contador;