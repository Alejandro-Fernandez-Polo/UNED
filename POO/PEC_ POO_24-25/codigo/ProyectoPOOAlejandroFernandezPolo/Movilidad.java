import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Write a description of class Movilidad here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Movilidad {
    // instance variables - replace the example below with your own
    SistemaGestion sistema = SistemaGestion.getInstancia();
    Scanner scanner = new Scanner(System.in);
    Administrador admin;

    /**
     * Constructor for objects of class Movilidad
     */
    public Movilidad() {
        admin = new Administrador("78153714J", "ALejando", 10101233);
        inicializarEjemplos();

    }

    public void iniciar() {
        System.out.println('\n' + "-----------------------------------------------------------------------------------------" + '\n');
        System.out.println("Bienvenido a la aplicacion de movilidad");
        String accion = "1";
        while (!accion.trim().isEmpty()) {

            System.out.println("Indique el rol que tiene en esta aplicación" + '\n' +
                    "1- Administrador " + '\n' +
                    "2- Usuario " + '\n' +
                    "3- Mecanico " + '\n' +
                    "4- Empleado de mantenimiento " + '\n' +
                    "Pulse Enter para salir" + '\n');

            accion = scanner.nextLine();
            if (!accion.trim().isEmpty()) {
                switch (accion) {
                    case "1":
                        admin.gestionarTareas();
                        break;
                    case "2":
                        sistema.mostrarLista(sistema.getUsuarios(), "usuarios");
                        Usuario usuarioSeleccionado = null;
                        while (usuarioSeleccionado == null) {
                            System.out.println("Introduzca su dni: ");
                            String dni = scanner.nextLine();
                            for (Usuario u : sistema.getUsuarios()) {
                                if (u.getDni().equalsIgnoreCase(dni)) {
                                    usuarioSeleccionado = u;
                                }
                            }
                            if (usuarioSeleccionado == null) {
                                System.out.println("No se ha encontrado un usuario con dni: " + dni);
                            } else {
                                usuarioSeleccionado.gestionarTareas();
                            }
                        }
                        break;
                    case "3":
                        sistema.mostrarLista(sistema.getMecanicos(), "mecanicos");
                        Mecanico mecanicoSeleccionado = null;
                        while (mecanicoSeleccionado == null) {
                            System.out.println("Introduzca su dni: ");
                            String dni = scanner.nextLine();
                            for (Mecanico m : sistema.getMecanicos()) {
                                if (m.getDni().equalsIgnoreCase(dni)) {
                                    mecanicoSeleccionado = m;
                                }
                            }
                            if (mecanicoSeleccionado == null) {
                                System.out.println("No se ha encontrado un mecanico con dni: " + dni);
                            } else {
                                mecanicoSeleccionado.gestionarTareas();
                            }
                        }
                        break;
                    case "4":
                        sistema.mostrarLista(sistema.getMantenimientos(), "mantenimientos");
                        Mantenimiento mantenimientoSeleccionado = null;
                        while (mantenimientoSeleccionado == null) {
                            System.out.println("Introduzca su dni: ");
                            String dni = scanner.nextLine();
                            for (Mantenimiento m : sistema.getMantenimientos()) {
                                if (m.getDni().equalsIgnoreCase(dni)) {
                                    mantenimientoSeleccionado = m;
                                }
                            }
                            if (mantenimientoSeleccionado == null) {
                                System.out.println("No se ha encontrado un empleado de mantenimiento con dni: " + dni);
                            } else {
                                mantenimientoSeleccionado.gestionarTareas();
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

    public void inicializarEjemplos() {
        //Tiempo
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime hace1Meses = ahora.minusMonths(1);
        LocalDateTime hace2Meses = ahora.minusMonths(2);
        LocalDateTime hace3Meses = ahora.minusMonths(3);


        long segundosDesde1Mes = hace1Meses.toEpochSecond(java.time.ZoneOffset.UTC);
        long segundosDesde2Mes = hace2Meses.toEpochSecond(java.time.ZoneOffset.UTC);
        long segundosDesde3Mes = hace3Meses.toEpochSecond(java.time.ZoneOffset.UTC);
        long segundosHasta = ahora.toEpochSecond(java.time.ZoneOffset.UTC);

        //Usuario estandar saldo positivo no puede ser premium saldo positivo
        crearUsuarioEstandar("85080411F", "UsuarioNopremium", 10101233, 100);
        //Usuario estandar saldo negativo no puede ser premium
        crearUsuarioEstandar("37063847Z", "UsuarioNegativo", 10101233, -100);
        //Usuario estandar puede ser premium ha usado 15 vehiculos en el ultimo mes
        Usuario UsuarioPremium1 = crearUsuarioEstandar("34111100F", "UsuarioPremium1", 10101233, 100);
        for (int i = 1; i <= 15; i++) {
            Random rand = new Random();

            int dato1 = rand.nextInt(10);
            int dato2 = rand.nextInt(100);
            int dato3 = rand.nextInt(100);


            Base b = new Base(i, dato1, dato2, dato3);

            long randomSegundos = ThreadLocalRandom.current().nextLong(segundosDesde1Mes, segundosHasta);
            LocalDateTime fechafin = LocalDateTime.ofEpochSecond(randomSegundos, 0, java.time.ZoneOffset.UTC);
            Bicicleta bici = new Bicicleta(i - 100);
            Alquiler alquiler = new Alquiler(i, bici, UsuarioPremium1, new Base(1, 1, 1, 1), new Base(1, 1, 1, 1), 0, 0, 0, 0, new Tarifa(null, 1, 1), fechafin, fechafin);
            UsuarioPremium1.agregarViaje(alquiler);
            UsuarioPremium1.finalizarAlquiler(alquiler);
            UsuarioPremium1.setSaldo(100);
        }

        //Usuario estandar puede ser premium ha usado 3 vehiculos en los ultimos 3 meses
        Usuario UsuarioPremium2 = crearUsuarioEstandar("59145604T", "UsuarioPremium2", 10101233, 10000);
        for (int i = 1; i <= 10; i++) {
            Random rand = new Random();

            int dato1 = rand.nextInt(10);
            int dato2 = rand.nextInt(100);
            int dato3 = rand.nextInt(100);

            Base b = new Base(i, dato1, dato2, dato3);
            // 50% de probabilidad de añadir una bicicleta

            long randomSegundos = ThreadLocalRandom.current().nextLong(segundosDesde1Mes, segundosHasta);
            LocalDateTime fechafin = LocalDateTime.ofEpochSecond(randomSegundos, 0, java.time.ZoneOffset.UTC);
            Bicicleta bici = new Bicicleta(i - 1000);
            Alquiler alquiler = new Alquiler(i, bici, UsuarioPremium2, new Base(1, 1, 1, 1), new Base(1, 1, 1, 1), 0, 0, 0, 0, new Tarifa(null, 1, 1), fechafin, fechafin);
            UsuarioPremium2.agregarViaje(alquiler);
            UsuarioPremium2.finalizarAlquiler(alquiler);

            long randomSegundos2 = ThreadLocalRandom.current().nextLong(segundosDesde2Mes, segundosDesde1Mes);
            LocalDateTime fechafin2 = LocalDateTime.ofEpochSecond(randomSegundos2, 0, java.time.ZoneOffset.UTC);
            Bicicleta bici2 = new Bicicleta(i - 2000);
            Alquiler alquiler2 = new Alquiler(i, bici2, UsuarioPremium2, new Base(1, 1, 1, 1), new Base(1, 1, 1, 1), 0, 0, 0, 0, new Tarifa(null, 1, 1), fechafin2, fechafin2);
            UsuarioPremium2.agregarViaje(alquiler2);
            UsuarioPremium2.finalizarAlquiler(alquiler2);

            long randomSegundos3 = ThreadLocalRandom.current().nextLong(segundosDesde3Mes, segundosDesde2Mes);
            LocalDateTime fechafin3 = LocalDateTime.ofEpochSecond(randomSegundos3, 0, java.time.ZoneOffset.UTC);
            Bicicleta bici3 = new Bicicleta(i - 3000);
            Alquiler alquiler3 = new Alquiler(i, bici3, UsuarioPremium2, new Base(1, 1, 1, 1), new Base(1, 1, 1, 1), 0, 0, 0, 0, new Tarifa(null, 1, 1), fechafin3, fechafin3);
            UsuarioPremium2.agregarViaje(alquiler3);
            UsuarioPremium2.finalizarAlquiler(alquiler3);

            UsuarioPremium2.setSaldo(100);
        }

        //Usuario estandar puede ser premium ha usado los 3 tipos de vehiculos en los ultimos 6 meses
        Usuario UsuarioPremium3 = crearUsuarioEstandar("99991220P", "UsuarioPremium3", 10101233, 10000);
        for (int i = 1; i <= 6; i++) {
            Random rand = new Random();

            int dato1 = rand.nextInt(10);
            int dato2 = rand.nextInt(100);
            int dato3 = rand.nextInt(100);
            int dato4 = rand.nextInt(100);
            int dato5 = rand.nextInt(100);

            Base b = new Base(i, dato1, dato2, dato3);
            // 50% de probabilidad de añadir una bicicleta

            LocalDateTime ahora1 = LocalDateTime.now();
            LocalDateTime hasta = ahora1.minusMonths(i - 1);
            LocalDateTime desde = ahora1.minusMonths(i);

            long segundosDesde = desde.toEpochSecond(java.time.ZoneOffset.UTC);
            long segundosHasta1 = hasta.toEpochSecond(java.time.ZoneOffset.UTC);

            long randomSegundos = ThreadLocalRandom.current().nextLong(segundosDesde, segundosHasta1);
            LocalDateTime fechafin = LocalDateTime.ofEpochSecond(randomSegundos, 0, java.time.ZoneOffset.UTC);
            Bicicleta bici = new Bicicleta(i); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
            UsuarioPremium3.agregarViaje(new Alquiler(i, bici, UsuarioPremium3, new Base(1, 1, 1, 1), new Base(1, 1, 1, 1), 0, 0, 0, 0, new Tarifa(null, 1, 1), fechafin, fechafin));

            Patinete patin = new Patinete(i); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
            UsuarioPremium3.agregarViaje(new Alquiler(i, patin, UsuarioPremium3, new Base(1, 1, 1, 1), new Base(1, 1, 1, 1), 0, 0, 0, 0, new Tarifa(null, 1, 1), fechafin, fechafin));

            Moto moto = new Moto(i, dato4, dato5, "grande");// Usa el mismo ID que la base, o cámbialo si quieres que sea único
            UsuarioPremium3.agregarViaje(new Alquiler(i, moto, UsuarioPremium3, new Base(1, 1, 1, 1), new Base(1, 1, 1, 1), 0, 0, 0, 0, new Tarifa(null, 1, 1), fechafin, fechafin));


            UsuarioPremium3.setSaldo(100);
        }

        //Usuario premium saldo positivo
        crearUsuarioPremium("37185714G", "UsuarioPremium", 10101233, 10000);

        //Mecanico
        Mecanico mecanico1 = new Mecanico("43150989E", "Mecanico1", 10101233);
        sistema.anadirMecanico(mecanico1);
        Mecanico mecanico2 = new Mecanico("55598868W", "Mecanico2", 10101233);
        sistema.anadirMecanico(mecanico2);
        Mecanico mecanico3 = new Mecanico("01840881F", "Mecanico3", 10101233);
        sistema.anadirMecanico(mecanico3);

        //Mantenimiento
        Mantenimiento mantenimiento1 = new Mantenimiento("69108369Q", "mantenimiento1", 10101233);
        sistema.anadirMantenimiento(mantenimiento1);
        Mantenimiento mantenimiento2 = new Mantenimiento("17656376N", "mantenimiento2", 10101233);
        sistema.anadirMantenimiento(mantenimiento2);
        Mantenimiento mantenimiento3 = new Mantenimiento("71335156N", "mantenimiento3", 10101233);
        sistema.anadirMantenimiento(mantenimiento3);

        //Motos
        //Moto grande normal
        Moto moto1 = new Moto(11, 10, 40, "grande"); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
        sistema.anadirVehiculo(moto1);
        //Moto pequeña normal
        Moto moto2 = new Moto(12, 150, 200, "pequeña"); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
        sistema.anadirVehiculo(moto2);
        //Moto pequena sin bateria
        Moto moto3 = new Moto(13, 400, 500, "pequeña"); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
        moto3.setNivelBateria(5);
        sistema.anadirVehiculo(moto3);
        //Moto grande sin bateria
        Moto moto4 = new Moto(14, 700, 100, "pequeña"); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
        moto4.setNivelBateria(5);
        sistema.anadirVehiculo(moto4);
        //Moto grande, bateria < 20%
        Moto moto5 = new Moto(15, 900, 350, "pequeña"); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
        moto5.setNivelBateria(17);
        sistema.anadirVehiculo(moto5);
        //Moto pequeña, bateria < 20%
        Moto moto6 = new Moto(16, 600, 700, "pequeña"); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
        moto6.setNivelBateria(15);
        sistema.anadirVehiculo(moto6);
        //Moto grande averiada
        Moto moto7 = new Moto(17, 430, 740, "pequeña"); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
        moto7.setEstado("averiado");
        sistema.anadirVehiculo(moto7);
        //Moto pequeña averiada
        Moto moto8 = new Moto(18, 20, 300, "pequeña"); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
        moto8.setEstado("averiado");
        sistema.anadirVehiculo(moto8);

        //Patinetes
        //Patinete normal
        Patinete patinete1 = new Patinete(20); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
        sistema.anadirVehiculo(patinete1);
        //Patinete averiado
        Patinete patinete2 = new Patinete(21); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
        patinete2.setEstado("averiado");
        sistema.anadirVehiculo(patinete2);
        //Patinete sin bateria
        Patinete patinete3 = new Patinete(22); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
        patinete3.setNivelBateria(5);
        sistema.anadirVehiculo(patinete3);
        //Patinete bateria < 20%
        Patinete patinete4 = new Patinete(23); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
        patinete4.setNivelBateria(17);
        sistema.anadirVehiculo(patinete4);
        //Patinete normal
        Patinete patinete5 = new Patinete(24); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
        sistema.anadirVehiculo(patinete5);
        //Patinete normal
        Patinete patinete6 = new Patinete(25); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
        sistema.anadirVehiculo(patinete6);
        //Patinete normal
        Patinete patinete7 = new Patinete(26); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
        sistema.anadirVehiculo(patinete7);

        //Bicicletas
        //Bicicleta normal
        Bicicleta bici1 = new Bicicleta(30);
        sistema.anadirVehiculo(bici1);
        //Bicicleta averiada
        Bicicleta bici2 = new Bicicleta(31);
        bici2.setEstado("averiado");
        sistema.anadirVehiculo(bici2);
        //Bicicleta sin bateria
        Bicicleta bici3 = new Bicicleta(32);
        bici3.setNivelBateria(5);
        sistema.anadirVehiculo(bici3);
        //Bicicleta bateria < 20%
        Bicicleta bici4 = new Bicicleta(34); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
        bici4.setNivelBateria(12);
        sistema.anadirVehiculo(bici4);
        //Bicicleta normal
        Bicicleta bici5 = new Bicicleta(35);
        sistema.anadirVehiculo(bici5);
        //Bicicleta normal
        Bicicleta bici6 = new Bicicleta(36);
        sistema.anadirVehiculo(bici6);
        //Bicicleta normal
        Bicicleta bici7 = new Bicicleta(37);
        sistema.anadirVehiculo(bici7);


        //Bases
        //Base averiada sin vehiculos
        Base base1 = new Base(1, 45, 300, 500);
        base1.setTieneFallosMecanicos(true);
        sistema.anadirBase(base1);
        //Base averiada con vehiculos
        Base base2 = new Base(2, 4, 700, 200);
        base2.setTieneFallosMecanicos(true);
        base2.agregarVehiculoDisponible(patinete1);
        base2.agregarVehiculoDisponible(bici1);
        sistema.anadirBase(base2);
        //Base normal sin vehiculos
        Base base3 = new Base(3, 10, 758, 934);
        sistema.anadirBase(base3);
        //Base normal con vehiculos
        Base base4 = new Base(4, 7, 293, 459);
        base4.agregarVehiculoDisponible(patinete3);
        base4.agregarVehiculoDisponible(bici2);
        base4.agregarVehiculoDisponible(patinete2);
        base4.agregarVehiculoDisponible(bici7);
        sistema.anadirBase(base4);
        //Base normal con vehiculos
        Base base5 = new Base(5, 9, 563, 245);
        base5.agregarVehiculoDisponible(patinete5);
        base5.agregarVehiculoDisponible(bici4);
        base4.agregarVehiculoDisponible(patinete6);
        base4.agregarVehiculoDisponible(bici5);
        base4.agregarVehiculoDisponible(patinete7);
        sistema.anadirBase(base5);
        //Base normal con vehiculos llena
        Base base6 = new Base(6, 3, 790, 982);
        base6.agregarVehiculoDisponible(patinete4);
        base6.agregarVehiculoDisponible(bici6);
        base6.agregarVehiculoDisponible(bici3);
        sistema.anadirBase(base6);
    }

    public Usuario crearUsuarioEstandar(String dni, String nombre, int fNacimiento, double saldo) {
        UsuarioEstandar usuario = new UsuarioEstandar(dni, nombre, fNacimiento, saldo);
        sistema.anadirUsuario(usuario);
        return usuario;
    }

    public Usuario crearUsuarioPremium(String dni, String nombre, int fNacimiento, double saldo) {
        UsuarioPremium usuario = new UsuarioPremium(dni, nombre, fNacimiento, saldo);
        sistema.anadirUsuario(usuario);
        return usuario;
    }

    public void mostrarEjemplos() {
        for (int j = 1; j <= 20; j++) {
            String nombre = "Usuario" + j;
            String dni = "a" + j;
            UsuarioEstandar usuarioAleatorio = new UsuarioEstandar(dni, nombre, 11112000, 100);
            for (int i = 1; i <= 20; i++) {
                Random rand = new Random();

                int dato1 = rand.nextInt(10); // Rango de 0 a 99 (ajusta si necesitas otro rango)
                int dato2 = rand.nextInt(100);
                int dato3 = rand.nextInt(100);
                int dato4 = rand.nextInt(100);
                int dato5 = rand.nextInt(100);

                Base b = new Base(i, dato1, dato2, dato3);
                // 50% de probabilidad de añadir una bicicleta

                if (rand.nextBoolean()) {
                    LocalDateTime ahora = LocalDateTime.now();
                    LocalDateTime hace6Meses = ahora.minusMonths(1);

                    long segundosDesde = hace6Meses.toEpochSecond(java.time.ZoneOffset.UTC);
                    long segundosHasta = ahora.toEpochSecond(java.time.ZoneOffset.UTC);

                    long randomSegundos = ThreadLocalRandom.current().nextLong(segundosDesde, segundosHasta);
                    LocalDateTime fechafin = LocalDateTime.ofEpochSecond(randomSegundos, 0, java.time.ZoneOffset.UTC);
                    Bicicleta bici = new Bicicleta(i); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
                    usuarioAleatorio.agregarViaje(new Alquiler(i, bici, usuarioAleatorio, new Base(1, 1, 1, 1), new Base(1, 1, 1, 1), 0, 0, 0, 0, new Tarifa(null, 1, 1), fechafin, fechafin));

                }
                if (rand.nextBoolean()) {
                    LocalDateTime ahora = LocalDateTime.now();
                    LocalDateTime hace6Meses = ahora.minusMonths(3);

                    long segundosDesde = hace6Meses.toEpochSecond(java.time.ZoneOffset.UTC);
                    long segundosHasta = ahora.toEpochSecond(java.time.ZoneOffset.UTC);

                    long randomSegundos = ThreadLocalRandom.current().nextLong(segundosDesde, segundosHasta);
                    LocalDateTime fechafin = LocalDateTime.ofEpochSecond(randomSegundos, 0, java.time.ZoneOffset.UTC);
                    Patinete patin = new Patinete(i); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
                    usuarioAleatorio.agregarViaje(new Alquiler(i, patin, usuarioAleatorio, new Base(1, 1, 1, 1), new Base(1, 1, 1, 1), 0, 0, 0, 0, new Tarifa(null, 1, 1), fechafin, fechafin));

                }
                if (rand.nextBoolean()) {
                    LocalDateTime ahora = LocalDateTime.now();
                    LocalDateTime hace6Meses = ahora.minusMonths(6);

                    long segundosDesde = hace6Meses.toEpochSecond(java.time.ZoneOffset.UTC);
                    long segundosHasta = ahora.toEpochSecond(java.time.ZoneOffset.UTC);

                    long randomSegundos = ThreadLocalRandom.current().nextLong(segundosDesde, segundosHasta);
                    LocalDateTime fechafin = LocalDateTime.ofEpochSecond(randomSegundos, 0, java.time.ZoneOffset.UTC);
                    Moto moto = new Moto(i, dato4, dato5, "grande");// Usa el mismo ID que la base, o cámbialo si quieres que sea único
                    usuarioAleatorio.agregarViaje(new Alquiler(i, moto, usuarioAleatorio, new Base(1, 1, 1, 1), new Base(1, 1, 1, 1), 0, 0, 0, 0, new Tarifa(null, 1, 1), fechafin, fechafin));

                }
                sistema.anadirUsuario(usuarioAleatorio);
            }
//                if (rand.nextBoolean()) {
//                    Patinete patin = new Patinete(i); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
//                    sistema.anadirVehiculo(patin);
//                    b.agregarVehiculoDisponible(patin);
//                }
//                if (rand.nextBoolean()) {
//                    Moto moto = new Moto(i, dato4, dato5, "grande"); // Usa el mismo ID que la base, o cámbialo si quieres que sea único
//                    sistema.anadirVehiculo(moto);
//                }
        }
    }
}
