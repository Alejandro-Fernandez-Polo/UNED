module Futoshiki where

-- Bidimensional Matrix --
type Matrix a = [[a]]

-- Relation between cells --
data Relation = Ind | RGT | RLT
  deriving (Eq, Read, Show)

-- Futoshiki definition --
data Futoshiki = Futoshiki {
  size  :: Int,
  cells :: Matrix Int,
  hRel  :: Matrix Relation,
  vRel  :: Matrix Relation
}
  deriving (Eq, Read, Show)

-- Función de backtracking genérica --
bt :: (a -> Bool) -> (a -> [a]) -> a -> [a]
bt    esSol          succ          n
  | esSol n                           = [n]
  | otherwise                         = concat (map (bt esSol succ) (succ n))

-- Main function: solves a Futoshiki --
solve :: Futoshiki -> [Matrix Int]

esSol :: Futoshiki -> Bool
esSol f = notElem 0 (concat (cells f))

-- f es nuestro Futoshiki
buscarCero :: Futoshiki -> (Int, Int)
buscarCero f = buscarCeroAux (cells f) (size f) 0 0

buscarCeroAux :: Matrix Int -> Int -> Int -> Int -> (Int, Int)
buscarCeroAux matriz n fila col 
  | col == n = buscarCeroAux matriz n (fila+1) 0
  | fila == n = (-1, -1)
  | ((matriz !! fila) !! col == 0) = (fila, col)
  | otherwise = buscarCeroAux matriz n fila (col+1)