polidivisible n 
  | n <= 0 = False
  | otherwise = ipolidivisible n (long n)
  where 
    ipolidivisible _ 1 = True
    ipolidivisible n lon = ((mod n lon) == 0) && (ipolidivisible (div n 10) (lon - 1))
    long n
      | n < 10 = 1
      | otherwise = 1 + long (div n 10)