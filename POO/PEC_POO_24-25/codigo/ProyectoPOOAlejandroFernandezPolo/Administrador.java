/**
 * La clase Administrador representa un empleado con responsabilidades específicas
 * para gestionar diversas áreas dentro del sistema. Esta clase extiende
 * la funcionalidad básica de un empleado, proporcionándole capacidades avanzadas
 * de gestión mediante métodos especializados.
 */
public class Administrador extends Empleado {

    SistemaGestion sistema = SistemaGestion.getInstancia();

    /**
     * Constructor de la clase Administrador que inicializa una nueva instancia con los
     * valores proporcionados. Este constructor llama al constructor de la superclase
     * Empleado para validar e inicializar los atributos básicos.
     *
     * @param dni El DNI completo del administrador. Debe constar de 8 dígitos y una letra válida.
     * @param nombre El nombre del administrador. No puede ser nulo ni estar vacío.
     * @param fNacimiento La fecha de nacimiento del administrador como un entero con el formato DDMMAAAA.
     * @throws IllegalArgumentException Si el DNI no es válido o tiene un formato incorrecto.
     * @throws IllegalArgumentException Si el nombre es nulo o está vacío.
     * @throws IllegalArgumentException Si la fecha de nacimiento no es válida.
     */
    public Administrador(String dni, String nombre, int fNacimiento) throws IllegalArgumentException {
        super(dni, nombre, fNacimiento);
    }

    /**
     * Este método permite a un administrador gestionar tareas relacionadas
     * con su ámbito de responsabilidad dentro del sistema.
     * Al invocar este método, se redirige la implementación al sistema de gestión,
     * el cual ejecuta las acciones correspondientes a nivel administrativo.
     *
     * El método funciona como un intermediario, invocando la funcionalidad
     * del sistema de gestión con el contexto del administrador actual.
     */
    public void gestionarTareas() {
        sistema.gestionarTareasAdministrador(this);
    }

    /**
     * Permite al administrador gestionar usuarios en el sistema.
     * Este método delega la funcionalidad de gestión de usuarios a la capa de gestión del sistema.
     */
    public void gestionarUsuarios() {
        sistema.gestionarUsuarios();
    }

    /**
     * Gestiona los vehículos a través del sistema de gestión. Este método delega la
     * operación al subsistema correspondiente encargado de las funcionalidades
     * relacionadas con la gestión y administración de los vehículos.
     */
    public void gestionarVehiculos() {
        sistema.gestionarVehiculos();
    }

    /**
     * Permite al administrador gestionar las tarifas disponibles dentro del sistema.
     * Este método delega la gestión de tarifas al sistema asociado, permitiendo que
     * se realicen las operaciones necesarias relacionadas con las tarifas establecidas.
     */
    public void gestionarTarifas() {
        sistema.gestionarTarifas();
    }

    /**
     * Este método se encarga de gestionar las mecánicas dentro del sistema.
     * Delega la funcionalidad de gestión al componente de gestión integradadel sistema,
     * lo que permite al administrador supervisar o configurar las operaciones relacionadas con las mecánicas.
     */
    public void gestionarMecanicos() {
        sistema.gestionarMecanicos();
    }

    /**
     * Este método permite al administrador gestionar los procesos de mantenimiento dentro del sistema.
     * Delega la lógica relacionada con el mantenimiento a la capa de gestión del sistema.
     * Este método suele garantizar que las operaciones de mantenimiento se gestionen y
     * supervisen eficientemente, incluyendo acciones como la programación, la asignación
     * de recursos.
     */
    public void gestionarMantenimientos() {
        sistema.gestionarMantenimientos();
    }

    /**
     * Invoca la funcionalidad de gestión de bases del sistema. Este método actúa como
     * un punto de delegación para gestionar las operaciones relacionadas con la gestión
     * de bases dentro del sistema. Los detalles de las operaciones se implementan
     * dentro de la lógica de gestión del sistema.
     */
    public void gestionarBases() {
        sistema.gestionarBases();
    }

    /**
     * Promueve a un usuario a la categoría de usuario premium dentro del sistema.
     * Este método delega la acción a la instancia del sistema de gestión asociada
     * al administrador, encargándose de realizar las operaciones necesarias para
     * actualizar el estado del usuario según las reglas definidas en el sistema.
     */
    public void promocionarUsuarioPremium(){
        sistema.promocionarUsuarioPremium();
    }

    /**
     * Asigna tareas de mantenimiento a las entidades correspondientes dentro del sistema de gestión.
     * Este método es utilizado por la clase Administrador para delegar responsabilidades de mantenimiento.
     */
    public void asignarMantenimiento(){
        sistema.asignarMantenimiento();
    }

    /**
     * Asigna un mecánico para la resolución de problemas o tareas de mantenimiento detectadas
     * en el sistema. Este método delega la acción a una instancia del sistema de gestión, que
     * centraliza las operaciones de asignación de mecánicos a las áreas o vehículos que lo requieran.
     */
    public void asignarMecanico(){
        sistema.asignarMecanico();
    }

}
