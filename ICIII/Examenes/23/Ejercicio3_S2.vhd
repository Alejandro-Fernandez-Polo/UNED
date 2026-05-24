LIBRARY IEEE;
USE IEEE.std_logic_1164.ALL;
USE work.ALU_CONSTANTS.ALL;

ENTITY bp_ALU IS
END ENTITY bp_ALU;

ARCHITECTURE bp_ALU OF bp_ALU IS
  CONSTANT DELAY : TIME := 10 ns;
  SIGNAL y : STD_LOGIC_VECTOR (WIDTH - 1 DOWNTO 0);
  SIGNAL a, b : STD_LOGIC_VECTOR (WIDTH - 1 DOWNTO 0);
  SIGNAL sel : STD_LOGIC_VECTOR (SEL_BITS - 1 DOWNTO 0);

  TYPE v_test IS ARRAY (0 TO 2) OF STD_LOGIC_VECTOR (WIDTH - 1 DOWNTO 0);

  COMPONENT ALU IS
    PORT (
      y : OUT STD_LOGIC_VECTOR (WIDTH - 1 DOWNTO 0);
      a, b : IN STD_LOGIC_VECTOR (WIDTH - 1 DOWNTO 0);
      sel : IN STD_LOGIC_VECTOR (SEL_BITS - 1 DOWNTO 0));
  END COMPONENT ALU;
BEGIN
  uut : COMPONENT ALU PORT MAP(y, a, b, sel);
  main : PROCESS
    VARIABLE a_test : v_test;
    VARIABLE b_test : v_test;
    VARIABLE ia, ib : INTEGER;
  BEGIN
    a_test(0) := (OTHERS => '0');
    a_test(1) := (OTHERS => '1');
    a_test(2) := (OTHERS => '0');

    b_test(0) := (OTHERS => '0');
    b_test(1) := (OTHERS => '0');
    b_test(2) := (OTHERS => '1');

    -- Bucle externo para recorrer las combinaciones de a y b
    FOR i IN 0 TO 2 LOOP
      a <= a_test(i);
      b <= b_test(i);
      ia := TO_INTEGER(SIGNED(a_test(i)));
      ib := TO_INTEGER(SIGNED(b_test(i)));

      -- Bucle interno para probar todas las operaciones posibles en sel
      FOR k IN 0 TO 2 ** SEL_BITS - 1 LOOP
        sel <= STD_LOGIC_VECTOR(TO_SIGNED(k, SEL_BITS));
        WAIT FOR DELAY;
      END LOOP;
    END LOOP;

    WAIT FOR DELAY;
    REPORT "Test finalizado";
    WAIT;
  END PROCESS main;
END ARCHITECTURE bp_ALU;