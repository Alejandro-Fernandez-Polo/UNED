library IEEE;
use IEEE.std_logic_1164.all;

entity bp_circ IS
end entity bp_circ;

architecture bp_circ of bp_circ is
  signal y : std_logic;
  signal x : std_logic_vector (2 downto 0);

  component circ is 
    port ( y : out std_logic;
           x : in std_logic_vector (2 downto 0));
  end component circ;

  
begin
  uut : component circ port map (x, y);

  gen_vec_test : process
    variable test_in : unsigned (2 downto 0);
    variable y2 ; std_logic;
  begin
    test_in := B"000";
    for count in 0 to 7 loop
      x(2) <= test_in(2);
      x(1) <= test_in(1);
      x(0) <= test_in(0);
      wait for 10 ns;
      if (test_in = "000") or (test_in = "001") or (test_in = "110") then
        y2 := '1';
      else 
        y2 := '0';
      end if;
      assert (y2 = y)
      report "ERROR, la salida no corresponde"
      test_in := test_in + 1;
    end loop;
    wait;
  end process gen_vec_test;
end architecture bp_circ;