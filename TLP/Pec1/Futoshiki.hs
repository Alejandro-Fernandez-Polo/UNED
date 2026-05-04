module Futoshiki where

-- Bidimensional Matrix --
type Matrix a = [[a]]

-- Relation between cells --
data Relation = Ind | RGT | RLT
  deriving (Eq, Read, Show)

-- Futoshiki definition --
data Futoshiki = Futoshiki
  { size :: Int,
    cells :: Matrix Int,
    hRel :: Matrix Relation,
    vRel :: Matrix Relation
  }
  deriving (Eq, Read, Show)

-- Función de backtracking genérica --
bt :: (a -> Bool) -> (a -> [a]) -> a -> [a]
bt esSol succ n
  | esSol n = [n]
  | otherwise = concat (map (bt esSol succ) (succ n))

-- Main function: solves a Futoshiki --
solve :: Futoshiki -> [Matrix Int]
solve f = map cells (bt esSol sucesores f)

-- Predicado que verifica si un estado del puzzle es una solución
-- Una solución es válida cuando no quedan celdas vacías (todos los valores son distintos de 0)
-- Parámetro: f es el puzzle actual
-- Retorna: True si es solución, False en caso contrario
esSol :: Futoshiki -> Bool
esSol f = notElem 0 (concat (cells f))


-- Función auxiliar que busca la primera celda vacía (valor 0) en el puzzle
-- Parámetro: f es el puzzle Futoshiki
-- Retorna: tupla (fila, col) de la primera celda vacía encontrada
-- Si no hay celdas vacías, retorna (-1, -1)
buscarCero :: Futoshiki -> (Int, Int)
buscarCero f = buscarCeroAux (cells f) (size f) 0 0

-- Función auxiliar recursiva que realiza la búsqueda secuencial de celdas vacías
-- Parámetros:
--   matriz: la matriz de celdas del puzzle
--   n: tamaño del puzzle (dimensión n x n)
--   fila: fila actual en la búsqueda (0 a n-1)
--   col: columna actual en la búsqueda (0 a n-1)
-- Funcionamiento: itera de izquierda a derecha, de arriba a abajo
-- Si encuentra un 0, retorna sus coordenadas inmediatamente
-- Si llega al final de una fila, continúa con la siguiente fila
-- Si llega al final de la matriz, retorna (-1, -1)
buscarCeroAux :: Matrix Int -> Int -> Int -> Int -> (Int, Int)
buscarCeroAux matriz n fila col
  | col == n = buscarCeroAux matriz n (fila + 1) 0
  | fila == n = (-1, -1)
  | ((matriz !! fila) !! col == 0) = (fila, col)
  | otherwise = buscarCeroAux matriz n fila (col + 1)

-- Función que genera todos los sucesores válidos de un estado del puzzle
-- Un sucesor es un new puzzle donde hemos rellenado una celda vacía con un valor válido
-- Parámetro: f es el puzzle actual
-- Retorna: lista de todos los puzzles sucesores (estados después de un movimiento válido)
-- Funcionamiento: encuentra la primera celda vacía y genera nuevos puzzles
-- colocando cada valor de 1 a n que sea válido según las restricciones
sucesores :: Futoshiki -> [Futoshiki]
sucesores f = [generarNuevoTablero f fila col v | v <- [1 .. n], movimientoEsValido f fila col v]
  where
    n = size f
    (fila, col) = buscarCero f

-- Función que verifica si un movimiento (colocar un valor en una celda) es válido
-- Un movimiento es válido si:
--   1. El valor no existe ya en la misma fila
--   2. El valor no existe ya en la misma columna
--   3. Se respetan todas las restricciones de relación con celdas adyacentes
-- Parámetros:
--   f: el puzzle actual
--   fila: fila de la celda donde queremos colocar el valor
--   col: columna de la celda donde queremos colocar el valor
--   v: valor a colocar (debe estar entre 1 y n)
-- Retorna: True si el movimiento es válido, False en caso contrario
movimientoEsValido :: Futoshiki -> Int -> Int -> Int -> Bool
movimientoEsValido f fila col v =
  comprobarFila f fila v
    && comprobarColumna f col v
    && comprobarRestricciones f fila col v

-- Función que verifica que el valor no esté ya en la fila
-- Parámetro: f es el puzzle, fila es el índice de la fila, v es el valor a comprobar
-- Retorna: True si el valor no está en la fila, False si ya existe
comprobarFila :: Futoshiki -> Int -> Int -> Bool
comprobarFila f fila v = notElem v (cells f !! fila)

-- Función que verifica que el valor no esté ya en la columna
-- Parámetro: f es el puzzle, col es el índice de la columna, v es el valor a comprobar
-- Retorna: True si el valor no está en la columna, False si ya existe
comprobarColumna :: Futoshiki -> Int -> Int -> Bool
comprobarColumna f col v = notElem v ([fila_actual !! col | fila_actual <- cells f])

-- Función que verifica todas las restricciones de relación con celdas adyacentes
-- Comprueba la relación con la celda de la izquierda, derecha, arriba y abajo
-- Retorna: True si se respetan todas las restricciones, False en caso contrario
comprobarRestricciones :: Futoshiki -> Int -> Int -> Int -> Bool
comprobarRestricciones f fila col v =
  comprobarIzquierda f fila col v
    && comprobarDerecha f fila col v
    && comprobarArriba f fila col v
    && comprobarAbajo f fila col v


