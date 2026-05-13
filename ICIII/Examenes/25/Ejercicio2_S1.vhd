ENTITY contador IS
  PORT (
    q : OUT STD_LOGIC_VECTOR(3 DOWNTO 0);
    clk, reset : IN STD_LOGIC;
    sync_clr, en, load : IN STD_LOGIC;
    d : IN STD_LOGIC_VECTOR(3 DOWNTO 0));
END ENTITY contador;

ARCHITECTURE contador OF contador IS
  SIGNAL q_int : unsigned(3 DOWNTO 0);
BEGIN
  PROCESS (reset, clk) IS
  BEGIN
    IF (reset = '1') THEN
      q_int <= "0000";
    ELSIF rising_edge(clk) THEN
      
      IF (sync_clr = '1') THEN
        q_int <= "0000"; -- Reseteo síncrono
      
      ELSIF (load = '1') THEN
        q_int <= unsigned(d); -- Carga síncrona de la entrada d
      
      ELSIF (en = '1') THEN
        q_int <= q_int + 1; -- Incrementa la cuenta
      
      END IF;
    END IF;

  END PROCESS;
  q <= std_logic_vector(q_int);
END ARCHITECTURE contador;