/**
 * Representa a un empleado dentro del sistema, heredando de la clase Persona.
 * Esta clase sirve como clase base para roles específicos de empleado,
 * como administradores, mecánicos y personal de mantenimiento.
 */
public class Empleado extends Persona
{

    /**
     * Construye una instancia de la clase Empleado inicializando sus atributos
     * usando los parámetros proporcionados. Este constructor llama al constructor de la superclase
     * para validar e inicializar las propiedades compartidas de una Persona.
     *
     * @param dni El DNI completo del empleado. Debe constar de 8 dígitos y una letra válida.
     * @param nombre El nombre del empleado. No puede ser nulo ni estar vacío.
     * @param fNacimiento La fecha de nacimiento del empleado como un entero con el formato DDMMAAAA.
     * @throws IllegalArgumentException Si el DNI no es válido o tiene un formato incorrecto.
     * @throws IllegalArgumentException Si el nombre es nulo o está vacío.
     * @throws IllegalArgumentException Si la fecha de nacimiento no es válida.
     */
    public Empleado(String dni, String nombre, int fNacimiento) throws IllegalArgumentException
    {
        super(dni, nombre, fNacimiento);
    }

}
