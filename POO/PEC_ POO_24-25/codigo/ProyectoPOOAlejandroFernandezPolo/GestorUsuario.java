import java.util.ArrayList;
import java.util.Scanner;

/**
 * La clase GestorUsuario se encarga de gestionar las tareas relacionadas con el usuario dentro del sistema.
 * Proporciona funcionalidad para gestionar las interacciones del usuario y las operaciones relacionadas,
 * como la gestión de alquileres, la adición de fondos y la notificación de incidencias.
 */
public class GestorUsuario {

    Scanner scanner = new Scanner(System.in);
    SistemaGestion sistema;

    public GestorUsuario(SistemaGestion sistema) {
        this.sistema = sistema;
    }

    /**
     * Gestiona las tareas del usuario en el sistema, permitiendo realizar diversas acciones
     * como agregar saldo, alquilar vehículos, reportar problemas, ver el historial de viajes
     * o finalizar un alquiler activo.
     *
     * @param usuario Instancia del usuario que realizará las acciones en el sistema.
     *                El usuario debe estar registrado previamente en el sistema y su información
     *                debe ser válida para realizar operaciones.
     */
    public void gestionarTareasUsuario(Usuario usuario) {
        String accion;
        do {
            System.out.println("\n-----------------------------------------------------------------------------------------\n");
            System.out.println("Bienvenido " + usuario.getNombre() + ", ¿qué desea hacer?");
            // Buscar alquiler no finalizado
            Alquiler alquilerNoFinalizado = usuario.getHistorialViajes().stream()
                    .filter(a -> a.getImporte() == 0)
                    .findFirst()
                    .orElse(null);
            mostrarMenuUsuario(usuario.getNombre(), alquilerNoFinalizado != null);
            accion = scanner.nextLine().trim();
            if (accion.isEmpty()) {
                System.out.println("Saliendo...");
            } else {
                switch (accion) {
                    case "1":
                        usuario.agregarSaldo();
                        break;
                    case "2":
                        usuario.alquilarVehiculo();
                        break;
                    case "3":
                        usuario.reportarProblema();
                        break;
                    case "4":
                        usuario.verHistorialViajes();
                        break;
                    case "5":
                        if (alquilerNoFinalizado != null) {
                            usuario.finalizarAlquiler(alquilerNoFinalizado);
                        } else {
                            System.out.println("No hay alquiler activo para finalizar.");
                        }
                        break;
                    default:
                        System.out.println("No ha introducido una opción correcta");
                }
            }

        } while (!accion.trim().isEmpty());
    }

    /**
     * Muestra un menú con diferentes opciones para el usuario según la información de su cuenta
     * y el estado del alquiler.
     *
     * @param nombreUsuario: el nombre del usuario para el que se muestra el menú.
     * @param hayAlquilerActivo: un valor booleano que indica si el usuario tiene un alquiler activo.
     */
    private void mostrarMenuUsuario(String nombreUsuario, boolean hayAlquilerActivo) {
        System.out.println("1- Añadir saldo");
        System.out.println("2- Alquilar un vehículo");
        System.out.println("3- Reportar un problema");
        System.out.println("4- Ver historial de viajes");
        if (hayAlquilerActivo) {
            System.out.println("5- Finalizar alquiler activo");
        }
        System.out.println("Pulse Enter para salir\n");
    }

