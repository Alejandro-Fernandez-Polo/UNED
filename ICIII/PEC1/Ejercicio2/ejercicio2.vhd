LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;

ENTITY ejercicio2 IS
  PORT (
    d : OUT STD_LOGIC_VECTOR(2 DOWNTO 0);
    x, y : IN STD_LOGIC_VECTOR(1 DOWNTO 0));
END ENTITY ejercicio2;

ARCHITECTURE implementacion OF ejercicio2 IS

  COMPONENT puerta_not IS
    PORT (
      x : OUT STD_LOGIC;
      y : IN STD_LOGIC);
  END COMPONENT puerta_not;

  COMPONENT puerta_or_3in IS
    PORT (
      x : OUT STD_LOGIC;
      y0, y1, y2 : IN STD_LOGIC);
  END COMPONENT puerta_or_3in;

  COMPONENT puerta_or_6in IS
    PORT (
      x : OUT STD_LOGIC;
      y0, y1, y2, y3, y4, y5 : IN STD_LOGIC);
  END COMPONENT puerta_or_6in;

  COMPONENT puerta_and IS
    PORT (
      x : OUT STD_LOGIC;
      y0, y1 : IN STD_LOGIC);
  END COMPONENT puerta_and;

  COMPONENT puerta_and_3in IS
    PORT (
      x : OUT STD_LOGIC;
      y0, y1, y2 : IN STD_LOGIC);
  END COMPONENT puerta_and_3in;

  COMPONENT puerta_and_4in IS
    PORT (
      x : OUT STD_LOGIC;
      y0, y1, y2, y3 : IN STD_LOGIC);
  END COMPONENT puerta_and_4in;

  COMPONENT puerta_xor IS
    PORT (
      x : OUT STD_LOGIC;
      y0, y1 : IN STD_LOGIC);
  END COMPONENT puerta_xor;

  SIGNAL notX1, notX0, notY1, notY0, C1, C2, C3, C4, C5, C6, C7, C8, C9 : STD_LOGIC;
BEGIN
  not1 : puerta_not PORT MAP(y => x(1), x => notX1);
  not2 : puerta_not PORT MAP(y => x(0), x => notX0);
  not3 : puerta_not PORT MAP(y => y(1), x => notY1);
  not4 : puerta_not PORT MAP(y => y(0), x => notY0);

  xor1 : puerta_xor PORT MAP(y0 => x(0), y1 => y(0), x => d(0));

  and1 : puerta_and_3in PORT MAP(y0 => notX1, y1 => x(0), y2 => y(1), x => C4);
  and2 : puerta_and_3in PORT MAP(y0 => x(1), y1 => x(0), y2 => notY1, x => C5);
  and3 : puerta_and_3in PORT MAP(y0 => notX1, y1 => y(1), y2 => notY0, x => C6);
  and4 : puerta_and_3in PORT MAP(y0 => x(1), y1 => notY1, y2 => notY0, x => C7);
  and5 : puerta_and_4in PORT MAP(y0 => notX1, y1 => notX0, y2 => notY1, y3 => y(0), x => C8);
  and6 : puerta_and_4in PORT MAP(y0 => x(1), y1 => notX0, y2 => y(1), y3 => y(0), x => C9);
  or1 : puerta_or_6in PORT MAP(y0 => C4, y1 => C5, y2 => C6, y3 => C7, y4 => C8, y5 => C9, x => d(1));

  and7 : puerta_and PORT MAP(y0 => notX1, y1 => y(1), x => C1);
  and8 : puerta_and_3in PORT MAP(y0 => notX1, y1 => notX0, y2 => y(0), x => C2);
  and9 : puerta_and_3in PORT MAP(y0 => notX0, y1 => y(1), y2 => y(0), x => C3);
  or2 : puerta_or_3in PORT MAP(y0 => C1, y1 => C2, y2 => C3, x => d(2));

END ARCHITECTURE implementacion;