-- Función que comprueba la restricción con la celda de la izquierda
-- Si estamos en la primera columna, no hay celda a la izquierda (retorna True)
-- Si la celda de la izquierda está vacía (0), no podemos verificar aún (retorna True)
-- En caso contrario, verifica que la relación se cumpla:
--   - Ind: no hay restricción, siempre válido
--   - RGT: la celda izquierda es mayor que v
--   - RLT: la celda izquierda es menor que v
-- Retorna: True si la restricción se cumple o no aplica, False si se viola
comprobarIzquierda :: Futoshiki -> Int -> Int -> Int -> Bool
comprobarIzquierda f fila col v
  | col == 0 = True
  | (cells f !! fila) !! (col - 1) == 0 = True
  | otherwise = case relacion of
      Ind -> True
      RGT -> vecino > v
      RLT -> vecino < v
  where
    vecino = (cells f !! fila) !! (col - 1)
    relacion = (hRel f !! fila) !! (col - 1)

-- Función que comprueba la restricción con la celda de la derecha
-- Si estamos en la última columna, no hay celda a la derecha (retorna True)
-- Si la celda de la derecha está vacía (0), no podemos verificar aún (retorna True)
-- En caso contrario, verifica que la relación se cumpla:
--   - Ind: no hay restricción, siempre válido
--   - RGT: la celda derecha es menor que v (porque v > vecino)
--   - RLT: la celda derecha es mayor que v (porque v < vecino)
-- Retorna: True si la restricción se cumple o no aplica, False si se viola
comprobarDerecha :: Futoshiki -> Int -> Int -> Int -> Bool
comprobarDerecha f fila col v
  | col == (size f - 1) = True
  | (cells f !! fila) !! (col + 1) == 0 = True
  | otherwise = case relacion of
      Ind -> True
      RGT -> vecino < v
      RLT -> vecino > v
  where
    vecino = (cells f !! fila) !! (col + 1)
    relacion = (hRel f !! fila) !! col

-- Función que comprueba la restricción con la celda de arriba
-- Si estamos en la primera fila, no hay celda arriba (retorna True)
-- Si la celda de arriba está vacía (0), no podemos verificar aún (retorna True)
-- En caso contrario, verifica que la relación se cumpla:
--   - Ind: no hay restricción, siempre válido
--   - RGT: la celda arriba es mayor que v
--   - RLT: la celda arriba es menor que v
-- Retorna: True si la restricción se cumple o no aplica, False si se viola
comprobarArriba :: Futoshiki -> Int -> Int -> Int -> Bool
comprobarArriba f fila col v
  | fila == 0 = True
  | (cells f !! (fila - 1)) !! col == 0 = True
  | otherwise = case relacion of
      Ind -> True
      RGT -> vecino > v
      RLT -> vecino < v
  where
    vecino = (cells f !! (fila - 1)) !! col
    relacion = (vRel f !! (fila - 1)) !! col

-- Función que comprueba la restricción con la celda de abajo
-- Si estamos en la última fila, no hay celda abajo (retorna True)
-- Si la celda de abajo está vacía (0), no podemos verificar aún (retorna True)
-- En caso contrario, verifica que la relación se cumpla:
--   - Ind: no hay restricción, siempre válido
--   - RGT: la celda abajo es menor que v (porque v > vecino)
--   - RLT: la celda abajo es mayor que v (porque v < vecino)
-- Retorna: True si la restricción se cumple o no aplica, False si se viola
comprobarAbajo :: Futoshiki -> Int -> Int -> Int -> Bool
comprobarAbajo f fila col v
  | fila == (size f - 1) = True
  | (cells f !! (fila + 1)) !! col == 0 = True
  | otherwise = case relacion of
      Ind -> True
      RGT -> vecino < v
      RLT -> vecino > v
  where
    vecino = (cells f !! (fila + 1)) !! col
    relacion = (vRel f !! fila) !! col

-- Función que genera un nuevo puzzle después de colocar un valor en una celda específica
-- Retorna: un nuevo puzzle con la celda actualizada (el resto permanece igual)
-- Funcionamiento: crea una nueva matriz con el valor colocado y actualiza el puzzle
generarNuevoTablero :: Futoshiki -> Int -> Int -> Int -> Futoshiki
generarNuevoTablero f fila col v = f {cells = nuevaMatriz}
  where
    nuevaMatriz = reemplazarEnMatriz (cells f) fila col v

-- Función auxiliar que reemplaza un elemento en una lista
-- Retorna: una nueva lista con el valor reemplazado en la posición especificada
-- Funcionamiento: toma los elementos antes del índice, añade el nuevo valor, y luego
-- los elementos después del índice
reemplazarEnLista :: [a] -> Int -> a -> [a]
reemplazarEnLista lista indice nuevoValor =
  take indice lista ++ [nuevoValor] ++ drop (indice + 1) lista

-- Función que reemplaza un elemento en una matriz bidimensional
-- Retorna: una nueva matriz con el valor reemplazado en la posición especificada
-- Funcionamiento:
--   1. Extrae la fila que contiene el elemento
--   2. Usa reemplazarEnLista para actualizar el valor en esa fila
--   3. Usa reemplazarEnLista nuevamente para insertar la fila actualizada en la matriz
reemplazarEnMatriz :: Matrix Int -> Int -> Int -> Int -> Matrix Int
reemplazarEnMatriz matriz fila col nuevoValor =
  let filaAntigua = matriz !! fila
      filaNueva = reemplazarEnLista filaAntigua col nuevoValor
   in reemplazarEnLista matriz fila filaNueva