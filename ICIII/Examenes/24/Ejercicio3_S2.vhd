library IEEE;

use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;
use work.BCD_CONSTANTS.all;

entity bp_sum is
end entity bp_sum;

architecture bp_sum of bp_sum is
  signal sum : std_logic_vector (WIDTH-1 downto 0);
  signal cout : std_logic;
  signal a, b : std_logic_vector (WIDTH-1 downto 0);
  signal cin : std_logic;

  component BCDSum is
    port ( sum : out std_logic_vector (WIDTH-1 downto 0);
      cout : out std_logic;
      a, b : in std_logic_vector (WIDTH-1 downto 0);
      cin : in std_logic );
  end component BCDSum;
begin
  uut : component BCDSum port map (sum, cout, a, b, cin);
  main : process
    variable errores : integer range 0 to 4 := 0;
  begin
    a <= "00010001"; b <= "00010001"; cin <= '0';
    wait for 10 ns;
    assert (sum = "00100010" and cout = '0') report "Error en la suma 1";
    if (sum /= "00100010" or cout /= '0') then
      errores := errores + 1;
    end if;
    a <= "10011001"; b <= "00000001"; cin <= '0';
    wait for 10 ns;
    assert (sum = "00000000" and cout = '1') report "Error en la suma 2";
    if (sum /= "00000000" or cout /= '1') then
      errores := errores + 1;
    end if;
    a <= "10011001"; b <= "00000001"; cin <= '1';
    wait for 10 ns;
    assert (sum = "00000001" and cout = '1') report "Error en la suma 3";
    if (sum /= "00000001" or cout /= '1') then
      errores := errores + 1;
    end if;
    a <= "00110101"; b <= "10001000"; cin <= '1';
    wait for 10 ns;
    assert (sum = "00100100" and cout = '1') report "Error en la suma 4";
    if (sum /= "00100100" or cout /= '1') then
      errores := errores + 1;
    end if;
    report "El test ha finalizado con " & integer'image(errores) & " errores";
    wait;
  end process main;
end architecture bp_sum;
