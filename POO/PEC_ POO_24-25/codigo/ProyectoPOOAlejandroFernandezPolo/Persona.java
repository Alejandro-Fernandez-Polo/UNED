/**
 * Clase abstracta que representa una persona.
 * Contiene información básica como nombre, DNI y fecha de nacimiento.
 * Proporciona validaciones para asegurar la integridad de los datos introducidos.
 */
public abstract class Persona {

    // Atributos comunes de cualquier persona
    public String dni;
    public String nombre;
    public int fNacimiento; // Fecha en formato entero AAAAMMDD


    /**
     * Constructor de la clase Persona que inicializa los atributos de la instancia
     * validando que sean correctos.
     *
     * @param dni         DNI completo, debe tener 8 dígitos y una letra válida.
     * @param nombre      Nombre de la persona, no puede ser nulo o vacío.
     * @param fNacimiento Fecha de nacimiento como entero en formato DDMMAAAA.
     * @throws IllegalArgumentException Si el DNI no es correcto.
     * @throws IllegalArgumentException Si el nombre es nulo o vacío.
     * @throws IllegalArgumentException Si la fecha de nacimiento no es válida.
     */
    public Persona(String dni, String nombre, int fNacimiento) {
        if(dniCorrecto(dni)){
            if(contenidoCadena(nombre)){
                if(fechaCorrecta(fNacimiento)){
                    this.dni = dni;
                    this.nombre = nombre;
                    this.fNacimiento = fNacimiento;
                }else{
                    throw new IllegalArgumentException("La fecha de nacimiento no es correcta");
                }
            }else{
                throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
            }
        }else{
            throw new IllegalArgumentException("El dni introducido no es correcto");
        }
    }

    // Getters y setters con validaciones
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI si es válido.
     *
     * @param dni DNI completo con letra
     * @throws IllegalArgumentException si el DNI no cumple con el formato y letra
     */
    public void setDni(String dni) {
        if (dniCorrecto(dni)) {
            this.dni = dni;
        } else {
            throw new IllegalArgumentException("El dni introducido no es correcto");
        }
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre si no está vacío.
     *
     * @param nombre Nombre de la persona
     * @throws IllegalArgumentException si el nombre es nulo o está vacío
     */
    public void setNombre(String nombre) {
        if (contenidoCadena(nombre)) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
    }

    public int getfNacimiento() {
        return fNacimiento;
    }

    /**
     * Establece la fecha de nacimiento si es válida.
     *
     * @param fNacimiento Fecha en formato DDMMAAAA
     * @throws IllegalArgumentException si la fecha no es válida
     */
    public void setfNacimiento(int fNacimiento) {
        System.out.println("editando");
        if (fechaCorrecta(fNacimiento)) {
            this.fNacimiento = fNacimiento;
        } else {
            throw new IllegalArgumentException("La fecha de nacimiento no es correcta");
        }
    }

    /**
     * Valida que una cadena no esté vacía ni contenga solo espacios.
     *
     * @param cadena Cadena a validar
     * @return true si tiene contenido
     */
    private static boolean contenidoCadena(String cadena) {
        return !cadena.trim().isEmpty();
    }

    /**
     * Valida que el DNI tenga 8 dígitos numéricos seguidos de la letra correcta.
     *
     * @param dniConLetra DNI completo como string
     * @return true si el formato y la letra son correctos
     */
    private static boolean dniCorrecto(String dniConLetra) {
        boolean correcto = false;
        char[] arrayDni = dniConLetra.toCharArray();
        if (arrayDni.length == 9) {
            String dni = "";
            int cont = 0;
            for (int j = 0; j < arrayDni.length - 1; j++) {
                if (arrayDni[j] >= '0' && arrayDni[j] <= '9') {
                    dni = dni + arrayDni[j];
                    ++cont;
                }
            }
            if (cont == 8) {
                if (compLetraDni(dniConLetra)) {
                    correcto = true;
                }
            }
        }
        return correcto;
    }

    /**
     * Comprueba si la letra del DNI corresponde a los 8 números iniciales.
     *
     * @param dni DNI completo como string
     * @return true si la letra es correcta
     */
    private static boolean compLetraDni(String dni) {
        String numDni = dni.substring(0, 8);
        char[] letras = {'T', 'R', 'W', 'A', 'G', 'N', 'Y', 'F', 'P', 'D', 'X',
                'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        int num = (Integer.parseInt(numDni) % 23);
        String dniCorrecto = numDni + letras[num];
        return dni.equalsIgnoreCase(dniCorrecto);
    }

    /**
     * Valida que la fecha tenga un formato válido y sea una fecha real.
     *
     * @param fecha Fecha como entero en formato AAAAMMDD
     * @return true si la fecha es válida
     */
    private static boolean fechaCorrecta(int fecha) {
        boolean correcta = false;

        int year = fecha % 10000;
        int month = (fecha / 10000) % 100;
        int day = fecha / 1000000;

        if ((month <= 12 && month >= 1) && (year >= 1 && day >= 1)) {
            switch (month) {
                case 1: case 3: case 5: case 6: case 9: case 10: case 12:
                    correcta = day <= 31;
                    break;
                case 4: case 7: case 8: case 11:
                    correcta = day <= 30;
                    break;
                case 2:
                    correcta = esBisiesto(year) ? day <= 29 : day <= 28;
                    break;
            }
        }
        return correcta;
    }


    /**
     * Determina si un año es bisiesto.
     *
     * @param year Año a evaluar
     * @return true si es bisiesto
     */
    private static boolean esBisiesto(int year) {
        boolean esBisiesto = false;
        if (year < 400) {
            if (esDivisible(year, 4)) {
                esBisiesto = true;
            }
        } else {
            if ((esDivisible(year, 4) && !esDivisible(year, 100))
                    || (esDivisible(year, 4) && esDivisible(year, 400))) {
                esBisiesto = true;
            }
        }
        return esBisiesto;
    }

    /**
     * Verifica si un año es divisible por cierto número.
     *
     * @param year Año
     * @param dividendo Divisor
     * @return true si es divisible
     */
    private static boolean esDivisible(int year, int dividendo) {
        return year % dividendo == 0;
    }

    /**
     * Devuelve una representación textual del objeto Persona.
     *
     * @return String con la información principal de la persona
     */
    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", dni=" + dni + ", fechaNacimiento=" + fNacimiento + '}';
    }

}
