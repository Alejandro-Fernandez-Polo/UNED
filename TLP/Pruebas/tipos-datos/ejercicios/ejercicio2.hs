reglas = [("DESPEDIDA", ["ADIOS", "HASTA LUEGO"]), ("SALUDO", ["HOLA", "BUENOS DIAS"]), ("FRASE", ["SALUDO DESPEDIDA"])]

-- Apartasdo a)
reescribe cad [] = []
reescribe cad ((ent, sal):resto)
  | cad == ent = sal
  | otherwise = reescribe cad resto

reescribe2 cad reg = head [ sal | (ent, sal) <- reg, ent == cad ]

-- Apartado b)
reescritura r [] = []
reescritura r (cad:resto)
  | salida == [] = cad : (reescritura r resto)
  | otherwise = reescritura r (salida ++ resto)
  where salida = reescribe cad r