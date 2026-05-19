library IEEE;
use IEEE.std_logic_1164.all;

architecture regulador of regulador is
  type state is ( YY, RY, GR, YR, RG);
  signal estado : state;
  signal contador : integer range 0 to 2700;
begin
  process (estado) is
  begin
    r1 <= '0';
    y1 <= '0';
    g1 <= '0';
    r2 <= '0';
    y2 <= '0';
    g2 <= '0';
    case estado is
      when YY =>
        y1 <= '1';
        y2 <= '1';
      when RY =>
        r1 <= '1';
        y2 <= '1';
      when GR =>
        g1 <= '1';
        r2 <= '1';
      when YR =>
        y1 <= '1';
        r2 <= '1';
      when RG =>
        r1 <= '1';
        g2 <= '1';
    end case;
  end process;

  process (stdby, clk) is
  begin
    if (stdby = '1') then
      estado <= YY;
      contador <= 0;
    elsif (rising_edge(clk)) then 
      case estado is
        when YY =>
          -- Si stdby es '0', al llegar el flanco pasamos a RY [cite: 69]
          estado <= RY;
          contador <= 0;
        when RY =>
          -- 5 segundos = 300 ciclos. Contamos de 0 a 299.
          if contador = 299 then
            estado <= GR;
            contador <= 0;
          else
            contador <= contador + 1;
          end if;
        when GR =>
          -- 45 segundos = 2700 ciclos. Contamos de 0 a 2699.
          if contador = 2699 then
            estado <= YR;
            contador <= 0;
          else
            contador <= contador + 1;
          end if;
        when YR =>
          -- 5 segundos = 300 ciclos. Contamos de 0 a 299.
          if contador = 299 then
            estado <= RG;
            contador <= 0;
          else
            contador <= contador + 1;
          end if;
        when RG =>
          -- 30 segundos = 1800 ciclos. Contamos de 0 a 1799.
          if contador = 1799 then
            estado <= RY;
            contador <= 0;
          else
            contador <= contador + 1;
          end if;
      end case;
    end if;
  end process;
end architecture regulador;