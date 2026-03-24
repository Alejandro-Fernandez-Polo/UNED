library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity ejercicio1_b is 
  port ( F : out std_logic;
         x, y, z : in std_logic);
end entity ejercicio1_b;

architecture comp_funcLog of ejercicio1_b is

  component puerta_not is 
    port (x : out std_logic;
          y : in std_logic);
  end component puerta_not;

  component puerta_or is 
    port (x : out std_logic;
          y0, y1 : in std_logic);
  end component puerta_or;

  component puerta_and is 
    port (x : out std_logic;
          y0, y1 : in std_logic);
  end component puerta_and;

  component puerta_and_3in is 
    port (x : out std_logic;
          y0, y1, y2 : in std_logic);
  end component puerta_and_3in;

  signal notX, notY, and1, and2, and3, notAnd3, or1, or2 : std_logic;
begin
  unit1 : puerta_not port map ( y => x, x => notX );
  unit2 : puerta_not port map ( y => y, x => notY );
  unit3 : puerta_or port map ( y0 => x, y1 => notY, x => or1 );
  unit4 : puerta_and port map ( y0 => x, y1 => notY, x => and1 );
  unit5 : puerta_and port map ( y0 => notX, y1 => y, x => and2 );
  unit6 : puerta_or port map ( y0 => and1, y1 => and2, x => or2 );
  unit7 : puerta_and port map ( y0 => y, y1 => z, x => and3 );
  unit8 : puerta_not port map ( y => and3, x => notAnd3 );
  unit9 : puerta_and_3in port map ( y0 => or1, y1 => or2, y2 => notAnd3, x => F );
end architecture comp_funcLog;
