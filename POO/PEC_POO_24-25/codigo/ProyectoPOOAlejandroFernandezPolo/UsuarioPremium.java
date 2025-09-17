/**
 * La clase UsuarioPremium extiende las funcionalidades de la clase Usuario
 * para representar a un usuario con privilegios avanzados dentro del sistema.
 * Este tipo de usuario tiene acceso a características exclusivas o ventajas añadidas
 * en comparación con los usuarios estándar.
 */
public class UsuarioPremium extends Usuario
{

    /**
     * Constructor de la clase UsuarioPremium que crea una nueva instancia de usuario premium
     * con los atributos especificados.
     *
     * @param dni         DNI del usuario, debe ser una cadena de texto válida con 8 dígitos y una letra.
     * @param nombre      Nombre del usuario, no puede ser nulo ni vacío.
     * @param fNacimiento Fecha de nacimiento del usuario como número entero en formato DDMMAAAA.
     * @param saldo       Saldo inicial del usuario premium, expresado como un valor decimal.
     */
    public UsuarioPremium(String dni, String nombre, int fNacimiento, double saldo)
    {
        super(dni, nombre, fNacimiento, saldo);
    }

    @Override
    public String toString() {
        return "UsuarioPremium{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fNacimiento=" + fNacimiento +
                ", saldo=" + saldo +
                '}';
    }
}
