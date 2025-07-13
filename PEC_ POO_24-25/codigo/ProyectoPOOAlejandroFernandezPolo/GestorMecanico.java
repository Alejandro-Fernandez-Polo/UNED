import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorMecanico {

    Scanner scanner = new Scanner(System.in);
    SistemaGestion sistema;
    
    public GestorMecanico(SistemaGestion sistema) {
        this.sistema = sistema;
    }

    public void gestionarTareasMecanico(Mecanico mecanico) {
        System.out.println("\n-----------------------------------------------------------------------------------------\n");
        System.out.println("Bienvenido " + mecanico.getNombre());
        String accion;

        do {
            mostrarMenuMecanico();
            accion = scanner.nextLine().trim();

            if (accion.isEmpty()) {
                System.out.println("Saliendo...");
            } else {
                switch (accion) {
                    case "1":
                        mecanico.verVehiculosAsignados();
                        mecanico.verBasesAsignadas();
                        break;
                    case "2":
                        if (mecanico.getVehiculosAsignados().isEmpty()) {
                            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
                            System.out.println("No hay vehiculos asignados");
                            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
                        }else{
                            mecanico.administrarVehiculosAsignados();
                        }
                        break;
                    case "3":
                        if (mecanico.getBasesAsignadas().isEmpty()) {
                            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
                            System.out.println("No hay bases asignadas");
                            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
                        }else{
                            mecanico.administrarBasesAsignadas();
                        }
                        break;
                    default:
                        System.out.println("No ha introducido una opción correcta");
                }
            }
        } while (!accion.trim().isEmpty());
    }

    private void mostrarMenuMecanico() {
        System.out.println("¿Qué desea realizar?");
        System.out.println("1- Ver vehículos y bases asignados");
        System.out.println("2- Administrar vehículos asignados");
        System.out.println("3- Administrar bases asignadas");
        System.out.println("Pulse Enter para salir\n");
    }

    public void administrarVehiculosAsignados(Mecanico mecanico) {
        try {
            sistema.mostrarLista(mecanico.getVehiculosAsignados(), "vehiculos asignados");
            Vehiculo vehiculoAdministrar = null;
            while (vehiculoAdministrar == null) {
                System.out.println("Introduzca el id del vehiculo que desea administrar: ");
                int idVehiculo = Integer.parseInt(scanner.nextLine());
                for (Vehiculo v : mecanico.getVehiculosAsignados()) {
                    if (v.getId() == idVehiculo) {
                        vehiculoAdministrar = v;
                    }
                }
                if (vehiculoAdministrar == null) {
                    System.out.println("No se ha encontrado el vehiculo con id " + idVehiculo);
                } else {
                    if (vehiculoAdministrar.getEstado().toString().equalsIgnoreCase("averiado")) {
                        System.out.println("El vehiculo con id " + vehiculoAdministrar.getId() + " esta averiado ");
                        System.out.println("¿Qué desea hacer con el vehiculo ? " + '\n' +
                                "1- Reparar vehiculo " + '\n' +
                                "Pulse Enter para salir" + '\n');
                        String accion = "8";
                        while (!accion.trim().isEmpty()) {
                            accion = scanner.nextLine();
                            if (!accion.trim().isEmpty()) {
                                if (accion.equals("1")) {
                                    vehiculoAdministrar = mecanico.repararVehiculo(vehiculoAdministrar);
                                    System.out.println("El vehiculo con id " + vehiculoAdministrar.getId() + " ha sido reparado");
                                    accion = "";
                                } else {
                                    System.out.println("No ha introducido una opción correcta");
                                }
                            } else {
                                System.out.println("Saliendo...");
                            }
                        }
                    } else {
                        System.out.println("El vehiculo con id " + vehiculoAdministrar.getId() + " esta listo para su uso");
                        System.out.println("¿Qué desea hacer con el vehiculo ? " + '\n' +
                                "1- Devolver vehiculo " + '\n' +
                                "Pulse Enter para salir" + '\n');
                        String accion = "8";
                        while (!accion.trim().isEmpty()) {
                            accion = scanner.nextLine();
                            if (!accion.trim().isEmpty()) {
                                switch (accion) {
                                    case "1":
                                        mecanico.devolverVehiculo(vehiculoAdministrar);
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
        } catch (NumberFormatException e) {
            System.err.println("Entrada inválida.");
        }
    }

    public void administrarBasesAsignadas(Mecanico mecanico) {
        try {
            if (mecanico.getBasesAsignadas().isEmpty()) {
                System.out.println("No hay bases asignadas");
            } else {
                sistema.mostrarLista(mecanico.getBasesAsignadas(), "bases asignadas");
                Base baseAdministrar = null;
                while (baseAdministrar == null) {
                    System.out.println("Introduzca el id de la base que desea administrar: ");
                    int idBase = Integer.parseInt(scanner.nextLine());
                    for (Base b : mecanico.getBasesAsignadas()) {
                        if (b.getId() == idBase) {
                            baseAdministrar = b;
                        }
                    }
                    if (baseAdministrar == null) {
                        System.out.println("No se ha encontrado el vehiculo con id " + idBase);
                    } else {
                        if (baseAdministrar.getTieneFallosMecanicos()) {
                            System.out.println("La base con id " + baseAdministrar.getId() + " esta averiada ");
                            System.out.println("¿Qué desea hacer con la base ? " + '\n' +
                                    "1- Reparar base " + '\n' +
                                    "Pulse Enter para salir" + '\n');
                            String accion = "8";
                            while (!accion.trim().isEmpty()) {
                                accion = scanner.nextLine();
                                if (!accion.trim().isEmpty()) {
                                    if (accion.equals("1")) {
                                        baseAdministrar.setTieneFallosMecanicos(false);
                                        System.out.println("Introduzca el importe de la factura: ");
                                        int importe = Integer.parseInt(scanner.nextLine());
                                        LocalDateTime fechaActual = LocalDateTime.now();

                                        int fechaHoraInt = Math.toIntExact((int) fechaActual.getMonthValue() * 100000000
                                                + fechaActual.getDayOfMonth() * 1000000
                                                + fechaActual.getHour() * 10000
                                                + fechaActual.getMinute() * 100);
                                        Factura factura = new Factura(fechaHoraInt, mecanico, null, baseAdministrar, importe, fechaActual);
                                        sistema.getFacturas().add(factura);
                                        System.out.println("La base con id " + baseAdministrar.getId() + " ha sido reparado");
                                        accion = "";
                                    } else {
                                        System.out.println("No ha introducido una opción correcta");
                                    }
                                } else {
                                    System.out.println("Saliendo...");
                                }
                            }
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Entrada inválida.");
        }
    }

    public void devolverVehiculoMeca(Vehiculo vehiculo, Mecanico mecanico) {
        try {
            System.out.println("Para devolver el vehiculo introduzca donde quiere dejarlo, primero la coordenada X");
            int coordX = Integer.parseInt(scanner.nextLine());
            System.out.println("Ahora la coordenada Y");
            int coordY = Integer.parseInt(scanner.nextLine());
            if (vehiculo instanceof Moto) {
                ((Moto) vehiculo).setCoordX(coordX);
                ((Moto) vehiculo).setCoordY(coordY);
                mecanico.getVehiculosAsignados().remove(vehiculo);
                System.out.println("Introduzca el importe de la factura: ");
                int importe = Integer.parseInt(scanner.nextLine());
                LocalDateTime fechaActual = LocalDateTime.now();

                int fechaHoraInt = Math.toIntExact((int) fechaActual.getYear() * 10000000000L
                        + fechaActual.getMonthValue() * 100000000
                        + fechaActual.getDayOfMonth() * 1000000
                        + fechaActual.getHour() * 10000
                        + fechaActual.getMinute() * 100);
                Factura factura = new Factura(fechaHoraInt, mecanico, vehiculo, null, importe, fechaActual);
                sistema.getFacturas().add(factura);
                System.out.println("El vehiculo con id " + vehiculo.getId() + " se ha devuelto");
            } else {
                ArrayList<Base> basesdisp = sistema.ordenarBases(sistema.getBases(), coordX, coordY);
                System.out.println("Este es el listado de las bases mas cercanas a la ubicación introducida: ");
                for (Base b : basesdisp) {
                    System.out.println(b);
                }

                Base baseEscogida = null;
                while (baseEscogida == null) {
                    System.out.println('\n' + "Seleccione el id de la base en la que va a dejar el vehiculo: " + '\n');
                    int id = Integer.parseInt(scanner.nextLine());
                    for (Base b : basesdisp) {
                        if (b.getId() == id) {
                            baseEscogida = b;
                        }
                    }
                    if (baseEscogida != null) {
                        baseEscogida.agregarVehiculoDisponible(vehiculo);
                        mecanico.getVehiculosAsignados().remove(vehiculo);
                        System.out.println("Introduzca el importe de la factura: ");
                        int importe = Integer.parseInt(scanner.nextLine());
                        LocalDateTime fechaActual = LocalDateTime.now();

                        int fechaHoraInt = Math.toIntExact((int) fechaActual.getYear() * 10000000000L
                                + fechaActual.getMonthValue() * 100000000
                                + fechaActual.getDayOfMonth() * 1000000
                                + fechaActual.getHour() * 10000
                                + fechaActual.getMinute() * 100);
                        Factura factura = new Factura(fechaHoraInt, mecanico, vehiculo, baseEscogida, importe, fechaActual);
                        sistema.getFacturas().add(factura);
                        System.out.println("El vehiculo con id " + vehiculo.getId() + " se ha devuelto a la base " + baseEscogida.getId());
                    } else {
                        System.out.println("No se ha encontrado la base con id " + id);
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Entrada inválida.");
        }

    }
}
