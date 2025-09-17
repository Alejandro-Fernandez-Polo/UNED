import java.util.ArrayList;


/**
 * La clase Usuario representa a un usuario en el sistema que puede realizar diversas operaciones,
 * como gestionar tareas, alquilar vehículos y mantener un historial de alquileres.
 * Extiende la clase Persona e incluye atributos y funcionalidades adicionales,
 * específicos de un usuario en el contexto del sistema.
 */
public class Usuario extends Persona {
    /**
     * Variable que representa una instancia única de la clase SistemaGestion,
     * utilizada para gestionar las operaciones del sistema relacionadas con un usuario.
     * Permite realizar acciones como gestionar tareas, alquilar vehículos
     */
    SistemaGestion sistema = SistemaGestion.getInstancia();
    /**
     * Representa el historial de viajes de un usuario.
     * Esta lista almacena instancias de la clase Alquiler, que registran los detalles de cada alquiler
     * o viaje realizado por el usuario dentro del sistema.
     * Sirve como registro histórico de los alquileres del usuario, lo que permite el seguimiento de sus actividades.
     */
    private ArrayList<Alquiler> historialViajes;
    /**
     * Representa el saldo disponible asociado a un usuario en el sistema.
     * Esta variable se utiliza para rastrear el valor monetario actual que el usuario puede gastar
     * en actividades como alquilar vehículos o realizar otras operaciones del sistema.
     */
    public double saldo;


    /**
     * Constructor de la clase Usuario que inicializa una nueva instancia de usuario con
     * los atributos especificados, incluyendo su saldo inicial y creando un historial de viajes vacío.
     *
     * @param dni         DNI del usuario, debe ser una cadena de texto válida con 8 dígitos y una letra.
     * @param nombre      Nombre del usuario, no puede ser nulo ni vacío.
     * @param fNacimiento Fecha de nacimiento como número entero en formato DDMMAAAA.
     * @param saldo       Saldo inicial del usuario, expresado como un valor decimal.
     * @throws IllegalArgumentException Si el DNI, el nombre o la fecha de nacimiento no son válidos.
     */
    public Usuario(String dni, String nombre, int fNacimiento, double saldo) throws IllegalArgumentException {
        super(dni, nombre, fNacimiento);
        this.saldo = saldo;
        this.historialViajes = new ArrayList<>();
    }

    /**
     * Getters y setters
     */
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Alquiler> getHistorialViajes() {
        return historialViajes;
    }

    public void setHistorialViajes(ArrayList<Alquiler> historialViajes) {
        this.historialViajes = historialViajes;
    }

    /**
     * Permite a un usuario gestionar las tareas asignadas en el sistema.
     * Este método delega la gestión de las tareas al sistema, pasando la instancia del usuario como parámetro.
     */
    public void gestionarTareas() {
        sistema.gestionarTareasUsuario(this);
    }

    /**
     * Permite al usuario agregar saldo a su cuenta a través del sistema de gestión.
     * Invoca la funcionalidad correspondiente en el sistema para realizar
     * la operación asociada al usuario actual.
     */
    public void agregarSaldo() {
        sistema.agregarSaldo(this);
    }

    /**
     * Agrega un nuevo alquiler al historial de viajes del usuario.
     *
     * @param alquiler el objeto de tipo Alquiler que representa el nuevo viaje a agregar al historial
     */
    public void agregarViaje(Alquiler alquiler) {
        historialViajes.add(alquiler);
    }

    /**
     * Permite al usuario alquilar un vehículo dentro del sistema de gestión.
     *
     * Precondiciones:
     * - El usuario debe tener saldo suficiente para realizar el alquiler.
     *
     * Postcondiciones:
     * - Un nuevo alquiler se registra en el historial del usuario si la operación
     *   se realiza con éxito.
     * - Los recursos correspondientes (por ejemplo, vehículos) se bloquean para
     *   el uso de este alquiler.
     */
    public void alquilarVehiculo() {
        sistema.alquilarVehiculo(historialViajes.size(), this);
    }

    /**
     * Finaliza un alquiler en curso utilizando el sistema de gestión asociado.
     *
     * @param alquiler el alquiler que se desea finalizar.
     */
    public void finalizarAlquiler(Alquiler alquiler) {
        sistema.finalizarAlquiler(alquiler);
    }

    /**
     * Muestra una lista del historial de viajes del usuario.
     * Este método utiliza la funcionalidad del sistema para presentar
     * el historial de viajes del usuario de forma clara y con formato.
     */
    public void verHistorialViajes() {
        sistema.mostrarLista(this.historialViajes, "historial de viajes");
    }

    /**
     * Informa al sistema sobre un problema detectado por el usuario.
     * Este método delega la operación al sistema de gestión para procesar
     * el reporte correspondiente.
     */
    public void reportarProblema() {
        sistema.reportarProblema();
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", dni=" + dni + ", fechaNacimiento=" + fNacimiento + ", saldo=" + saldo + '}';
    }
}
