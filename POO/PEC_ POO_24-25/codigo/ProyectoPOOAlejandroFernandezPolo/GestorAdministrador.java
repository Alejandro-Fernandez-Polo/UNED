import java.time.LocalDateTime;
import java.util.*;

/**
 * La clase GestorAdministrador se encarga de gestionar las operaciones relacionadas con la administración
 * de un sistema de gestión. Proporciona funcionalidades para manejar entidades, usuarios, vehículos, tarifas,
 * mecánicos, empleado de mantenimiento, bases y otros aspectos organizativos y operativos.
 */
public class GestorAdministrador {

    Scanner scanner = new Scanner(System.in);
    SistemaGestion sistema;


    public GestorAdministrador(SistemaGestion sistema) {
        this.sistema = sistema;
    }

    /**
     * Gestiona las tareas administrativas del sistema presentando un menú de opciones
     * disponibles para el administrador y ejecutando las acciones correspondientes según la
     * elección del usuario.
     *
     * @param administrador El objeto administrador que ejecutará las tareas. Este
     * parámetro se utiliza para invocar diversos métodos de gestión, como la gestión
     * de usuarios, vehículos, tarifas, tareas de mantenimiento, etc.
     */
    public void gestionarTareasAdministrador(Administrador administrador) {
        System.out.println("\n-----------------------------------------------------------------------------------------\n");
        System.out.println("Bienvenido " + administrador.getNombre() + " al sistema de gestión de movilidad sostenible.");
        String accion;

        do {
            mostrarMenuAdministrador();
            accion = scanner.nextLine().trim();

            if (accion.isEmpty()) {
                System.out.println("Saliendo...");
            } else {
                switch (accion) {
                    case "1":
                        administrador.gestionarUsuarios();
                        break;
                    case "2":
                        administrador.gestionarVehiculos();
                        break;
                    case "3":
                        administrador.gestionarTarifas();
                        break;
                    case "4":
                        administrador.gestionarMecanicos();
                        break;
                    case "5":
                        administrador.gestionarMantenimientos();
                        break;
                    case "6":
                        administrador.gestionarBases();
                        break;
                    case "7":
                        administrador.promocionarUsuarioPremium();
                        break;
                    case "8":
                        administrador.asignarMantenimiento();
                        break;
                    case "9":
                        administrador.asignarMecanico();
                        break;
                    default:
                        System.out.println("No ha introducido una opción correcta");
                }
            }
        } while (!accion.trim().isEmpty());
    }

    /**
     * Muestra un menú con opciones administrativas disponibles para el administrador.
     * Este menú permite al administrador gestionar diversas entidades y asignaciones,
     * proporcionando funcionalidades como la gestión de usuarios, vehículos y tarifas,
     * y más. Las opciones se enumeran numéricamente e incluyen la función de salida.
     */
    private void mostrarMenuAdministrador() {
        System.out.println("¿Qué desea realizar?");
        System.out.println("1- Gestionar usuarios");
        System.out.println("2- Gestionar vehículos");
        System.out.println("3- Gestionar tarifas");
        System.out.println("4- Gestionar mecánicos");
        System.out.println("5- Gestionar mantenimientos");
        System.out.println("6- Gestionar bases");
        System.out.println("7- Promocionar usuarios");
        System.out.println("8- Asignar vehículo a empleado de mantenimiento");
        System.out.println("9- Asignar vehículo o base a mecánico");
        System.out.println("Pulse Enter para salir\n");
    }

    /**
     * Método genérico para gestionar cualquier tipo de entidad proporcionando
     * diferentes opciones como crear, mostrar, editar o eliminar.
     *
     * @param tipoEntidad Descripción textual del tipo de entidad que se está gestionando (por ejemplo, "Usuario", "Vehículo").
     * @param crear Acción asociada a la creación de una nueva instancia de la entidad.
     * @param mostrar Acción asociada a la visualización de todas las entidades existentes.
     * @param editar Acción asociada a la modificación de una entidad específica.
     * @param eliminar Acción asociada a la eliminación de una entidad específica.
     * @param opciones Array de cadenas que contiene opciones adicionales que pueden ser utilizadas para personalizar
     *                 el comportamiento del menú o del proceso de gestión, aunque en este método no están directamente utilizadas.
     */
    // Método genérico para gestionar cualquier tipo de entidad
    public void gestionarEntidad(String tipoEntidad,
                                 Runnable crear,
                                 Runnable mostrar,
                                 Runnable editar,
                                 Runnable eliminar,
                                 String[] opciones) {
        String accion = "5";
        while (!accion.trim().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
            System.out.println("¿Qué desea hacer con los " + tipoEntidad + "s?" + '\n' +
                    "1- Añadir un " + tipoEntidad + '\n' +
                    "2- Mostrar los " + tipoEntidad + "s" + '\n' +
                    "3- Editar un " + tipoEntidad + '\n' +
                    "4- Eliminar un " + tipoEntidad + '\n' +
                    "Pulse Enter para salir" + '\n');
            accion = scanner.nextLine().trim();
            if (!accion.trim().isEmpty()) {
                switch (accion) {
                    case "1":
                        crear.run();
                        break;
                    case "2":
                        mostrar.run();
                        break;
                    case "3":
                        editar.run();
                        break;
                    case "4":
                        eliminar.run();
                        break;
                    default:
                        System.out.println("No ha introducido una opción correcta");
                }
            } else {
                System.out.println("Saliendo...");
            }
        }
    }

    /**
     * Gestiona las operaciones relacionadas con los usuarios, incluyendo la creación, visualización,
     * edición y eliminación de usuarios.
     * Este método utiliza un mecanismo genérico de gestión de entidades mediante el método gestionarEntidad.
     *
     * Los métodos internos como crearUsuario, mostrarUsuarios, editarUsuario y eliminarUsuario
     * se utilizan para realizar tareas específicas.
     */
    public void gestionarUsuarios() {
        gestionarEntidad("usuario",
                this::crearUsuario,
                this::mostrarUsuarios,
                this::editarUsuario,
                this::eliminarUsuario,
                new String[]{"1", "2", "3", "4"});
    }

