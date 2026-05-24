library IEEE;
use IEEE.std_logic_1164.all;

architecture control of control is
  type estado is (A, B);
  signal state : estado;
  signal sal : std_logic;
  signal count : integer range 0 to 8:= 0;
begin
  process (state)
  begin
    case state is
      when A => sal <= '0';
      when B => sal <= '1';
    end case;
  end process;
  process (clk)
  begin
    if rising_edge(clk) then
      if (rst = '0') then 
        state <= A;
        count <= 0;
      else
        if (state = A) then
          if (count = 7) then
            state <= B;
            count <= 0;
          else
            count <= count + 1;
          end if;
        elsif (state = B) then
          if (count = 3) then
            state <= A;
            count <= 0;
          else
            count <= count + 1;
          end if;
        end if;
      end if;
    end if;
  end process;
  salida <= sal;
end architecture control;