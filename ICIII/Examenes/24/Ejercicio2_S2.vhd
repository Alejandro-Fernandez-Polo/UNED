package BCD_CONSTANTS is
constant WIDTH : integer := 8;
-- N´um. bits de los operandos
end package BCD_CONSTANTS;

entity BCDSum is
port( sum : out std_logic_vector (WIDTH-1 downto 0);
cout : out std_logic;
a, b : in std_logic_vector (WIDTH-1 downto 0);
cin : in std_logic );
end entity BCDSum;

library IEEE;

use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;
use work.BCD_CONSTANTS.all;

architecture BCDSum of BCDSum is
begin
  process (a, b, cin)
    variable sumv : unsigned(WIDTH downto 0 );
    variable acarreo : unsigned(4 downto 0)
  begin
    acarreo := "00000";
    acarreo(0) := cin;
    sumv(4 downto 0) := unsigned('0' & a(3 downto 0)) + unsigned('0' & b(3 downto 0)) + acarreo;
    if (sum(4 downto 0) > 9 ) then
      sumv(4 downto 0) := sumv(4 downto 0) + 6;
    end if;
    acarreo(0) := sumv(4);
    sumv(8 downto 4) := unsigned('0' & a( 7 downto 4)) + unsigned('0' & b(7 downto 4)) + acarreo;
    if (sum(8 downto 4) > 9 ) then
      sumv(8 downto 4) := sumv(8 downto 4) + 6;
    end if;
    sum <= st_logic_vector(sumv(7 downto 0));
    cout <= sumv(WIDTH);
  end process;
end architecture BCDSum;