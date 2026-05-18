library IEEE;
use IEEE.std_logic_1164.all;

entity puerta_not is
  port ( y : out std_logic;
         x : in std_logic);
end entity puerta_not;

architecture puerta_not of puerta_not IS
begin 
  y <= not x;
end architecture puerta_not;

library IEEE;
use IEEE.std_logic_1164.all;

entity puerta_and is
  port ( y : out std_logic;
        x1, x2, x3 : in std_logic);
end entity puerta_and;

architecture puerta_and of puerta_and is 
begin
  y <= x1 and x2 and x3;
end architecture puerta_and;

library IEEE;
use IEEE.std_logic_1164.all;

entity puerta_or is
  port ( y : out std_logic;
         x1, x2, x3 : in std_logic);
end entity puerta_or;

architecture puerta_or of puerta_or is
begin
  y <= x1, x2, x3;
end architecture puerta_or;


library IEEE;
use IEEE.std_logic_1164.all;

entity circ is
  port ( y : out std_logic;
         x : in std_logic_vector(2 downto 0));
end entity circ;

architecture circ of circ is

  component puerta_not is
    port ( y : out std_logic;
           x : in std_logic);
  end component puerta_not;

  component puerta_and is
    port ( y : out std_logic;
           x1, x2, x3 : in std_logic);
  end component puerta_and;

  component puerta_or is
    port ( y : out std_logic;
           x1, x2, x3 : in std_logic);
  end component puerta_or;

  signal and1, and2, and3, notx0, notx1, notx2 : std_logic;
begin
  not1 : component puerta_not port map (x => x(0), y => notx0);
  not2 : component puerta_not port map (x => x(1), y => notx1);
  not3 : component puerta_not port map (x => x(2), y => notx2);
  A1 : component puerta_and port map (x1 => notx0, x2 => notx1, x3 => notx2, y => and1);
  A2 : component puerta_and port map (x1 => x(0), x2 => notx1, x3 => notx2, y => and2);
  A3 : component puerta_and port map (x1 => notx0, x2 => x(1), x3 => x(2), y => and3);
  O1 : component puerta_or port map (x1 => and1, x2 => and2, x3 => and3, y => y);
end architecture circ;