    /**
     * Agrega saldo a la cuenta de un usuario especificado.
     * Solicita al usuario un monto a agregar, valida que sea positivo
     * y lo suma al saldo actual del usuario. Si el monto ingresado no
     * es válido o es negativo, se informa al usuario con un mensaje de error.
     *
     * @param usuario El objeto de tipo Usuario al que se le desea agregar saldo.
     *                Debe ser una instancia válida y no nula.
     */
    public void agregarSaldo(Usuario usuario) {
        System.out.println("Introduzca el saldo que desea añadir: ");
        try {
            double saldo = Double.parseDouble(scanner.nextLine());
            if (saldo < 0) {
                System.out.println("El saldo a agregar debe ser un número entero positivo");
            } else {
                usuario.setSaldo(usuario.getSaldo() + saldo);
                System.out.println("Saldo agregado correctamente.");
                System.out.println(usuario.getNombre() + " tu saldo actual es de " + usuario.getSaldo() + "€");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al añadir el saldo: " + e.getMessage());
        }
    }

    /**
     * Permite a un usuario alquilar un vehículo del sistema, el cual puede ser una bicicleta,
     * un patinete o una motocicleta. El método solicita al usuario seleccionar el tipo
     * de vehículo, la ubicación del vehículo a alquilar, y las coordenadas de destino.
     *
     * @param idAlquiler Identificador único del alquiler que se va a generar.
     * @param usuario Usuario que desea realizar el alquiler del vehículo.
     * @return Un objeto de tipo Alquiler que representa el alquiler realizado, o null si el
     *         alquiler no pudo efectuarse por falta de saldo, vehículos disponibles o por
     *         coordenadas fuera de los límites.
     */
    public Alquiler alquilarVehiculo(int idAlquiler, Usuario usuario) {
        if (sistema.getVehiculos().isEmpty()) {
            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
            System.out.println("No hay vehiculos en el sistema");
            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
        } else {
            Alquiler alquiler = null;
            if (usuario.getSaldo() < 0) {
                System.out.println("No tienes saldo suficiente para alquilar el vehiculo.");
            } else {
                String accion = "1";
                while (!accion.trim().isEmpty() && alquiler == null) {
                    System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
                    System.out.println("¿Qué vehiculo desea alquilar?" + '\n' +
                            "1- Bicicleta " + '\n' +
                            "2- Patinete " + '\n' +
                            "3- Motocicleta " + '\n' +
                            "Pulse Enter para salir" + '\n');
                    accion = scanner.nextLine().trim();
                    switch (accion) {
                        case "1" -> {
                            ArrayList<Base> basesdisp = new ArrayList();
                            for (Base b : sistema.getBases()) {
                                if (!b.getBicicletasDisponibles().isEmpty()) {
                                    basesdisp.add(b);
                                }
                            }
                            if (basesdisp.isEmpty()) {
                                System.out.println('\n' + "-----------------------------------------------------------------------------------------");
                                System.out.println("No hay bicicletas en el sistema");
                                System.out.println("-----------------------------------------------------------------------------------------" + '\n');
                            }
                        }
                        case "2" -> {
                            ArrayList<Base> basesdisp = new ArrayList();
                            for (Base b : sistema.getBasesDisponibles()) {
                                if (!b.getPatinetesDisponibles().isEmpty()) {
                                    basesdisp.add(b);
                                }
                            }
                            if (basesdisp.isEmpty()) {
                                System.out.println('\n' + "-----------------------------------------------------------------------------------------");
                                System.out.println("No hay patinetes en el sistema");
                                System.out.println("-----------------------------------------------------------------------------------------" + '\n');
                            }
                        }
                        case "3" -> {
                            ArrayList<Vehiculo> motosDisp = new ArrayList();
                            for (Vehiculo v : sistema.getVehiculos()) {
                                if (v instanceof Moto) {
                                    motosDisp.add(v);
                                }
                            }
                            if (motosDisp.isEmpty()) {
                                System.out.println('\n' + "-----------------------------------------------------------------------------------------");
                                System.out.println("No hay motos en el sistema");
                                System.out.println("-----------------------------------------------------------------------------------------" + '\n');
                            }
                        }
                    }
                    int coordX = -1;
                    int coordY = -1;
                    int coordXFin = -1;
                    int coordYFin = -1;
                    while (coordX == -1 || coordY == -1 || coordXFin == -1 || coordYFin == -1) {
                        System.out.println("Para encontar un vehiculo cerca de usted introduzca sus coordenadas, primero su coordenada X");
                        try {
                            coordX = Integer.parseInt(scanner.nextLine());
                            System.out.println("Ahora su coordenada Y");
                            coordY = Integer.parseInt(scanner.nextLine());
                            System.out.println("Introduzca a donde se dirige, primero la coordenada X");
                            coordXFin = Integer.parseInt(scanner.nextLine());
                            System.out.println("Ahora la coordenada Y");
                            coordYFin = Integer.parseInt(scanner.nextLine());
                            if (coordXFin < 0 || coordXFin > sistema.getLimiteX() || coordYFin < 0 || coordYFin > sistema.getLimiteY()) {
                                System.out.println("Las coordenadas a las que se dirije están fuera de los límites de la ciudad");
                                return null;
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Entrada inválida.");
                        }
                    }
                    try {
                        switch (accion) {
                            case "1" -> {
                                ArrayList<Base> basesdisp = new ArrayList();
                                for (Base b : sistema.getBases()) {
                                    if (!b.getBicicletasDisponibles().isEmpty()) {
                                        basesdisp.add(b);
                                    }
                                }
                                basesdisp = sistema.ordenarBases(basesdisp, coordX, coordY);
                                System.out.println("Este es el listado de las bases mas cercanas con bicicletas disponibles: ");
                                for (Base b : basesdisp) {
                                    System.out.println(b);
                                }

                                Base baseEscogida = null;
                                while (baseEscogida == null) {
                                    System.out.println('\n' + "Seleccione el id de la base de la que va a alquilar su bici: " + '\n');
                                    int id = Integer.parseInt(scanner.nextLine());
                                    for (Base b : basesdisp) {
                                        if (b.getId() == id) {
                                            baseEscogida = b;
                                        }
                                    }
                                    if (baseEscogida != null) {
                                        Vehiculo biciEscogida = null;
                                        while (biciEscogida == null) {
                                            System.out.println("La base elegida tiene estas bicicletas disponibles, introduzca el id de " +
                                                    "la bicicleta que quiere alquilar: ");
                                            for (Vehiculo bici : baseEscogida.getBicicletasDisponibles()) {
                                                System.out.println(bici);
                                            }
                                            int idBici = Integer.parseInt(scanner.nextLine());
                                            for (Vehiculo v : baseEscogida.getBicicletasDisponibles()) {
                                                if (v.getId() == idBici) {
                                                    biciEscogida = v;
                                                }
                                            }
                                            if (biciEscogida != null) {
                                                alquiler = new Alquiler(idAlquiler, biciEscogida, usuario, baseEscogida, sistema.ordenarBases(sistema.getBasesDisponiblesConHueco(), coordXFin, coordYFin).get(0), 0, 0, 0, 0, sistema.tarifaBicicleta);
                                                baseEscogida.eliminarVehiculoDisponible(biciEscogida);
                                                biciEscogida.setEstado("RESERVADO");
                                                usuario.agregarViaje(alquiler);
                                                System.out.println("Alquiliando bicicleta " + biciEscogida);
                                                break;
                                            } else {
                                                System.out.println("No se ha encontrado la bicicleta con id " + idBici);
                                            }
                                        }

                                    } else {
                                        System.out.println("No se ha encontrado la base con id " + id);
                                    }
                                }

                            }
                            case "2" -> {
                                ArrayList<Base> basesdisp = new ArrayList();
                                for (Base b : sistema.getBasesDisponibles()) {
                                    if (!b.getPatinetesDisponibles().isEmpty()) {
                                        basesdisp.add(b);
                                    }
                                }
                                basesdisp = sistema.ordenarBases(basesdisp, coordX, coordY);
                                System.out.println("Este es el listado de las bases mas cercanas con patinetes disponibles: ");
                                for (Base b : basesdisp) {
                                    System.out.println(b);
                                }

                                Base baseEscogida = null;
                                while (baseEscogida == null) {
                                    System.out.println('\n' + "Seleccione el id de la base de la que va a alquilar su patinete: " + '\n');
                                    int id = Integer.parseInt(scanner.nextLine());
                                    for (Base b : basesdisp) {
                                        if (b.getId() == id) {
                                            baseEscogida = b;
                                        }
                                    }
                                    if (baseEscogida != null) {
                                        Vehiculo patinEscogido = null;
                                        while (patinEscogido == null) {
                                            System.out.println("La base elegida tiene estos patinetes disponibles, introduzca el id de " +
                                                    "el patinete que quiere alquilar: ");
                                            for (Vehiculo patin : baseEscogida.getPatinetesDisponibles()) {
                                                System.out.println(patin);
                                            }
                                            int idPatin = Integer.parseInt(scanner.nextLine());
                                            for (Vehiculo v : baseEscogida.getPatinetesDisponibles()) {
                                                if (v.getId() == idPatin) {
                                                    patinEscogido = v;
                                                }
                                            }
                                            if (patinEscogido != null) {
                                                alquiler = new Alquiler(idAlquiler, patinEscogido, usuario, baseEscogida, sistema.ordenarBases(sistema.getBasesDisponiblesConHueco(), coordXFin, coordYFin).get(0), 0, 0, 0, 0, sistema.tarifaPatinete);
                                                baseEscogida.eliminarVehiculoDisponible(patinEscogido);
                                                patinEscogido.setEstado("RESERVADO");
                                                usuario.agregarViaje(alquiler);
                                                System.out.println("Alquiliando patinete " + patinEscogido);
                                                break;
                                            } else {
                                                System.out.println("No se ha encontrado el patinete con id " + idPatin);
                                            }
                                        }

                                    } else {
                                        System.out.println("No se ha encontrado la base con id " + id);
                                    }
                                }
                            }
                            case "3" -> {
                                ArrayList<Vehiculo> motosDisp = new ArrayList();
                                for (Vehiculo v : sistema.getVehiculos()) {
                                    if (v instanceof Moto && v.getEstado().equals(EstadosVehiculo.DISPONIBLE) && v.getNivelBateria() > 20) {
                                        motosDisp.add(v);
                                    }
                                }
                                if (motosDisp.isEmpty()) {
                                    System.out.println('\n' + "-----------------------------------------------------------------------------------------");
                                    System.out.println("No hay motos disponibles en el sistema");
                                    System.out.println("-----------------------------------------------------------------------------------------" + '\n');
                                    return null;
                                }else {
                                    motosDisp = sistema.ordenarMotos(motosDisp, coordX, coordY);
                                    Vehiculo motoEscogida = null;
                                    while (motoEscogida == null) {
                                        System.out.println("Este es el listado de las motos mas cercanas, introduzca el id de la que desea alquilar: ");
                                        for (Vehiculo m : motosDisp) {
                                            System.out.println(m);
                                        }
                                        int idMoto = Integer.parseInt(scanner.nextLine());
                                        for (Vehiculo v : motosDisp) {
                                            if (v.getId() == idMoto) {
                                                motoEscogida = v;
                                            }
                                        }
                                        if (motoEscogida != null) {
                                            alquiler = new Alquiler(idAlquiler, motoEscogida, usuario, null, null, coordX, coordY, coordXFin, coordYFin, sistema.tarifaPatinete);
                                            motoEscogida.setEstado("RESERVADO");
                                            usuario.agregarViaje(alquiler);
                                            System.out.println("Alquiliando moto " + motoEscogida);
                                            break;
                                        } else {
                                            System.out.println("No se ha encontrado la moto con id " + idMoto);
                                        }
                                    }
                                }
                            }
                            default -> System.out.println("Saliendo...");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Entrada inválida.");
                    }
                }
            }
            return alquiler;
        }
        return null;
    }

    /**
     * Finaliza un proceso de alquiler en curso.
     *
     * @param alquiler El objeto de alquiler que contiene los detalles necesarios del proceso de alquiler,
     * incluyendo el vehículo asociado, el usuario y la información del alquiler.
     */
    public void finalizarAlquiler(Alquiler alquiler) {
        alquiler.finalizarAlquiler();
        Vehiculo vehiculo = alquiler.getVehiculo();
        vehiculo.setEstado("disponible");
        vehiculo.calcularConsumoBateria(alquiler.getTiempoViaje());
        Usuario usuario = alquiler.getUsuario();
        usuario.setSaldo(usuario.getSaldo() - alquiler.getImporte());
        if (vehiculo instanceof Moto) {
            ((Moto) vehiculo).setCoordX(alquiler.getCoordenadasFinX());
            ((Moto) vehiculo).setCoordY(alquiler.getCoordenadasFinY());
        } else {
            alquiler.getBaseFin().agregarVehiculoDisponible(vehiculo);
        }
        System.out.println("Finalizando alquiler...");
        System.out.println("Tu saldo restante es de " + alquiler.getUsuario().getSaldo() + "€");

    }

    /**
     * Este método permite al usuario reportar problemas relacionados con vehículos o bases dentro del sistema.
     * Los usuarios pueden optar por reportar un vehículo o una base con mal funcionamiento.
     *
     * El método también gestiona correctamente las entradas numéricas no válidas y ofrece una opción para salir.
     *
     * Excepciones:
     * - Se gestiona la excepción NumberFormatException si se proporciona una entrada no numérica para los ID de vehículos o bases.
     */
    public void reportarProblema() {
        try {
            String accion = "1";
            while (!accion.trim().isEmpty()) {
                System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
                System.out.println("Quiere reportar un problema, de que se trata:" + '\n' +
                        "1- Vehiculo estropeado " + '\n' +
                        "2- Base estropeada " + '\n' +
                        "Pulse Enter para salir" + '\n');

                accion = scanner.nextLine().trim();
                if (!accion.trim().isEmpty()) {
                    switch (accion) {
                        case "1":
                            if (sistema.getVehiculos().isEmpty()) {
                                System.out.println('\n' + "-----------------------------------------------------------------------------------------");
                                System.out.println("No hay vehiculos en el sistema");
                                System.out.println("-----------------------------------------------------------------------------------------" + '\n');
                            }else{
                                sistema.mostrarLista(sistema.getVehiculos(), "vehiculos");
                                Vehiculo vehiculoReportar = null;
                                while (vehiculoReportar == null) {
                                    System.out.println("Introduzca el id del vehiculo que desea reportar: ");
                                    int idVehiculo = Integer.parseInt(scanner.nextLine());
                                    for (Vehiculo v : sistema.getVehiculos()) {
                                        if (v.getId() == idVehiculo) {
                                            vehiculoReportar = v;
                                        }
                                    }
                                    if (vehiculoReportar == null) {
                                        System.out.println("No se ha encontrado el vehiculo con id " + idVehiculo);
                                    } else {
                                        vehiculoReportar.setEstado("averiado");
                                        System.out.println("El vehiculo con id " + vehiculoReportar.getId() + " ha sido reportado");
                                    }
                                }
                            }
                            break;
                        case "2":
                            if (sistema.getBases().isEmpty()) {
                                System.out.println('\n' + "-----------------------------------------------------------------------------------------");
                                System.out.println("No hay bases en el sistema");
                                System.out.println("-----------------------------------------------------------------------------------------" + '\n');
                            }else{
                                sistema.mostrarLista(sistema.getBases(), "bases");
                                Base baseReportar = null;
                                while (baseReportar == null) {
                                    System.out.println("Introduzca el id de la base que desea reportar: ");
                                    int idBase = Integer.parseInt(scanner.nextLine());
                                    for (Base b : sistema.getBases()) {
                                        if (b.getId() == idBase) {
                                            baseReportar = b;
                                        }
                                    }
                                    if (baseReportar == null) {
                                        System.out.println("No se ha encontrado la base con id " + idBase);
                                    } else {
                                        baseReportar.setTieneFallosMecanicos(true);
                                        System.out.println("La base con id " + baseReportar.getId() + " ha sido reportada");
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
        } catch (NumberFormatException e) {
            System.err.println("Entrada inválida.");
        }
    }
}
