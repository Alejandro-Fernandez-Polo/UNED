suma3 x = x + 3

-- hace lo mismo que suma3 x * suma3 x pero con una función de orden superior
funCuadrado f x = f x * f x
main = do
    print (suma3 5)
    print (funCuadrado suma3 5)