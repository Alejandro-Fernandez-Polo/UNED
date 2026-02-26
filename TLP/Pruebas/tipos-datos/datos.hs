(4, "vacio", False)-- tupla con un entero, una cadena y un booleano
[1,2,3]-- lista del 1 al 3
[5..87]-- lista del 5 al 87
[1,3..10]-- lista del 1 al 10 con salto de 2
[10..]-- lista del 10 al infinito
[1,3..]-- lista del 1 al infinito con salto de 2
-- función que devuelve True si el número es 1 y False si el número es 2, sino devuelve Nothing
f 1 = True
f 2 = False
-- función que suma dos números
suma x y = x + y
-- función que devuelve el número 2 sin importar el valor de x
siempre2 x = 2 
siempre2 _ = 2 -- otra forma de escribir la función anterior, usando un guion bajo para indicar que no se usa el argumento

--Patrones para listas
-- función encaja una lista vacia
esVacia [] = True
-- función encaja una lista con un solo elemento
esUnico [x] = True
-- función encaja una lista con solo dos elementos
esUnico [x,y] = True
-- función encaja una lista con al menos dos elementos
esAlMenosDos (x:y:_) = True
-- función encaja una lista con al menos un elemento y una cola cualquiera
esAlMenosUno (x:xs) = True
-- función encaja una lista con al menos dos elementos y una cola cualquiera
esAlMenosDos (x:y:xs) = True

-- función suma todos los elementos de una lista
suma [] = 0
suma (x:xs) = x + suma xs

