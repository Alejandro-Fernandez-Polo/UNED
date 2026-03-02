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

-- patrones para tuplas
(x,y,z) = (1,2,3) -- asignación de tupla a variables
(a,_) = (4,5) -- asignación de tupla a variables, ignorando el segundo elemento, notar que el guion bajo se puede usar para ignorar cualquier elemento de la tupla

-- patrones con nombre
duplicaCabeza (x:xs) = x:x:xs -- función que duplica el primer elemento de una lista y lo deja al principio, seguida de la lista original sin el primer elemento
duplicaCabeza 1@(x:xs) = x:1 -- función que duplica el primer elemento de una lista y lo deja al principio, seguida de la lista original sin el primer elemento, usando un patrón con nombre para referirse a la lista completa

-- funciones definidas a trozos: guardas
esPar x 
  | even x = True
  | otherwise = False --otherwise siempre es True, se puede usar para indicar el caso contrario al caso anterior
esPositivo x 
  | x > 0 = True
  | otherwise = False
absoluto x -- función que devuelve el valor absoluto de un número
  | x < 0 = -x
  | x >= 0 = x

-- subfunciones
f x y = ( a + 1 ) * ( c - 1 )
  where
    a = div (x + y) 2 -- subfunción que calcula el promedio de x e y
    c = mod (x + y) 2 -- subfunción que calcula el residuo de la división de x e y entre 2

-- funciones anónimas o lambdas
esPar x = mod x 2 == 0 
(\x -> mod x 2 == 0) 4 -- función anónima que devuelve True si el número es par, y se le aplica el número 4, lo que devuelve True

-- funcion error
divide a 0 = error "No se puede dividir por cero" -- función que devuelve un error si se intenta dividir por cero
divide a b = a / b -- función que divide a entre b, siempre y cuando b

-- sinonimos de tipos
type Caracter = Char -- sinonimo de tipo para caracteres
type String = [Caracter] -- sinonimo de tipo para cadenas de caracteres, usando el sinonimo de tipo para caracteres

type Complex = (Double, Double) -- sinonimo de tipo para números complejos, usando una tupla de dos números de punto flotante para representar la parte real e imaginaria
a :: Complex -- anotación de tipo para la variable a, indicando que es de tipo Complex
a = (1.0, 2.0) -- asignación de un número complejo

-- Nuevos tipos de datos

-- tipos enumarados
data Color = Rojo | Verde | Azul -- definición de un nuevo tipo de datos llamado Color
deriving (Eq, -- tipo de dato que se puede comparar por igualdad
          Ord, -- tipo de dato que se puede comparar por orden, el orden se define por el orden en que se declaran los constructores, en este caso Rojo < Verde < Azul
          Show, -- tipo de dato que se puede mostrar como una cadena de caracteres
          Read,) -- tipo de dato que se puede leer desde una cadena de caracteres

read "Rojo" :: Color -- función que lee una cadena de caracteres y la convierte en un valor del tipo Color, en este caso devuelve Rojo

-- unión de tipos
data BooleanoEntero = Boleano Bool | Entero Integer -- definición de un nuevo tipo de datos llamado BooleanoEntero, que puede ser un valor del tipo Bool o un valor del tipo Integer
-- asi podemos tener una lista de BooleanoEntero que contenga tanto valores booleanos como enteros
listaMixta :: [BooleanoEntero] -- anotación de tipo para la variable lista
listaMixta = [Boleano True, Entero 5, Boleano False, Entero 10] -- asignación de una lista de BooleanoEntero que contiene tanto valores booleanos como enteros

negacion :: BooleanoEntero -> BooleanoEntero -- función que toma un valor del tipo BooleanoEntero y devuelve su negación si es un valor booleano, o su negativo si es un valor entero
negacion (Boleano x) = Boleano (not x) -- caso para valores booleanos
negacion (Entero x) = Entero (-x) -- caso para valores enteros

-- Producto cartisiano
data Asignatura = Asig String Integer String -- definición de un nuevo tipo de datos llamado Asignatura, que tiene tres campos: un nombre de tipo String, un curso de tipo Integer y un enlace de tipo String
-- Asig es el constructor de datos para el tipo Asignatura, que se puede usar para crear valores del tipo Asignatura
tlp = Asig "TLP" 2 "https://www.tlp.com" -- asignación de un valor del tipo Asignatura, usando el constructor de datos Asig para crear una asignatura llamada TLP de 2 año y una web

esDePrimero (Asig _ x _) = x == 1 -- función que toma un valor del tipo Asignatura y devuelve True si la asignatura es de 1 curso

data Asignatura2 = Asig2 { nombre :: String, curso :: Integer, enlace :: String } -- definición de un nuevo tipo de datos llamado Asignatura2, que tiene tres campos: un nombre de tipo String, un curso de tipo Integer y un enlace de tipo String, usando la sintaxis de registros para definir los campos y sus nombres
tlp2 = Asig2 { nombre = "TLP", curso = 2, enlace = "https://www.tlp.com" } -- asignación de un valor del tipo Asignatura2, usando la sintaxis de registros para crear una asignatura llamada TLP de 2 año y una web
esDePrimero2 x = curso x == 1 -- función que toma un valor del tipo Asignatura2 y devuelve True si la asignatura es de 1 curso, usando la sintaxis de registros para acceder al campo curso

-- Tipos de datos recursivos
data Natural = Cero | Sucesor Natural -- definición de un nuevo tipo de datos llamado Natural, que puede ser Cero o el Sucesor de otro Natural, lo que permite representar números naturales de forma recursiva
uno = Sucesor Cero -- asignación de un valor del tipo Natural que representa el número 1
dos = Sucesor uno -- asignación de un valor del tipo Natural que representa el número 2
tres = Sucesor dos -- asignación de un valor del tipo Natural que representa el número 3

mayorQue2 ( Sucesor ( Sucesor ( Sucesor x ) ) ) = True -- función que toma un valor del tipo Natural y devuelve True si es mayor que 2, usando patrones para encajar el caso de un número mayor que 2
mayorQue2 _ = False -- caso para números menores o iguales a 2

-- función que compara dos números naturales y devuelve True si el primero es mayor que el segundo
mayor (Sucesor _) Cero = True
mayor Cero _ = False
mayor (Sucesor x) (Sucesor y) = mayor x y

