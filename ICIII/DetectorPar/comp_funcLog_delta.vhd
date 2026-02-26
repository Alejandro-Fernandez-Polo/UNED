-- Detector de número par de entreadas '1'
-- architecture comportamiento func logica
-- comp_funcLog_delta.vhd
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity detectorPar is
  port ( par : out std_logic;
         a : in std_logic_vector(2 downto 0));
end entity detectorPar;

architecture comp_funcLog of detectorPar is
  signal p1, p2, p3, p4 : std_logic;
begin
  par <= (p1 or p2) or (p3 or p4);
  p1 <= (not a(2)) and (not a(1)) and (not a(0));
  p2 <= (not a(2)) and a(1) and a(0);
  p3 <= a(2) and (not a(1)) and a(0);
  p4 <= a(2) and a(1) and (not a(0));
end architecture comp_funcLog; 