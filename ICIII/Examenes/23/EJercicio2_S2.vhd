LIBRARY IEEE;
USE IEEE.std_logic_1164.ALL;
USE IEEE.numeric_std.ALL;
USE work.ALU_CONSTANTS.ALL;

ARCHITECTURE ALU_concurrente OF ALU IS
  SIGNAL al, ul : STD_LOGIC_VECTOR(WIDTH - 1 DOWNTO 0);
BEGIN
  --Unidad Aritm´etica
  arith : PROCESS (a, b, sel) IS
  BEGIN
    CASE sel(SEL_BITS - 2 DOWNTO 0) IS
      WHEN "000" => al <= a;
      WHEN "001" => al <= STD_LOGIC_VECTOR(-signed(a));
      WHEN "010" => al <= STD_LOGIC_VECTOR(signed(a) + 1);
      WHEN "011" => al <= b;
      WHEN "100" => al <= STD_LOGIC_VECTOR(-signed(b));
      WHEN "101" => al <= STD_LOGIC_VECTOR(signed(b) + 1);
      WHEN "110" =>
        al <= STD_LOGIC_VECTOR(signed(a) - signed(b));
      WHEN OTHERS =>
        al <= STD_LOGIC_VECTOR(signed(a) + signed(b));
    END CASE;
  END PROCESS;

  --Unidad L´ogica
  ul <= NOT a WHEN (sel(SEL_BITS - 2 DOWNTO 0) = "000") ELSE
    NOT b WHEN (sel(SEL_BITS - 2 DOWNTO 0) = "001") ELSE
    a OR b WHEN (sel(SEL_BITS - 2 DOWNTO 0) = "010") ELSE
    a AND b WHEN (sel(SEL_BITS - 2 DOWNTO 0) = "011") ELSE
    a NOR b WHEN (sel(SEL_BITS - 2 DOWNTO 0) = "100") ELSE
    a NAND b WHEN (sel(SEL_BITS - 2 DOWNTO 0) = "101") ELSE
    a XNOR b WHEN (sel(SEL_BITS - 2 DOWNTO 0) = "110") ELSE
    a XOR b;

  WITH sel(SEL_BITS - 1) SELECT --Multiplexor
  y <= al WHEN '1',
    ul WHEN OTHERS;

END ARCHITECTURE ALU_concurrente;