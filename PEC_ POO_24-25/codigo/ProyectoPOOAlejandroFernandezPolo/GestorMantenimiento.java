import java.util.ArrayList;
import java.util.Scanner;

public class GestorMantenimiento {

    Scanner scanner = new Scanner(System.in);
    SistemaGestion sistema;

    public GestorMantenimiento(SistemaGestion sistema) {
        this.sistema = sistema;
    }

    public void gestionarTareasMantenimiento(Mantenimiento mantenimiento) {
        System.out.println("\n-----------------------------------------------------------------------------------------\n");
        System.out.println("Bienvenido " + mantenimiento.getNombre());
        String accion;

        do {
            mostrarMenuMantenimiento();
            accion = scanner.nextLine().trim();

            if (accion.isEmpty()) {
                System.out.println("Saliendo...");
            } else {
                switch (accion) {
                    case "1":
                        if (mantenimiento.getVehiculosAsignados().isEmpty()) {
                            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
                            System.out.println("No hay vahiculos asignados");
                            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
                        }else{
                            mantenimiento.verVehiculosAsignados();
                        }
                        break;
                    case "2":
                        if (mantenimiento.getVehiculosAsignados().isEmpty()) {
                            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
                            System.out.println("No hay vahiculos asignados");
                            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
                        }else{
                            mantenimiento.recogerVehiculo();
                        }
                        break;
                    case "3":
                        if (mantenimiento.getVehiculosRecogidos().isEmpty()) {
                            System.out.println('\n' + "-----------------------------------------------------------------------------------------");
                            System.out.println("No hay vahiculos recogidos");
                            System.out.println("-----------------------------------------------------------------------------------------" + '\n');
                        }else{
                            mantenimiento.administrarVehiculosRecogidos();
                        }
                        break;
                    default:
                        System.out.println("No ha introducido una opción correcta");
                }
            }

        } while (!accion.trim().isEmpty());
    }

    private void mostrarMenuMantenimiento() {
        System.out.println("¿Qué desea realizar?");
        System.out.println("1- Ver vehículos asignados");
        System.out.println("2- Recoger vehículos asignados");
        System.out.println("3- Administrar vehículos recogidos");
        System.out.println("Pulse Enter para salir\n");
    }

    public void recogerVehiculo(Mantenimiento mantenimiento) {
        if (mantenimiento.getVehiculosAsignados().isEmpty()) {
            System.out.println("No tiene vehiculos asignados");
        } else {
            mantenimiento.verVehiculosAsignados();
            System.out.println("Introduzca el id del vehiculo que desea recoger: ");
            Vehiculo vehiculoRecoger = null;
            while (vehiculoRecoger == null) {
                int idVehiculo = -1;
                try {
                    idVehiculo = Integer.parseInt(scanner.nextLine());
                } catch (IllegalArgumentException e) {
                    System.out.println("Entrada invalida.");
                }

                for (Vehiculo v : sistema.getVehiculos()) {
                    if (v.getId() == idVehiculo) {
                        vehiculoRecoger = v;
                    }
                }
                if (vehiculoRecoger == null) {
                    System.out.println("No se ha encontrado el vehiculo con id " + idVehiculo);
                } else {

                    if (vehiculoRecoger instanceof Moto) {
                        ((Moto) vehiculoRecoger).setCoordX(-1);
                        ((Moto) vehiculoRecoger).setCoordY(-1);
                        mantenimiento.getVehiculosAsignados().remove(vehiculoRecoger);
                        mantenimiento.agregarVehiculoRecogido(vehiculoRecoger);
                        System.out.println("El vehiculo con id " + vehiculoRecoger.getId() + " se ha recogido");
                    } else {
                        Base base = null;
                        for (Base b : sistema.getBases()) {
                            if (b.getVehiculosDisponibles().contains(vehiculoRecoger)) {
                                base = b;
                            }
                        }
                        if (base != null) {
                            base.eliminarVehiculoDisponible(vehiculoRecoger);
                            mantenimiento.getVehiculosAsignados().remove(vehiculoRecoger);
                            mantenimiento.agregarVehiculoRecogido(vehiculoRecoger);
                            System.out.println("El vehiculo con id " + vehiculoRecoger.getId() + " se ha recogido en la base con id " + base.getId());
                        } else {
//                            System.out.println("El vehiculo con id " + vehiculoRecoger.getId() + " no se encuentra en la base " + base);
                            System.out.println("No se ha podido recoger el veiculo");
                        }
                    }
                }
            }
        }

    }