    /**
     * Método que permite crear un nuevo usuario estándar dentro del sistema.
     * Este método solicita al administrador que introduzca los datos necesarios,
     * como el DNI, nombre, fecha de nacimiento y saldo inicial del usuario.
     * Si ya existe un usuario con el mismo DNI, el nuevo usuario no será agregado.
     *
     * En su implementación, este método utiliza una estructura genérica que
     * permite la creación de una persona, delegando parte de la construcción
     * de la instancia del usuario estándar a un*/
    public void crearUsuario() {
        crearPersona("usuario", (dni, nombre, fechaNac) -> {
            System.out.println("Introduce el saldo del usuario");
            double saldo = -1;
            try {
                saldo = Double.parseDouble(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Error al añadir el saldo: " + e.getMessage());
            }
            UsuarioEstandar usuario = new UsuarioEstandar(dni, nombre, fechaNac, saldo);
            boolean agregado = false;
            for (Usuario u : sistema.getUsuarios()) {
                if (u.getDni().equalsIgnoreCase(usuario.getDni())) {
                    agregado = true;
                }
            }
            if (!agregado) {
                sistema.getUsuarios().add(usuario);
                System.out.println("Usuario agregado");
            } else {
                System.out.println("Ya existe un usuario con ese dni, el usuario no se ha podido agregar");
            }

        });
    }

    /**
     * Muestra la lista de usuarios del sistema.
     *
     * Si la lista de usuarios está vacía, se mostrará en la consola un mensaje indicando que no hay usuarios en el sistema.
     * De lo contrario, el método delega la tarea de mostrar la lista de usuarios al método `sistema.mostrarLista`.
     */
    public void mostrarUsuarios() {
        if (sistema.getUsuarios().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
            System.out.println("No hay usuarios en el sistema");
            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
        } else {
            sistema.mostrarLista(sistema.getUsuarios(), "usuarios");
        }
    }

    /**
     * Elimina un usuario del sistema.
     *
     * Este método verifica si existen usuarios en el sistema antes de proceder
     * a eliminarlos. Si no hay usuarios registrados, informa que el sistema
     * está vacío. En caso contrario, delega la eliminación al método
     * `eliminarPorDni`, solicitando al usuario el DNI del usuario que desea
     * eliminar. Notifica al usuario sobre el éxito o fracaso de la operación.
     */
    public void eliminarUsuario() {
        if (sistema.getUsuarios().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
            System.out.println("No hay usuarios en el sistema");
            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
        } else {
            eliminarPorDni(sistema.getUsuarios(), u -> u.dni, "usuarios");
        }
    }

    /**
     * Edita los datos de un usuario existente en el sistema.
     *
     * Este método comprueba si el sistema contiene usuarios antes de continuar.
     * Si no hay usuarios presentes, muestra un mensaje correspondiente. De lo contrario,
     * invoca un método de propósito general para gestionar el proceso de edición para
     * los usuarios de la categoría "usuarios".
     */
    public void editarUsuario() {
        if (sistema.getUsuarios().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
            System.out.println("No hay usuarios en el sistema");
            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
        } else {
            editarPersona(sistema.getUsuarios(), "usuarios"); // No necesitamos editor de saldo para mecánicos
        }
    }

    /**
     * Gestiona las operaciones vehiculares, como la creación, visualización, edición y eliminación.
     * Este método funciona como coordinador, permitiendo al usuario realizar operaciones
     * en entidades de vehículos a través de una interfaz con menús.
     *
     * El método invoca un mecanismo genérico de gestión de entidades adaptado a vehículos, al
     * especificar operaciones específicas para crear vehículos, mostrar una lista de vehículos,
     * editar los detalles de los vehículos y eliminarlos.
     *
     * Se valida la entrada del usuario y se activan los submétodos correspondientes
     * según la opción seleccionada.
     */
    public void gestionarVehiculos() {
        gestionarEntidad("vehiculo",
                this::elegirCrearVehiculo,
                this::mostrarVehiculos,
                this::editarVehiculo,
                this::eliminarVehiculo,
                new String[]{"1", "2", "3", "4"});
    }

    /**
     * Permite al usuario seleccionar y crear un tipo específico de vehículo entre las opciones disponibles:
     * Bicicleta, scooter o motocicleta. El usuario introduce una opción numérica correspondiente al
     * tipo de vehículo deseado. Si el usuario introduce una cadena vacía, el proceso finaliza.
     *
     * Este método utiliza un bucle para mostrar el menú repetidamente hasta que el usuario decide salir.
     **/
    public void elegirCrearVehiculo() {
        String accion;
        do {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
            System.out.println("Que tipo de vehículo desea crear, introduzca el número correspondiente:" + '\n' +
                    "1- Bicicleta" + '\n' + "2- Patinete" + '\n' + "3- Moto" + '\n' +
                    "Para salir introduzca una cadena vacía" + '\n');
            accion = scanner.nextLine().trim();
            if (!accion.trim().isEmpty()) {
                switch (accion) {
                    case "1":
                        crearBicicleta();
                        break;
                    case "2":
                        crearPatinete();
                        break;
                    case "3":
                        crearMoto();
                        break;
                    default:
                        System.out.println("No ha introducido una opción correcta");
                }
            } else {
                System.out.println("Saliendo...");
            }
        } while (!accion.trim().isEmpty());

    }

    /**
     * Crea un nuevo patinete y lo registra en el sistema si es posible.
     *
     * Este método verifica primero si el sistema cuenta con bases registradas.
     * Si no hay bases, muestra un mensaje indicando que estas son necesarias
     * para crear un patinete. Si hay bases disponibles, se utiliza
     * el método genérico {@code crearVehiculoGenerico} para gestionar
     * el proceso de creación y registro del patinete.
     */
    public void crearPatinete() {
        if (sistema.getBases().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
            System.out.println("No hay bases registradas en el sistema y son necesarias para crear un patinete");
            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
        }else {
            crearVehiculoGenerico("Patinete", datos -> new Patinete(
                    (int) datos.get("id")
            ));
        }
    }

    /**
     * Método encargado de crear una nueva bicicleta en el sistema.
     *
     * Este método verifica primero si existen bases registradas en el sistema,
     * ya que son necesarias para asignar la bicicleta correctamente. Si no hay
     * bases disponibles, muestra un mensaje informativo y no realiza la creación.
     * En caso contrario, utiliza el método genérico para crear
     */
    public void crearBicicleta() {
        if (sistema.getBases().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
            System.out.println("No hay bases registradas en el sistema y son necesarias para crear una bicicleta");
            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
        }else {
            crearVehiculoGenerico("Bicicleta", datos -> new Bicicleta(
                    (int) datos.get("id")
            ));
        }
    }

    /**
     * Crea una nueva instancia de motocicleta y la añade a la lista de vehículos del sistema.
     * Este método utiliza un proceso genérico de creación de vehículos, especificando "Moto" como tipo de vehículo.
     * y proporcionando un constructor para crear instancias de "Moto".
     *
     * Si ya existe una motocicleta con el mismo ID en el sistema, no se añadirá ninguna nueva motocicleta.
     * Se mostrará un mensaje indicando la duplicación.
     *
     * Si el proceso de creación detecta datos no válidos o faltantes, se mostrarán los mensajes de error correspondientes.
     * */
    public void crearMoto() {
        crearVehiculoGenerico("Moto", datos -> new Moto(
                (int) datos.get("id"),
                (int) datos.get("coordX"),
                (int) datos.get("coordY"),
                (String) datos.get("cilindrada")
        ));
    }

    /**
     * Crea un vehículo a partir del tipo especificado, solicitando al usuario los atributos necesarios
     * según el tipo indicado. Los datos del vehículo se almacenan en un mapa para su posterior uso.
     *
     * @param vehiculo el tipo de vehículo que se desea crear (por ejemplo, "Moto").
     *                 Dependiendo del tipo, se pueden solicitar atributos como coordenadas
     *                 o cilindrada específica.
     *
     * @return un mapa que contiene los atributos del vehículo. Si ocurre un error o
     *         el vehículo no se puede crear (por ejemplo, coordenadas fuera de los límites),
     *         se devuelve null.
     */
    public Map<String, Object> crearVehiculo(String vehiculo) {
        Map<String, Object> datos = new HashMap<>();
        try {
            System.out.println("Introduce el id de " + vehiculo);
            datos.put("id", Integer.parseInt(scanner.nextLine()));
            if (vehiculo.equalsIgnoreCase("Moto")) {
                System.out.println("Introduce la coordenada X de " + vehiculo + " debe estar entre 0 y " + sistema.getLimiteX());
                datos.put("coordX", Integer.parseInt(scanner.nextLine()));
                System.out.println("Introduce la coordenada Y de " + vehiculo + " debe estar entre 0 y " + sistema.getLimiteY());
                datos.put("coordY", Integer.parseInt(scanner.nextLine()));
                System.out.println("Introduce la cilindrada de la moto , puede ser: grande o pequeña");
                datos.put("cilindrada", scanner.nextLine());
                if ((int) datos.get("coordX") < 0 || (int) datos.get("coordX") > sistema.getLimiteX() || (int) datos.get("coordY") < 0 || (int) datos.get("coordY") > sistema.getLimiteY()) {
                    System.out.println("Las coordenadas están fuera de los límites de la ciudad");
                    return null;
                } else {
                    return datos;
                }
            }
            return datos;
        } catch (IllegalArgumentException e) {
            System.err.println("Error al crear el vehiculo: " + e.getMessage());
        }
        return null;
    }

    /**
     * Muestra la lista de vehículos almacenados en el sistema.
     *
     * Si no hay vehículos registrados, se imprime un mensaje indicando
     * que no hay vehículos en el sistema.
     * En caso contrario, se invoca el método {@code mostrarLista} del sistema
     * para listar todos los vehículos disponibles con el encabezado correspondiente.
     */
    public void mostrarVehiculos() {
        if (sistema.getVehiculos().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
            System.out.println("No hay vehiculos en el sistema");
            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
        } else {
            sistema.mostrarLista(sistema.getVehiculos(), "vehiculos");
        }
    }

    /**
     * Elimina un vehículo del sistema según su identificador (ID), excepto en el caso de motocicletas (Moto).
     *
     * Este método verifica si la lista de vehículos está vacía antes de proceder.
     * Si no hay vehículos, se informa al usuario. Si hay vehículos, se muestra una lista de los mismos para
     * que el usuario pueda seleccionar el vehículo a eliminar introduciendo su ID.
     *
     * En caso de que el usuario introduzca un ID que no sea un número entero, se informa de que la entrada es inválida.
     *
     * Excepciones:
     * - NumberFormatException: Si la entrada del usuario*/
    public void eliminarVehiculo() {
        if (sistema.getVehiculos().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
            System.out.println("No hay vehiculos en el sistema");
            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
        } else {
            sistema.mostrarLista(sistema.getVehiculos(), "vehiculos");
            System.out.println("Introduce el id del vehiculo que quiere eliminar");
            try {
                int id = Integer.parseInt(scanner.nextLine());
                Vehiculo eliminar = null;
                for (Vehiculo v : sistema.getVehiculos()) {
                    if (v.id == id && !(v instanceof Moto)) {
                        eliminar = v;
                    }
                }
                if (eliminar != null) {
                    if (eliminar instanceof Patinete) {
                        for (Base b : sistema.getBases()) {
                            for (Vehiculo v : b.getPatinetesDisponibles()) {
                                if (v.id == eliminar.id) {
                                    b.eliminarVehiculoDisponible(v);
                                }
                            }
                        }
                    }
                    if (eliminar instanceof Bicicleta) {
                        for (Base b : sistema.getBases()) {
                            for (Vehiculo v : b.getBicicletasDisponibles()) {
                                if (v.id == eliminar.id) {
                                    b.eliminarVehiculoDisponible(v);
                                }
                            }
                        }
                    }

                }
                boolean eliminado = sistema.getVehiculos().removeIf(v -> v.id == id);

                if (eliminado) {
                    System.out.println("Vehiculo eliminado");
                } else {
                    System.out.println("No se ha encontrado el vehiculo");
                }
            } catch (NumberFormatException e) {
                System.out.println('\n' +"Entrada inválida. " + '\n');
            }
        }
    }

    /**
     * Permite editar los detalles de un vehículo en el sistema.
     *
     * El método valida si existen vehículos registrados en el sistema antes de proceder.
     * Si no existen, muestra un mensaje indicando que no hay vehículos en el sistema.
     * En caso de haberlos, se solicita al usuario el ID del vehículo que desea editar.
     *
     * En caso de un error de entrada como un ID no numérico o no existente, notifica al usuario
     * y no realiza los cambios.
     *
     * Además, el menú de edición permite al usuario salir en cualquier*/
    public void editarVehiculo() {
        if (sistema.getVehiculos().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
            System.out.println("No hay vehiculos en el sistema");
            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
        } else {
            sistema.mostrarLista(sistema.getVehiculos(), "vehiculos");
            System.out.println("Introduce el ID del vehículo que quieres editar:");
            int id;
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("El ID debe ser un número entero.");
                return;
            }

            Vehiculo vehiculoEditar = sistema.getVehiculos().stream()
                    .filter(v -> v.id == id)
                    .findFirst()
                    .orElse(null);

            if (vehiculoEditar == null) {
                System.out.println("No se ha encontrado un vehículo con ese ID.");
            } else {
                String opcion;
                do {
                    mostrarMenuEditarVehiculo(vehiculoEditar);
                    opcion = scanner.nextLine().trim();

                    try {
                        switch (opcion) {
                            case "1" -> editarNivelBateria(vehiculoEditar);
                            case "2" -> editarEstado(vehiculoEditar);
                            case "3" -> editarCoordenada(vehiculoEditar, true);
                            case "4" -> editarCoordenada(vehiculoEditar, false);
                            case "5" -> editarCilindrada(vehiculoEditar);
                            case "" -> System.out.println("Saliendo...");
                            default -> System.out.println("Opción no válida.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println('\n' +"Entrada inválida. Se esperaba un número." + '\n');
                    }
                } while (!opcion.isEmpty());
            }

        }
    }

    /**
     * Muestra el menú para editar las propiedades de un vehículo específico.
     * Este menú varía dependiendo del tipo de vehículo, mostrando opciones adicionales
     * si se trata de una motocicleta.
     *
     * @param vehiculo El vehículo que será editado, incluyendo atributos como nivel de batería, estado,
     *                 y atributos específicos dependiendo del tipo de vehículo (por ejemplo, coordenadas
     *                 o cilindrada en caso de una motocicleta).
     */
    private void mostrarMenuEditarVehiculo(Vehiculo vehiculo) {
        System.out.println('\n' + "-----------------------------------------------------------------------------------------");
        System.out.println("Vehículo encontrado: " + vehiculo);
        System.out.println("¿Qué desea editar?");
        System.out.println("1 - Nivel de batería");
        System.out.println("2 - Estado");
        if (vehiculo instanceof Moto) {
            System.out.println("3 - Coordenada X");
            System.out.println("4 - Coordenada Y");
            System.out.println("5 - Cilindrada");
        }
        System.out.println("Pulse Enter para salir");
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    /**
     * Actualiza el nivel de batería de un vehículo y sincroniza su información
     * en la base correspondiente si pertenece a una lista de patinetes o bicicletas disponibles.
     *
     * @param vehiculo Objeto de la clase Vehiculo cuyo nivel de batería se va a editar.
     */
    private void editarNivelBateria(Vehiculo vehiculo) {
        System.out.println("Nuevo nivel de batería:");
        double nivel = 100;
        try {
            nivel = Double.parseDouble(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Error al añadir el saldo: " + e.getMessage());
        }
        vehiculo.setNivelBateria(nivel);
        if (vehiculo instanceof Patinete) {
            for (Base b : sistema.getBases()) {
                for (Vehiculo v : b.getPatinetesDisponibles()) {
                    if (v.id == vehiculo.id) {
                        b.eliminarVehiculoDisponible(v);
                        b.agregarVehiculoDisponible(vehiculo);
                    }
                }
            }
        }
        if (vehiculo instanceof Bicicleta) {
            for (Base b : sistema.getBases()) {
                for (Vehiculo v : b.getBicicletasDisponibles()) {
                    if (v.id == vehiculo.id) {
                        b.eliminarVehiculoDisponible(v);
                        b.agregarVehiculoDisponible(vehiculo);
                    }
                }
            }
        }
        System.out.println("Nivel de batería actualizado.");
    }

    /**
     * Actualiza el estado de un vehículo y realiza los cambios necesarios en las bases
     * correspondientes para reflejar el nuevo estado.
     *
     * @param vehiculo El vehículo cuyo estado se desea editar. Puede ser una bicicleta,
     *                 un patinete, o cualquier subclase de Vehiculo.
     */
    private void editarEstado(Vehiculo vehiculo) {
        System.out.println("Nuevo estado (disponible, reservado o averiado):");
        String estado = scanner.nextLine().trim().toLowerCase();
        try {
            vehiculo.setEstado(estado);
            if (vehiculo instanceof Patinete) {
                for (Base b : sistema.getBases()) {
                    for (Vehiculo v : b.getPatinetesDisponibles()) {
                        if (v.id == vehiculo.id) {
                            b.eliminarVehiculoDisponible(v);
                            b.agregarVehiculoDisponible(vehiculo);
                        }
                    }
                }
            }
            if (vehiculo instanceof Bicicleta) {
                for (Base b : sistema.getBases()) {
                    for (Vehiculo v : b.getBicicletasDisponibles()) {
                        if (v.id == vehiculo.id) {
                            b.eliminarVehiculoDisponible(v);
                            b.agregarVehiculoDisponible(vehiculo);
                        }
                    }
                }
            }
            System.out.println("Estado actualizado.");
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }

    }

    /**
     * Actualiza la coordenada X o Y de un objeto de tipo Moto si el vehículo proporcionado es una instancia de Moto.
     * Si el vehículo no es del tipo Moto, muestra un mensaje indicando que la operación no es válida.
     * Solicita al usuario un valor numérico para la nueva coordenada.
     *
     * @param vehiculo*/
    private void editarCoordenada(Vehiculo vehiculo, boolean esX) {
        if (vehiculo instanceof Moto moto) {
            System.out.println("Nueva coordenada " + (esX ? "X" : "Y") + ":");
            try {
                int valor = Integer.parseInt(scanner.nextLine());
                if (esX) {
                    moto.setCoordX(valor);
                    System.out.println("Coordenada X actualizada.");
                } else {
                    moto.setCoordY(valor);
                    System.out.println("Coordenada Y actualizada.");
                }
            } catch (NumberFormatException e) {
                System.out.println("El ID debe ser un número entero.");
                return;
            }
        } else {
            System.out.println("Opción no válida para este tipo de vehículo.");
        }
    }

    /**
     * Permite editar la cilindrada de un vehículo en caso de que este sea una moto.
     * La cilindrada puede establecerse como "grande" o "pequeña". Si el vehículo no
     * es una moto, se notifica que la opción no es válida para ese tipo de vehículo.
     *
     * @param vehiculo El objeto de tipo Vehiculo cuya cilindrada se desea actualizar.
     */
    private void editarCilindrada(Vehiculo vehiculo) {
        if (vehiculo instanceof Moto moto) {
            try {
                System.out.println("Nueva cilindrada (grande o pequeña):");
                String cilindrada = scanner.nextLine().trim().toLowerCase();
                moto.setCilindrada(cilindrada);
                System.out.println("Cilindrada actualizada.");
            } catch (IllegalArgumentException e) {
                System.err.println(e);
            }
        } else {
            System.out.println("Opción no válida para este tipo de vehículo.");
        }
    }

    /**
     * Permite al usuario gestionar las tarifas de varios tipos de vehículos mediante un menú en la consola.
     * El método ofrece opciones para modificar, ver o salir del proceso de gestión de tarifas.
     */
    public void gestionarTarifas() {
        String accion = "1";
        while (!accion.trim().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
            System.out.println("La tarifa de que vehiculo quiere modificar" + '\n' +
                    "1- Moto" + '\n' + "2- Bicicleta" + '\n' + "3- Patinete" + '\n' +
                    "4- Ver tarifas" + '\n' +
                    "Pulse Enter para salir" + '\n');
            accion = scanner.nextLine().trim();
            if (!accion.trim().isEmpty()) {
                if (accion.equalsIgnoreCase("1")) {
                    editarTarifas(TipoVehiculos.MOTO);
                } else if (accion.equalsIgnoreCase("2")) {
                    editarTarifas(TipoVehiculos.BICICLETA);
                } else if (accion.equalsIgnoreCase("3")) {
                    editarTarifas(TipoVehiculos.PATINETE);
                } else if (accion.equalsIgnoreCase("4")) {
                    mostrarTarifas();
                } else {
                    System.out.println("No ha introducido una opcion correcta");
                }
            } else {
                System.out.println("Saliendo...");
            }
        }
    }

    /**
     * Permite visualizar y editar las tarifas asociadas a un tipo específico de vehículo.
     * Ofrece opciones para visualizar las tarifas actuales, editar el precio por minuto
     * o modificar el descuento premium de un tipo de vehículo específico.
     *
     * @param tipo El tipo de vehículo para el que se desea gestionar las tarifas. Puede ser
     *             BICICLETA, PATINETE o MOTO.
     */
    public void editarTarifas(TipoVehiculos tipo) {
        String accion = "1";
        while (!accion.trim().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
            System.out.println("Que desea hacer" + '\n' +
                    "1- Ver la tarifa de " + tipo + '\n' + "2- Editar el precio por minuto" + '\n' + "3- Editar el descuento premium" + '\n' +
                    "Pulse Enter para salir" + '\n');
            accion = scanner.nextLine().trim();
            if (!accion.trim().isEmpty()) {
                if (accion.equalsIgnoreCase("1")) {
                    mostrarTarifa(tipo);
                } else if (accion.equalsIgnoreCase("2")) {
                    editarPrecioMinuto(tipo);
                } else if (accion.equalsIgnoreCase("3")) {
                    editarDescuentoPremium(tipo);
                } else {
                    System.out.println("No ha introducido una opcion correcta");
                }
            } else {
                System.out.println("Saliendo...");
            }
        }
    }

    /**
     * Muestra la información de tarifas para un tipo de vehículo específico.
     *
     * @param tipo: el tipo de vehículo para el que se mostrará la tarifa.
     * Puede ser: moto, bicicleta o patinete.
     */
    public void mostrarTarifa(TipoVehiculos tipo) {
        if (tipo == TipoVehiculos.BICICLETA) {
            System.out.println(sistema.tarifaBicicleta.toString());
        } else if (tipo == TipoVehiculos.PATINETE) {
            System.out.println(sistema.tarifaPatinete.toString());
        } else if (tipo == TipoVehiculos.MOTO) {
            System.out.println(sistema.tarifaMoto.toString());
        }
    }

    /**
     * Modifica el precio por minuto de alquiler de un tipo específico de vehículo.
     *
     * @param tipo el tipo de vehículo cuyo precio por minuto se desea editar.
     *             Puede ser uno de los valores de la enumeración TipoVehiculos
     *             como BICICLETA, PATINETE o MOTO.
     */
    public void editarPrecioMinuto(TipoVehiculos tipo) {

        String tipoVehiculo = "";
        int descuento = 0;
        int precio = 0;
        if (tipo == TipoVehiculos.BICICLETA) {
            precio = sistema.tarifaBicicleta.getPrecioPorMinuto();
            precio = mostrarPrecioMinuto("bicicleta", precio);
            if (precio < 0) {
                System.out.println("El precio debe ser positivo");
            } else {
                sistema.tarifaBicicleta.setPrecioPorMinuto(precio);
                System.out.println('\n' + "Precio editado correctamente");
            }
        } else if (tipo == TipoVehiculos.PATINETE) {
            precio = sistema.tarifaPatinete.getPrecioPorMinuto();
            precio = mostrarPrecioMinuto("patinete", precio);
            if (precio < 0) {
                System.out.println("El precio debe ser positivo");
            } else {
                sistema.tarifaPatinete.setPrecioPorMinuto(precio);
                System.out.println('\n' + "Precio editado correctamente");
            }
        } else if (tipo == TipoVehiculos.MOTO) {
            precio = sistema.tarifaMoto.getPrecioPorMinuto();
            precio = mostrarPrecioMinuto("moto", precio);
            if (precio < 0) {
                System.out.println("El precio debe ser positivo");
            } else {
                sistema.tarifaMoto.setPrecioPorMinuto(precio);
                System.out.println('\n' + "Precio editado correctamente");
            }
        }
    }

    /**
     * Muestra el precio actual por minuto para un tipo de vehículo específico.
     * Permite al usuario introducir un nuevo precio y actualizar el valor si la entrada es válida.
     *
     * @param tipoVehiculo El tipo de vehículo para el que se muestra y actualiza el precio por minuto.
     * @param precio El precio actual por minuto para el tipo de vehículo especificado.
     * @return El precio por minuto actualizado si se proporciona una entrada válida; de lo contrario, devuelve -1
     * si la entrada es inválida.
     */
    public int mostrarPrecioMinuto(String tipoVehiculo, int precio) {
        System.out.println('\n' + "El precio por minuto actual de " + tipoVehiculo + " es de: " + precio + "€" + '\n');
        System.out.println("Introduzca el nuevo precio por minuto, solo se aceptan unidades");
        try {
            precio = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println('\n' +"Entrada inválida. " + '\n');
            precio = -1;
        }

        return precio;
    }

    /**
     * Modifica el descuento premium asociado a un tipo de vehículo específico
     * (bicicleta, patinete o moto). Permite asignar un nuevo valor al descuento
     * premium, verificando que este se encuentre en un rango válido entre 0 y 100.
     * Si el valor ingresado no cumple con las restricciones, se notifica al usuario.
     *
     * @param tipo el tipo de vehículo cuyo descuento premium se desea editar.
     *             Puede ser TipoVehiculos.BICICLETA, TipoVehiculos.PATINETE o
     *             TipoVehiculos.MOTO.
     */
    public void editarDescuentoPremium(TipoVehiculos tipo) {
        String tipoVehiculo = "";
        int descuento = 0;
        int descuentoPremium = 0;
        if (tipo == TipoVehiculos.BICICLETA) {
            descuentoPremium = sistema.tarifaBicicleta.getDescuentoPremium();
            descuentoPremium = mostrarDescuentoPremium("bicicleta", descuentoPremium);
            if (descuentoPremium < 0 || descuentoPremium > 100) {
                System.out.println("El descuento premium debe estar entre 0 y 100");
            } else {
                sistema.tarifaBicicleta.setDescuentoPremium(descuentoPremium);
                System.out.println('\n' + "Descuento editado correctamente");
            }
        } else if (tipo == TipoVehiculos.PATINETE) {
            descuentoPremium = sistema.tarifaPatinete.getDescuentoPremium();
            descuentoPremium = mostrarDescuentoPremium("patinete", descuentoPremium);
            if (descuentoPremium < 0 || descuentoPremium > 100) {
                System.out.println("El descuento premium debe estar entre 0 y 100");
            } else {
                sistema.tarifaPatinete.setDescuentoPremium(descuentoPremium);
                System.out.println('\n' + "Descuento editado correctamente");
            }
        } else if (tipo == TipoVehiculos.MOTO) {
            descuentoPremium = sistema.tarifaMoto.getDescuentoPremium();
            descuentoPremium = mostrarDescuentoPremium("moto", descuentoPremium);
            if (descuentoPremium < 0 || descuentoPremium > 100) {
                System.out.println("El descuento premium debe estar entre 0 y 100");
            } else {
                sistema.tarifaMoto.setDescuentoPremium(descuentoPremium);
                System.out.println('\n' + "Descuento editado correctamente");
            }
        }
    }

    /**
     * Muestra el descuento premium actual para el tipo de vehículo especificado y permite al usuario actualizarlo.
     * Si el valor introducido no es válido, el descuento se mantiene y se muestra un mensaje de error.
     *
     * @param tipoVehiculo: el tipo de vehículo para el que se muestra y modifica el descuento premium.
     * @param descuentoPremium: el valor actual del descuento premium para el tipo de vehículo especificado.
     * @return: el valor actualizado del descuento premium o -1 si se proporcionó una entrada no válida.
     */
    public int mostrarDescuentoPremium(String tipoVehiculo, int descuentoPremium) {
        System.out.println('\n' + "El descuento premium actual de " + tipoVehiculo + " es de: " + descuentoPremium + "%" + '\n');
        System.out.println("Introduzca el nuevo descuento premium, solo se aceptan unidades");
        try {
            descuentoPremium = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println('\n' +"Entrada inválida. " + '\n');
            descuentoPremium = -1;
        }

        return descuentoPremium;
    }

    public void mostrarTarifas() {
        System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
        System.out.println(sistema.tarifaBicicleta.toString());
        System.out.println(sistema.tarifaMoto.toString());
        System.out.println(sistema.tarifaPatinete.toString());
    }

    /**
     * Gestiona las operaciones del ciclo de vida de las mecánicas dentro de la aplicación.
     * Este método es un controlador de alto nivel para realizar diversas acciones, como crear,
     * mostrar, editar y eliminar entidades mecánicas.
     */
    public void gestionarMecanicos() {
        gestionarEntidad("mecánico",
                this::crearMecanico,
                this::mostrarMecanicos,
                this::editarMecanico,
                this::eliminarMecanico,
                new String[]{"1", "2", "3", "4"});
    }

    /**
     * Crea un nuevo mecánico y lo añade al sistema si no existe otro mecánico con el mismo DNI.
     * Este método utiliza el método `crearPersona` para solicitar los datos del mecánico y luego
     * realiza una validación con los mecánicos existentes en el sistema según su DNI.
     **/
    public void crearMecanico() {
        crearPersona("mecánico", (dni, nombre, fechaNac) -> {
            Mecanico mecanico = new Mecanico(dni, nombre, fechaNac);
            boolean agregado = false;
            for (Mecanico m : sistema.getMecanicos()) {
                if (m.getDni().equalsIgnoreCase(mecanico.getDni())) {
                    agregado = true;
                }
            }
            if (!agregado) {
                sistema.getMecanicos().add(mecanico);
                System.out.println("Mecanico agregado");
            } else {
                System.out.println("Ya existe un mecanico con ese dni, el mecanico no se ha podido agregar");
            }
        });
    }

    /**
     * Muestra la lista de mecánicas en el sistema.
     *
     * Si el sistema no tiene mecánicas, se mostrará en la consola un mensaje indicando que no hay mecánicas.
     * */
    public void mostrarMecanicos() {
        if (sistema.getMecanicos().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
            System.out.println("No hay mecánicos en el sistema");
            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
        } else {
            sistema.mostrarLista(sistema.getMecanicos(), "mecánicos");
        }
    }

    /**
     * Elimina a un mecánico del sistema según su DNI (Documento Nacional de Identidad).
     * Si no hay ningún mecánico registrado en el sistema, se muestra el mensaje correspondiente.
     * El método primero comprueba si la lista de mecánicos está vacía.
     **/
    public void eliminarMecanico() {
        if (sistema.getMecanicos().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
            System.out.println("No hay mecánicos en el sistema");
            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
        } else {
            eliminarPorDni(sistema.getMecanicos(), m -> m.dni, "mecánico");
        }
    }

    /**
     * Edita los datos de un mecánico en el sistema.
     *
     * Este método comprueba si hay mecánicos registrados en el sistema.
     * Si la lista de mecánicos del sistema está vacía, se muestra un mensaje indicando que no hay mecánicos.
     **/
    public void editarMecanico() {
        if (sistema.getMecanicos().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
            System.out.println("No hay mecánicos en el sistema");
            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
        } else {
            editarPersona(sistema.getMecanicos(), "mecánico");
        }
    }

    /**
     * Asigna un mecánico a un vehículo o base averiado de la lista disponible en el sistema.
     *
     * Este método busca vehículos y bases que necesiten reparación mecánica
     * y verifica que haya mecánicos disponibles en el sistema. Si se cumplen ambas condiciones
     *, ofrece al usuario la opción de seleccionar un vehículo o una base
     * para asignar un mecánico. El usuario puede seleccionar un vehículo o una base específicos por su ID y luego
     * asignarlo a un mecánico ingresando su DNI.
     */
    public void asignarMecanico() {
        ArrayList<Vehiculo> vehiculosNecesitanMecanico = getVehiculosAveriados();
        ArrayList<Base> basesNecesitanMecanico = getBasesAveriadas();
        if (vehiculosNecesitanMecanico.isEmpty() && basesNecesitanMecanico.isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
            System.out.println("No hay vehículos o bases averiadas");
            System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
        } else {
            if (sistema.getMecanicos().isEmpty()) {
                System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
                System.out.println("No hay mecanicos en el sistema");
                System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
            } else {
                System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
                String accion = "8";
                while (!accion.trim().isEmpty()) {
                    System.out.println("Que desea reaparar?" + '\n' +
                            "1- Bases " + '\n' +
                            "2- Vehiculos " + '\n' +
                            "Pulse Enter para salir" + '\n');
                    accion = scanner.nextLine().trim();
                    if (!accion.trim().isEmpty()) {
                        switch (accion) {
                            case "1":
                                if (basesNecesitanMecanico.isEmpty()) {
                                    System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
                                    System.out.println("No hay ninguna base que necesite mecanico" + '\n');
                                } else {
                                    System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
                                    sistema.mostrarLista(basesNecesitanMecanico, "bases que necesitan mecanico");
                                    Base baseSeleccionada = null;
                                    while (baseSeleccionada == null) {
                                        System.out.println("Seleccione el id de la base que necesita mecanico");
                                        int id = -1;
                                        try {
                                            id = Integer.parseInt(scanner.nextLine());
                                        } catch (Exception e) {
                                            System.out.println('\n' +"Entrada inválida. " + '\n');
                                            break;
                                        }

                                        for (Base b : basesNecesitanMecanico) {
                                            if (b.getId() == id) {
                                                baseSeleccionada = b;
                                                break;
                                            }
                                        }
                                        if (baseSeleccionada == null) {
                                            System.out.println('\n' +"No se ha encontrado una base con ese ID."+ '\n');
                                        } else {
                                            sistema.mostrarLista(sistema.getMecanicos(), "mecanicos");
                                            Mecanico mecanicoSeleccionado = null;
                                            String opcionMecanico;
                                            if (mecanicoSeleccionado == null) {
                                                do {
                                                    System.out.println("Introduzca el dni del mecanico al que desea asignar esta base");
                                                    opcionMecanico = scanner.nextLine();
                                                    if (!opcionMecanico.trim().isEmpty()) {
                                                        for (Mecanico m : sistema.getMecanicos()) {
                                                            if (m.getDni().equalsIgnoreCase(opcionMecanico)) {
                                                                mecanicoSeleccionado = m;
                                                            }
                                                        }
                                                        if (mecanicoSeleccionado == null) {
                                                            System.out.println('\n' + "No se ha encontrado mecanico con ese DNI." + '\n');
                                                        } else {
                                                            mecanicoSeleccionado.agregaBaseAsignada(baseSeleccionada);
                                                            System.out.println('\n' +"La " + baseSeleccionada + '\n' + "ha sido asignada a el mecanico " + mecanicoSeleccionado.getNombre() + '\n');
                                                            accion = "";
                                                        }
                                                    }
                                                } while (mecanicoSeleccionado == null && !opcionMecanico.trim().isEmpty());
                                            }
                                        }
                                    }
                                }
                                break;
                            case "2":
                                if (vehiculosNecesitanMecanico.isEmpty()) {
                                    System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
                                    System.out.println("No hay ningun vehiculo que necesite mecanico" + '\n');
                                } else {
                                    System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
                                    sistema.mostrarLista(vehiculosNecesitanMecanico, "vehiculos que necesitan mecanico");
                                    Vehiculo vehiculoSeleccionado = null;
                                    while (vehiculoSeleccionado == null) {
                                        System.out.println("Seleccione el id del vehiculo que necesita mecanico");
                                        int id = -1;
                                        try {
                                            id = Integer.parseInt(scanner.nextLine());
                                        } catch (Exception e) {
                                            System.out.println('\n' +"Entrada inválida. " + '\n');
                                            break;
                                        }
                                        for (Vehiculo v : vehiculosNecesitanMecanico) {
                                            if (v.getId() == id) {
                                                vehiculoSeleccionado = v;
                                                break;
                                            }
                                        }
                                        if (vehiculoSeleccionado == null) {
                                            System.out.println("No se ha encontrado un vehiculo con ese ID.");
                                        } else {
                                            sistema.mostrarLista(sistema.getMecanicos(), "mecanicos");
                                            Mecanico mecanicoSeleccionado = null;
                                            String opcionMecanico;
                                            if (mecanicoSeleccionado == null) {
                                                do {
                                                    System.out.println("Introduzca el dni del mecanico al que desea asignar este vehiculo");
                                                    opcionMecanico = scanner.nextLine();
                                                    if (!opcionMecanico.trim().isEmpty()) {
                                                        for (Mecanico m : sistema.getMecanicos()) {
                                                            if (m.getDni().equalsIgnoreCase(opcionMecanico)) {
                                                                mecanicoSeleccionado = m;
                                                            }
                                                        }
                                                        if (mecanicoSeleccionado == null) {
                                                            System.out.println("No se ha encontrado mecanico con ese DNI.");
                                                        } else {
                                                            mecanicoSeleccionado.agregarVehiculoAsignado(vehiculoSeleccionado);
                                                            System.out.println('\n' + "Vehiculo " + vehiculoSeleccionado + "asignado a mecanico"+ '\n');
                                                        }
                                                    }
                                                } while (mecanicoSeleccionado == null && !opcionMecanico.trim().isEmpty());
                                            }
                                        }
                                    }
                                }
                                break;
                            default:
                                System.out.println("No ha introducido una opción correcta");
                        }
                    } else {
                        System.out.println("Saliendo...");
                    }
                }
            }
        }
    }

    /**
     * Gestiona los procesos de mantenimiento delegando operaciones específicas a otros métodos.
     * Este método gestiona el ciclo de vida de las entidades de mantenimiento, incluyendo su creación, 
     * visualización, edición y eliminación.
     *
     * El método utiliza un sistema genérico de gestión de entidades.
     * */
    public void gestionarMantenimientos() {
        gestionarEntidad("mantenimiento",
                this::crearMantenimiento,
                this::mostrarMantenimientos,
                this::editarMantenimiento,
                this::eliminarMantenimiento,
                new String[]{"1", "2", "3", "4"});
    }
    
    public void crearMantenimiento() {
        crearPersona("mantenimiento", (dni, nombre, fechaNac) -> {
            Mantenimiento mantenimiento = new Mantenimiento(dni, nombre, fechaNac);
            boolean agregado = false;
            for (Mantenimiento m : sistema.getMantenimientos()) {
                if (m.getDni().equalsIgnoreCase(mantenimiento.getDni())) {
                    agregado = true;
                }
            }
            if (!agregado) {
                sistema.getMantenimientos().add(mantenimiento);
                System.out.println("Empleado de mantenimiento agregado");
            } else {
                System.out.println("Ya existe un empleado de mantenimiento con ese dni, el empleado de mantenimineto no se ha podido agregar");
            }
        });
    }
    
    public void mostrarMantenimientos() {
        if (sistema.getMantenimientos().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
            System.out.println("No hay empleados de mantenimiento en el sistema");
            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
        } else {
            sistema.mostrarLista(sistema.getMantenimientos(), "mantenimientos");
        }
    }
    
    public void eliminarMantenimiento() {
        if (sistema.getMantenimientos().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
            System.out.println("No hay empleados de mantenimiento en el sistema");
            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
        } else {
            eliminarPorDni(sistema.getMantenimientos(), m -> m.dni, "mantenimiento");
        }
    }
    
    public void editarMantenimiento() {
        if (sistema.getMantenimientos().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
            System.out.println("No hay empleados de mantenimiento en el sistema");
            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
        } else {
            editarPersona(sistema.getMantenimientos(), "mantenimiento");
        }
    }
    
    public void asignarMantenimiento() {
        ArrayList<Vehiculo> vehiculosNecesitanMantenimiento = getVehiculosAveriados();
        for (Vehiculo v : sistema.getVehiculos()) {
            if (v.getNivelBateria() < 20 && v.getEstado() == EstadosVehiculo.DISPONIBLE) {
                vehiculosNecesitanMantenimiento.add(v);
            }
        }
        if (vehiculosNecesitanMantenimiento.isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
            System.out.println("No hay ningun vehiculo que necesite mantenimento");
            System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
        } else {
            if (sistema.getMantenimientos().isEmpty()) {
                System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
                System.out.println("No hay empleados de mantenimiento en el sistema");
                System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
            } else {
                System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
                sistema.mostrarLista(vehiculosNecesitanMantenimiento, "vehiculos que necesitan mantenimiento");
                Vehiculo vehiculoSeleccionado = null;
                String opcion = ".";
                while (vehiculoSeleccionado == null && !opcion.trim().isEmpty()) {
                    System.out.println("Seleccione el id del vehiculo que necesita mantenimiento");
                    int id = -1;
                    try {
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println('\n' +"Entrada inválida. " + '\n');
                        break;
                    }
                    for (Vehiculo v : vehiculosNecesitanMantenimiento) {
                        if (v.id == id) {
                            vehiculoSeleccionado = v;
                            break;
                        }
                    }
                    if (vehiculoSeleccionado == null) {
                        System.out.println('\n' +"No se ha encontrado un vehiculo con ese ID." + '\n');
                    } else {
                        sistema.mostrarLista(sistema.getMantenimientos(), "trabajadores de mantenimiento");
                        Mantenimiento mantenimientoSeleccionado = null;
                        String opcionMantenimiento;
                        if (mantenimientoSeleccionado == null) {
                            do {
                                System.out.println("Introduzca el dni del trabajador de mantenimiento al que desea asignar este vehiculo");
                                opcionMantenimiento = scanner.nextLine();
                                if (!opcionMantenimiento.trim().isEmpty()) {
                                    for (Mantenimiento m : sistema.getMantenimientos()) {
                                        if (m.getDni().equalsIgnoreCase(opcionMantenimiento)) {
                                            mantenimientoSeleccionado = m;
                                        }
                                    }
                                    if (mantenimientoSeleccionado == null) {
                                        System.out.println('\n' + "No se ha encontrado un trabajador de mantenimiento con ese DNI." + '\n');
                                    } else {
                                        if (vehiculoSeleccionado.getEstado().toString().equalsIgnoreCase("disponible")) {
                                            sistema.getVehiculos().remove(vehiculoSeleccionado);
                                            vehiculoSeleccionado.setEstado("reservado");
                                            sistema.getVehiculos().add(vehiculoSeleccionado);
                                        }
                                        mantenimientoSeleccionado.agregarVehiculoAsignado(vehiculoSeleccionado);
                                        System.out.println('\n' + "Vehiculo " + vehiculoSeleccionado + "asignado al trabajador de mantenimiento" + '\n');
                                    }
                                }
                            } while (mantenimientoSeleccionado == null && !opcionMantenimiento.trim().isEmpty());
                        }
                    }
                }
            }
        }
    }
    
    public void gestionarBases() {
        gestionarEntidad("Base",
                this::crearBase,
                this::mostrarBases,
                this::editarBase,
                this::eliminarBase,
                new String[]{"1", "2", "3", "4"});
    }
    
    public void crearBase() {
        Base base = null;
        try {
            System.out.println("Introduce el id de la base");
            int id = Integer.parseInt(scanner.nextLine());
            for (Base b : sistema.getBases()) {
                if (b.getId() == id) {
                    base = b;
                }
            }
            if (base == null) {
                System.out.println("Introduce la capacidad de la base");
                int capacidad = Integer.parseInt(scanner.nextLine());
                if (capacidad < 0) {
                    System.out.println("La capacidad de la base debe ser un número entero positivo");
                } else {
                    System.out.println("Introduce la coordenada X de la base");
                    int coordX = Integer.parseInt(scanner.nextLine());
                    System.out.println("Introduce la coordenada Y de la base");
                    int coordY = Integer.parseInt(scanner.nextLine());
                    if (coordX < 0 || coordX > sistema.getLimiteX() || coordY < 0 || coordY > sistema.getLimiteY()) {
                        System.out.println("Las coordenadas están fuera de los límites de la ciudad");
                    } else {
                        base = new Base(id, capacidad, coordX, coordY);
                        sistema.getBases().add(base);
                        System.out.println("Base agregada");
                    }
                }
            } else {
                System.out.println("La base con id " + id + " ya existe");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error al crear la base: " + e.getMessage());
        }
    }
    
    public void mostrarBases() {
        if (sistema.getBases().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
            System.out.println("No hay bases en el sistema");
            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
        } else {
            sistema.mostrarLista(sistema.getBases(), "bases");
        }
    }
    
    public void eliminarBase() {
        if (sistema.getBases().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
            System.out.println("No hay bases en el sistema");
            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
        } else {
            eliminarPorId(sistema.getBases(), b -> b.id, "bases");
        }
    }
    
    public void editarBase() {
        if (sistema.getBases().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
            System.out.println("No hay bases en el sistema");
            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
        } else {
            sistema.mostrarLista(sistema.getBases(), "bases");
            System.out.println("Introduce el id de la base que quieres editar");
            int id = -1;
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println('\n' +"Entrada inválida. " + '\n');
            }

            Base baseEditar = null;

            for (Base b : sistema.getBases()) {
                if (b.id == id) {
                    baseEditar = b;
                    break;
                }
            }

            if (baseEditar == null) {
                System.out.println("No se ha encontrado una base con ese ID.");
                return;
            }

            String opcion = "a";
            while (!opcion.trim().isEmpty()) {
                System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
                System.out.println("Base encontrada: " + baseEditar);
                System.out.println("¿Qué desea editar?" + '\n' +
                        "1 - Capacidad" + '\n' +
                        "2 - Coordenada X" + '\n' +
                        "3 - Coordenada Y" + '\n' +
                        "Pulse Enter para salir");
                opcion = scanner.nextLine();

                try {
                    if (opcion.equals("1")) {
                        System.out.println("Nueva capacidad:");
                        int nuevaCapacidad = Integer.parseInt(scanner.nextLine());
                        if (nuevaCapacidad < 0) {
                            System.out.println("La capacidad de la base debe ser un número entero positivo");
                        } else {
                            if (nuevaCapacidad < baseEditar.getVehiculosDisponibles().size()) {
                                System.out.println("No se puede reducir tanto la capacidad de la base, ya que hay " + baseEditar.getVehiculosDisponibles().size() + " vehículos disponibles");
                            } else {
                                baseEditar.setCapacidad(nuevaCapacidad);
                                System.out.println("Capacidad actualizada.");
                            }
                        }
                    } else if (opcion.equals("2")) {
                        System.out.println("Nueva coordenada X:");
                        int nuevaX = Integer.parseInt(scanner.nextLine());
                        if (nuevaX < 0 || nuevaX > sistema.getLimiteX()) {
                            System.out.println("Las coordenadas están fuera de los límites de la ciudad");
                        } else {
                            baseEditar.setCoordX(nuevaX);
                            System.out.println("Coordenada X actualizada.");
                        }
                    } else if (opcion.equals("3")) {
                        System.out.println("Nueva coordenada Y:");
                        int nuevaY = Integer.parseInt(scanner.nextLine());
                        if (nuevaY < 0 || nuevaY > sistema.getLimiteY()) {
                            System.out.println("Las coordenadas están fuera de los límites de la ciudad");
                        } else {
                            baseEditar.setCoordY(nuevaY);
                            System.out.println("Coordenada Y actualizada.");
                        }
                    } else if (!opcion.trim().isEmpty()) {
                        System.out.println("Opción no válida.");
                    } else {
                        System.out.println("Saliendo...");
                    }
                } catch (NumberFormatException e) {
                    System.out.println('\n' +"Entrada inválida. " + '\n');
                }
            }
        }
    }
    
    public ArrayList<Base> getBasesAveriadas() {
        ArrayList<Base> basesAveriadas = new ArrayList<>();
        for (Base b : sistema.getBases()) {
            if (b.getTieneFallosMecanicos()) {
                basesAveriadas.add(b);
            }
        }
        return basesAveriadas;
    }
    
    public void agregarABase(Vehiculo vehiculo) {
        Base base = null;
        while (base == null) {
            sistema.mostrarLista(sistema.getBasesDisponiblesConHueco(), "bases disponibles");
            System.out.println("Introduce el id de la base en la que quieres añadir el vehiculo: ");
            int id = -1;
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println('\n' +"Entrada inválida. " + '\n');
            }
            for (Base b : sistema.getBasesDisponiblesConHueco()) {
                if (b.getId() == id) {
                    base = b;
                }
            }
            if (base != null) {
                base.agregarVehiculoDisponible(vehiculo);
                System.out.println("Vehiculo agregado.");
            } else {
                System.out.println("No se ha encontrado una base con ese id");
            }
        }
    }
    
    public ArrayList<Vehiculo> getVehiculosAveriados() {
        ArrayList<Vehiculo> vehiculosAveriados = new ArrayList();
        ArrayList<Vehiculo> vehiculosEnReparacion = new ArrayList();
        for (Mantenimiento m : sistema.getMantenimientos()) {
            for (Vehiculo v : m.getVehiculosAsignados()) {
                vehiculosEnReparacion.add(v);
            }
        }
        for (Mecanico m : sistema.getMecanicos()) {
            for (Vehiculo v : m.getVehiculosAsignados()) {
                vehiculosEnReparacion.add(v);
            }
        }
        for (Vehiculo v : sistema.getVehiculos()) {
            if (v.getEstado() == EstadosVehiculo.AVERIADO) {
                if (!vehiculosEnReparacion.contains(v)) {
                    vehiculosAveriados.add(v);
                }
            }
        }
        return vehiculosAveriados;
    }
    
    public void promocionarUsuarioPremium() {
        listadoUsuariosPremium();
        if (sistema.getPosiblesUsuariosPremium().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
            System.out.println("No hay usuarios premium que puedan ser promocionados");
            System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
        } else {
            mostrarPosiblesUsuaiosPremium();
            Usuario usuario = null;
            String dni = ".";
            while (usuario == null && !dni.trim().isEmpty()) {
                System.out.println("Introduce el dni del usuario que quieres promover a premium: ");
                dni = scanner.nextLine();
                for (Usuario u : sistema.getPosiblesUsuariosPremium()) {
                    if (u.getDni().equals(dni)) {
                        usuario = u;
                    }
                }
                if (usuario != null) {
                    sistema.getPosiblesUsuariosPremium().remove(usuario);
                    Usuario usuarioPremium = new UsuarioPremium(usuario.getDni(), usuario.getNombre(), usuario.getfNacimiento(), usuario.getSaldo());
                    sistema.getUsuarios().remove(usuario);
                    sistema.getUsuarios().add(usuarioPremium);
                    System.out.println('\n' + "El usuario " + usuarioPremium + " ha sido promovido a premium." + '\n');
                } else {
                    System.out.println('\n' + "No se ha encontrado un usuario con ese dni que pueda ser promovido a premium." + '\n');
                }
            }
        }
    }
    
    public void listadoUsuariosPremium() {
        for (Usuario u : sistema.getUsuarios()) {
            List<Alquiler> historial = u.getHistorialViajes();
            LocalDateTime ahora = LocalDateTime.now();

            // Condición 1: 15 alquileres en el último mes
            int viajesUltimoMes = (int) historial.stream()
                    .filter(a -> a.getFechaHoraFin().isAfter(ahora.minusMonths(1)))
                    .count();

            // Condición 2: 10 alquileres por mes durante 3 meses consecutivos
            boolean cumple3Meses = true;
            for (int i = 1; i <= 3; i++) {
                final int mesOffset = i;
                int viajesMes = (int) historial.stream()
                        .filter(a -> {
                            LocalDateTime inicioMes = ahora.minusMonths(mesOffset);
                            LocalDateTime finMes = ahora.minusMonths(mesOffset - 1);
                            return a.getFechaHoraFin().isAfter(inicioMes) && a.getFechaHoraFin().isBefore(finMes);
                        })
                        .count();
                if (viajesMes < 10) {
                    cumple3Meses = false;
                    break;
                }
            }

            // Condición 3: Usar moto, bicicleta y patinete al menos una vez cada uno durante 6 meses seguidos
            boolean cumple6Meses = true;
            for (int i = 6; i >= 1; i--) {
                final int mesOffset = i;
                Set<String> tiposUsados = new HashSet<>();
                historial.stream()
                        .filter(a -> {
                            LocalDateTime inicioMes = ahora.minusMonths(mesOffset);
                            LocalDateTime finMes = ahora.minusMonths(mesOffset - 1);
                            return a.getFechaHoraFin().isAfter(inicioMes) && a.getFechaHoraFin().isBefore(finMes);
                        })
                        .forEach(a -> tiposUsados.add(a.getVehiculo().getClass().getSimpleName()));
                if (!(tiposUsados.contains("Moto") && tiposUsados.contains("Bicicleta") && tiposUsados.contains("Patinete"))) {
                    cumple6Meses = false;
                }
            }
            // Verificamos si cumple alguna de las tres condiciones
            if (viajesUltimoMes >= 15 || cumple3Meses || cumple6Meses) {
                if (!sistema.getPosiblesUsuariosPremium().contains(u)) {
                    sistema.getPosiblesUsuariosPremium().add(u);
                }
            }
        }
    }
    
    public void mostrarPosiblesUsuaiosPremium() {
        sistema.mostrarLista(sistema.getPosiblesUsuariosPremium(), "posibles usuarios premium");
    }

    // Interfaces funcionales:
    interface VehiculoCreator<T> {
        T crear(Map<String, Object> datos);
    }

    interface contructorPersona {
        void construir(String dni, String nombre, int fechaNacimiento);
    }

    interface DniExtractor<T> {
        String getDni(T obj);
    }

    interface IdExtractor<T> {
        int getId(T obj);
    }
    
    private <T extends Vehiculo> void crearVehiculoGenerico(String tipoVehiculo, VehiculoCreator<T> constructor) {
        Map<String, Object> datos = crearVehiculo(tipoVehiculo);

        if (datos != null) {
            try {
                T nuevo = constructor.crear(datos);
                boolean agregado = false;
                for (Vehiculo v : sistema.getVehiculos()) {
                    if (v.getId() == nuevo.getId()) {
                        agregado = true;
                    }
                }
                if (!agregado) {
                    sistema.getVehiculos().add(nuevo);
                    if (tipoVehiculo.equalsIgnoreCase("Patinete") || tipoVehiculo.equalsIgnoreCase("Bicicleta")) {
                        agregarABase(nuevo);
                    }
                    System.out.println(tipoVehiculo + " creado: " + nuevo);
                } else {
                    System.out.println("Ya existe un vehiculo con ese id");
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Error al crear el/la " + tipoVehiculo + ": " + e.getMessage());
            }
        } else {
            System.out.println("No se pudo crear el/la " + tipoVehiculo + " por error en los datos.");
        }
    }
    
    private <T extends Persona> void editarPersona(List<T> lista, String tipo) {
        sistema.mostrarLista(lista, tipo);
        System.out.println("Introduce el dni del " + tipo + " que quieres editar");
        String dni = scanner.nextLine();
        T personaEditar = null;

        for (T p : lista) {
            if (p.getDni().equalsIgnoreCase(dni)) {
                personaEditar = p;
                break;
            }
        }

        boolean editorSaldo = false;
        if (personaEditar == null) {
            System.out.println("No se ha encontrado un " + tipo + " con ese DNI.");
            return;
        } else {
            if (personaEditar instanceof Usuario) {
                editorSaldo = true;
            }
        }

        String opcion = "a";
        while (!opcion.trim().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
            System.out.println(tipo.substring(0, 1).toUpperCase() + tipo.substring(1) + " encontrado: " + personaEditar);
            System.out.println("¿Qué desea editar?" + '\n' +
                    "1 - Nombre" + '\n' +
                    "2 - Fecha de nacimiento" + '\n' +
                    (editorSaldo ? "3 - Saldo" : "") + '\n' +
                    "Pulse Enter para salir");
            opcion = scanner.nextLine();
            if (!opcion.trim().isEmpty()) {
                try {
                    if (opcion.equalsIgnoreCase("1")) {
                        System.out.println("Introduce el nuevo nombre:");
                        String nuevoNombre = scanner.nextLine();
                        personaEditar.setNombre(nuevoNombre);
                        System.out.println("Nombre actualizado.");
                    } else if (opcion.equalsIgnoreCase("2")) {
                        System.out.println("Introduce la nueva fecha de nacimiento:");
                        try {
                            int nuevaFecha = Integer.parseInt(scanner.nextLine());
                            personaEditar.setfNacimiento(nuevaFecha); // Cambiar la fecha
                            System.out.println("Fecha de nacimiento actualizada.");
                        } catch (NumberFormatException e) {
                            System.err.println("Fecha inválida.");
                        }
                    } else if (opcion.equalsIgnoreCase("3") && editorSaldo) {
                        System.out.println("Introduce el nuevo saldo:");
                        try {
                            Usuario usuarioEditar = (Usuario) personaEditar;
                            double nuevoSaldo = Double.parseDouble(scanner.nextLine());
                            usuarioEditar.setSaldo(nuevoSaldo); // Cambiar el saldo
                            System.out.println("Saldo actualizado.");
                        } catch (NumberFormatException e) {
                            System.err.println("Saldo inválido.");
                        }
                    } else {
                        System.out.println("No ha introducido una opción correcta");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Error al editar el usuario: " + e.getMessage());
                }
            } else {
                System.out.println("Saliendo...");
            }
        }
    }
    
    private void crearPersona(String tipo, contructorPersona builder) {
        System.out.println("Introduce el dni del " + tipo);
        String dni = scanner.nextLine();
        System.out.println("Introduce el nombre del " + tipo);
        String nombre = scanner.nextLine();
        try {
            System.out.println("Introduce la fecha de nacimiento del " + tipo);
            int fechaNacimiento = Integer.parseInt(scanner.nextLine());
            builder.construir(dni, nombre, fechaNacimiento);
        } catch (IllegalArgumentException e) {
            System.err.println("Error al crear el " + tipo + ": " + e.getMessage());
        }
    }
    
    private <T> void eliminarPorDni(List<T> lista, DniExtractor<T> extractor, String tipo) {
        sistema.mostrarLista(lista, tipo);
        System.out.println("Introduce el dni del " + tipo + " que quiere eliminar");
        String dni = scanner.nextLine();
        boolean eliminado = lista.removeIf(e -> extractor.getDni(e).equalsIgnoreCase(dni));
        if (eliminado) {
            System.out.println(tipo.substring(0, 1).toUpperCase() + tipo.substring(1) + " eliminado");
        } else {
            System.out.println("No se ha encontrado el " + tipo + " con DNI: " + dni);
        }
    }
    
    private <T> void eliminarPorId(List<T> lista, IdExtractor<T> extractor, String tipo) {
        sistema.mostrarLista(lista, tipo);
        System.out.println("Introduce el id del " + tipo + " que quiere eliminar");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            boolean eliminado = lista.removeIf(e -> extractor.getId(e) == id);
            if (eliminado) {
                System.out.println(tipo.substring(0, 1).toUpperCase() + tipo.substring(1) + " eliminado");
            } else {
                System.out.println("No se ha encontrado el " + tipo);
            }
        } catch (Exception e) {
            System.out.println('\n' +"Entrada inválida. " + '\n');
        }
    }

}
