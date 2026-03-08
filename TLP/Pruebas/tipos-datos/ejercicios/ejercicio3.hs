-- Apartado a)
expand [] = []
expand [x] = [1,x]
expand (x:y:ys)
  | x /= y = 1:x:expand (y:ys)
  | otherwise = iexpand ys 2 x
  where
    iexpand [] n x = [n,x]
    iexpand (y:ys) n x
      | x /= y = n:x:expand (y:ys)
      | otherwise = iexpand ys (n+1) x

repetidos [] = 0
repetidos (x:xs) = length ( takeWhile (x==) (x:xs) )

listarestante [] = []
listarestante (x:xs) = dropWhile (x==) xs

expand2 [] = []
expand2 (x:xs) = (repetidos (x:xs)) : x : expand2 (listarestante (x:xs)) 

-- Apartado b)
list2num xs = ilist2num 0 xs
  where
    ilist2num n [] = n
    ilist2num n (x:xs) = ilist2num (10*n+x) xs

-- Apartado c)
expansivos = iexpansivos [1]
iexpansivos xs = (list2num xs) : iexpansivos (expand2 xs)

expansivos2 = map list2num (iterate expand2 [1])