    public void administrarVehiculosRecogidos(Mantenimiento mantenimiento) {
        sistema.mostrarLista(mantenimiento.getVehiculosRecogidos(), "vehiculos recogidos");
        Vehiculo vehiculoAdministrar = null;
        while (vehiculoAdministrar == null) {
            System.out.println("Introduzca el id del vehiculo que desea administrar: ");
            int idVehiculo = -1;
            try {
                idVehiculo = Integer.parseInt(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Entrada invalida.");
            }
            for (Vehiculo v : mantenimiento.getVehiculosRecogidos()) {
                if (v.getId() == idVehiculo) {
                    vehiculoAdministrar = v;
                }
            }
            if (vehiculoAdministrar == null) {
                System.out.println("No se ha encontrado el vehiculo con id " + idVehiculo);
            } else {
                if (vehiculoAdministrar.getEstado().toString().equalsIgnoreCase("reservado")) {
                    System.out.println("El vehiculo con id " + vehiculoAdministrar.getId() + " tiene una bateria de " + vehiculoAdministrar.getNivelBateria() + "%");
                    System.out.println("¿Qué desea hacer con el vehiculo ? " + '\n' +
                            "1- Recargar su bateria " + '\n' +
                            "Pulse Enter para salir" + '\n');
                    String accion = "8";
                    while (!accion.trim().isEmpty()) {
                        accion = scanner.nextLine();
                        if (!accion.trim().isEmpty()) {
                            if (accion.equals("1")) {
                                vehiculoAdministrar.setNivelBateria(100);
                                vehiculoAdministrar.setEstado("disponible");
                                System.out.println("El vehiculo con id " + vehiculoAdministrar.getId() + " ha recargado su bateria al " + vehiculoAdministrar.getNivelBateria() + "%");
                            } else {
                                System.out.println("No ha introducido una opción correcta");
                            }
                        } else {
                            System.out.println("Saliendo...");
                        }
                    }
                } else if (vehiculoAdministrar.getEstado().toString().equalsIgnoreCase("averiado")) {
                    System.out.println("El vehiculo con id " + vehiculoAdministrar.getId() + " esta averiado ");
                    System.out.println("¿Qué desea hacer con el vehiculo ? " + '\n' +
                            "1- Dejarlo en manos de un mecanico " + '\n' +
                            "Pulse Enter para salir" + '\n');
                    String accion = "8";
                    while (!accion.trim().isEmpty()) {
                        accion = scanner.nextLine();
                        if (!accion.trim().isEmpty()) {
                            if (accion.equals("1")) {
                                mantenimiento.getVehiculosRecogidos().remove(vehiculoAdministrar);
                                accion = "";
                                System.out.println("El vehiculo con id " + vehiculoAdministrar.getId() + " se ha dejado en el taller");
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
                            if (accion.equals("1")) {
                                mantenimiento.devolverVehiculo(vehiculoAdministrar);
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

    public void devolverVehiculoMant(Vehiculo vehiculo, Mantenimiento mantenimiento) {
        try {
            System.out.println("Para devolver el vehiculo introduzca donde quiere dejarlo, primero la coordenada X");
            int coordX = Integer.parseInt(scanner.nextLine());
            System.out.println("Ahora la coordenada Y");
            int coordY = Integer.parseInt(scanner.nextLine());
            if (vehiculo instanceof Moto) {
                ((Moto) vehiculo).setCoordX(coordX);
                ((Moto) vehiculo).setCoordY(coordY);
                mantenimiento.getVehiculosRecogidos().remove(vehiculo);
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
                        mantenimiento.getVehiculosRecogidos().remove(vehiculo);
                        System.out.println("El vehiculo con id " + vehiculo.getId() + " se ha devuelto a la base " + baseEscogida.getId());
                    } else {
                        System.out.println("No se ha encontrado la base con id " + id);
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Entrada invalida.");
        }
    }
}
