/**
 * La clase UsuarioEstandar es una subclase de la clase Usuario que representa a un usuario estándar
 * dentro del sistema. Hereda los atributos y comportamiento de la clase Usuario y no añade nuevas funcionalidades,
 * pero permite crear instancias específicas para usuarios que pertenecen a esta categoría.
 *
 * Dado que extiende a Usuario, tiene acceso a todas las propiedades y métodos inherentes, como el manejo
 * de saldos, historial de viajes y las interacciones con el sistema de gestión.
 */
public class UsuarioEstandar extends Usuario
{

    /**
     * Constructor de la clase UsuarioEstandar que inicializa una nueva instancia de usuario estándar
     * con los atributos especificados. Este constructor invoca al constructor de la superclase Usuario.
     *
     * @param dni         DNI del usuario, debe ser una cadena de texto válida con 8 dígitos y una letra.
     * @param nombre      Nombre del usuario, no puede ser nulo ni vacío.
     * @param fNacimiento Fecha de nacimiento del usuario como número entero en formato DDMMAAAA.
     * @param saldo       Saldo inicial del usuario estándar, expresado como un valor decimal.
     */
    public UsuarioEstandar(String dni, String nombre, int fNacimiento, double saldo)
    {
        super(dni, nombre, fNacimiento, saldo);
    }

    @Override
    public String toString() {
        return "UsuarioEstandar{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fNacimiento=" + fNacimiento +
                ", saldo=" + saldo +
                '}';
    }
}
