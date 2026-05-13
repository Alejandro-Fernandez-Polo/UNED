ENTITY divisor_frecuencia_6 IS PORT (
  clk6  : OUT STD_LOGIC;
  clk, resetn : IN STD_LOGIC);
END ENTITY divisor_frecuencia_6;

Architecture divisor_frecuencia_6 of divisor_frecuencia_6 is
  signal contador : integer range 0 to 2;
  signal clk6_int : std_logic := '0';
begin
  process (clk, resetn)
    begin
        if (resetn = '0') then
            contador <= 0;
            clk6_int <= '0';
            
        elsif rising_edge(clk) then
            if contador = 2 then
                contador <= 0;               -- Reiniciamos el contador
                clk6_int <= not clk6_int;    -- Invertimos la salida (bascula)
            else
                contador <= contador + 1;    -- Incrementamos en 1
            end if;
        end if;
    end process;
    clk6 <= clk6_int;
end architecture divisor_frecuencia_6;