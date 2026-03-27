library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

architecture implementacion_2 of ejercicio1 is

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

  signal notX, notY, XAndNotY, NotXAndY, YAndZ, notYAndZ, XOrNotY, XAndNotYOrNotXAndY : std_logic;
begin
  not1 : puerta_not port map ( y => x, x => notX );
  not2 : puerta_not port map ( y => y, x => notY );
  or1 : puerta_or port map ( y0 => x, y1 => notY, x => XOrNotY );
  and1 : puerta_and port map ( y0 => x, y1 => notY, x => XAndNotY );
  and2 : puerta_and port map ( y0 => notX, y1 => y, x => NotXAndY );
  or2 : puerta_or port map ( y0 => XAndNotY, y1 => NotXAndY, x => XAndNotYOrNotXAndY );
  and3 : puerta_and port map ( y0 => y, y1 => z, x => YAndZ );
  not3 : puerta_not port map ( y => YAndZ, x => notYAndZ );
  and4 : puerta_and_3in port map ( y0 => XOrNotY, y1 => XAndNotYOrNotXAndY, y2 => notYAndZ, x => F );
end architecture implementacion_2;
