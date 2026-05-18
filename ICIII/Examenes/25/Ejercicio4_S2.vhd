library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;
architecture crtlAscensor of ctrlAscensor is
  type state is (Inicial, Cerrado, Subiendo, Bajando);
  signal estado : state;
  function codifica ( pulso : std_logic_vector(3 downto 0) ) return std_logic_vector is
  begin
    case pulso is
      when "0001" => return "00";
      when "0010" => return "01";
      when "0100" => return "10";
      when "1000" => return "11";
      when others => return "00";
    end case;
  end codifica;
begin
  process (clk, reset) is
    variable bot : std_logic_vector(1 downto 0);
  begin
    if (reset = '1') then
      estado <= Inicial;
    elsif rising_edge(clk) then
      case Estado is 
        when Inicial => 
          motor <= "00";
          puerta <= '1';
          bot := codifica(boton);
          if (boton /= "0000" and bot /= piso ) then
            estado <= Cerrado;
          end if;
        when Cerrado =>
          if (celula = '0') then
            if (bot>piso) then estado <= Subiendo;
            else estado <= Bajando;
            end if;
          end if;
        when Subiendo =>
          motor <= "10";
          puerta <= '0';
          if (bot=piso) then
            estado <= Inicial;
          end if;
        when Bajando =>
          motor <= "01";
          puerta <= '0';
          if (bot=piso) then 
            estado <= Inicial;
          end if;
      end case;
    end if;
  end process;
end architecture crtlAscensor; 