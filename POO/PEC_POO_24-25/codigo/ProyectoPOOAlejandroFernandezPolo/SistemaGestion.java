import java.time.LocalDateTime;
import java.util.*;

/**
 * Write a description of class Administrador here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class SistemaGestion {

    private static SistemaGestion instancia;

    Scanner scanner = new Scanner(System.in);
    // instance variables - replace the example below with your own
    private ArrayList<Usuario> usuarios = new ArrayList();
    private ArrayList<Vehiculo> vehiculos = new ArrayList();
    private ArrayList<Mecanico> mecanicos = new ArrayList();
    private ArrayList<Mantenimiento> mantenimientos = new ArrayList();
    private ArrayList<Base> bases = new ArrayList();
    private ArrayList<Factura> facturas = new ArrayList();
    private ArrayList<Usuario> posiblesUsuariosPremium = new ArrayList();
    static GestorAdministrador gestorAdministrador;
    static GestorUsuario gestorUsuario;
    static GestorMecanico gestorMecanico;
    static GestorMantenimiento gestorMantenimiento;
    private int limiteX;
    private int limiteY;

    //Inicializamos las tarifas por defecto asi el administrador no se vera obligado a crearlas
    Tarifa tarifaMoto = new Tarifa(TipoVehiculos.MOTO, 1, 10);
    Tarifa tarifaBicicleta = new Tarifa(TipoVehiculos.BICICLETA, 1, 10);
    Tarifa tarifaPatinete = new Tarifa(TipoVehiculos.PATINETE, 1, 10);

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public ArrayList<Mecanico> getMecanicos() {
        return mecanicos;
    }

    public ArrayList<Mantenimiento> getMantenimientos() {
        return mantenimientos;
    }

    public ArrayList<Base> getBases() {
        return bases;
    }

    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public ArrayList<Usuario> getPosiblesUsuariosPremium() {
        return posiblesUsuariosPremium;
    }

    //Añadir a mano
    public void anadirUsuario(Usuario usuario) {
        if (!usuarios.contains(usuario)) {
            usuarios.add(usuario);
        }
    }

    public void anadirVehiculo(Vehiculo vehiculo) {
        if (!vehiculos.contains(vehiculo)) {
            vehiculos.add(vehiculo);
        }
    }

    public void anadirMecanico(Mecanico mecanico) {
        mecanicos.add(mecanico);
    }

    public void anadirMantenimiento(Mantenimiento mantenimiento) {
        if (!mantenimientos.contains(mantenimiento)) {
            mantenimientos.add(mantenimiento);
        }
    }

    public void anadirBase(Base base) {
        if (!bases.contains(base)) {
            Base baseID = null;
            for (Base b : bases) {
                if (b.getId() == base.getId()) {
                    baseID = b;
                }
            }
            if (baseID == null) {
                bases.add(base);
            }
        }
    }

    /**
     * Constructor for objects of class SistemaGestion
     */
    public SistemaGestion() {
        limiteX = 1000;
        limiteY = 1000;
    }

    public static SistemaGestion getInstancia() {
        if (instancia == null) {
            instancia = new SistemaGestion();
            gestorAdministrador = new GestorAdministrador(instancia);
            gestorUsuario = new GestorUsuario(instancia);
            gestorMecanico = new GestorMecanico(instancia);
            gestorMantenimiento = new GestorMantenimiento(instancia);
        }
        return instancia;
    }

    public int getLimiteY() {
        return limiteY;
    }

    public int getLimiteX() {
        return limiteX;
    }

    public void gestionarTareasUsuario(Usuario usuario) {
        gestorUsuario.gestionarTareasUsuario(usuario);
    }

    public void agregarSaldo(Usuario usuario) {
        gestorUsuario.agregarSaldo(usuario);
    }

    public void alquilarVehiculo(int idAlquiler, Usuario usuario) {
        gestorUsuario.alquilarVehiculo(idAlquiler, usuario);
    }

    public void finalizarAlquiler(Alquiler alquiler) {
        gestorUsuario.finalizarAlquiler(alquiler);
    }

    public void reportarProblema() {
        gestorUsuario.reportarProblema();
    }

    public void gestionarTareasMecanico(Mecanico mecanico) {
        gestorMecanico.gestionarTareasMecanico(mecanico);
    }

    public void administrarVehiculosAsignados(Mecanico mecanico) {
        gestorMecanico.administrarVehiculosAsignados(mecanico);
    }

    public void administrarBasesAsignadas(Mecanico mecanico) {
        gestorMecanico.administrarBasesAsignadas(mecanico);
    }

    public void devolverVehiculoMeca(Vehiculo vehiculo, Mecanico mecanico) {
        gestorMecanico.devolverVehiculoMeca(vehiculo, mecanico);
    }

    public void gestionarTareasMantenimiento(Mantenimiento mantenimiento) {
        gestorMantenimiento.gestionarTareasMantenimiento(mantenimiento);
    }

    public void recogerVehiculo(Mantenimiento mantenimiento) {
        gestorMantenimiento.recogerVehiculo(mantenimiento);
    }

    public void administrarVehiculosRecogidos(Mantenimiento mantenimiento) {
        gestorMantenimiento.administrarVehiculosRecogidos(mantenimiento);
    }

    public void devolverVehiculoMant(Vehiculo vehiculo, Mantenimiento mantenimiento) {
        gestorMantenimiento.devolverVehiculoMant(vehiculo, mantenimiento);
    }

    public void gestionarTareasAdministrador(Administrador administrador) {
        gestorAdministrador.gestionarTareasAdministrador(administrador);
    }

    public void gestionarUsuarios() {
        gestorAdministrador.gestionarUsuarios();
    }

    public void gestionarVehiculos() {
        gestorAdministrador.gestionarVehiculos();
    }

    public void gestionarTarifas() {
        gestorAdministrador.gestionarTarifas();
    }

    public void gestionarMecanicos() {
        gestorAdministrador.gestionarMecanicos();
    }

    public void asignarMecanico() {
        gestorAdministrador.asignarMecanico();
    }

    public void gestionarMantenimientos() {
        gestorAdministrador.gestionarMantenimientos();
    }

    public void asignarMantenimiento() {
        gestorAdministrador.asignarMantenimiento();
    }

    public void gestionarBases() {
        gestorAdministrador.gestionarBases();
    }

    public void promocionarUsuarioPremium() {
        gestorAdministrador.promocionarUsuarioPremium();
    }

    public ArrayList<Base> getBasesDisponibles() {
        ArrayList<Base> basesDisponibles = new ArrayList();
        for (Base b : bases) {
            if (!b.getTieneFallosMecanicos()) {
                basesDisponibles.add(b);
            }
        }
        return basesDisponibles;
    }

    public ArrayList<Base> getBasesDisponiblesConHueco() {
        ArrayList<Base> getBasesDisponiblesConHueco = new ArrayList();
        for (Base b : getBasesDisponibles()) {
            if (b.getHuecosDisponibles() > 0) {
                getBasesDisponiblesConHueco.add(b);
            }
        }
        return getBasesDisponiblesConHueco;
    }

    public ArrayList<Base> ordenarBases(ArrayList<Base> bases, int coordX, int coordY) {
        for (int i = 0; i < bases.size() - 1; i++) {
            for (int j = 0; j < bases.size() - 1 - i; j++) {
                Base b1 = bases.get(j);
                Base b2 = bases.get(j + 1);

                double dist1 = Math.pow(b1.getCoordX() - coordX, 2) + Math.pow(b1.getCoordY() - coordY, 2);
                double dist2 = Math.pow(b2.getCoordX() - coordX, 2) + Math.pow(b2.getCoordY() - coordY, 2);

                if (dist1 > dist2) {
                    // Intercambiar las bases
                    bases.set(j, b2);
                    bases.set(j + 1, b1);
                }
            }
        }
        return bases;
    }

    public ArrayList<Vehiculo> ordenarMotos(ArrayList<Vehiculo> motos, int coordX, int coordY) {
        for (int i = 0; i < motos.size() - 1; i++) {
            for (int j = 0; j < motos.size() - 1 - i; j++) {
                Moto m1 = (Moto) motos.get(j);
                Moto m2 = (Moto) motos.get(j + 1);

                double dist1 = Math.pow(m1.getCoordX() - coordX, 2) + Math.pow(m1.getCoordY() - coordY, 2);
                double dist2 = Math.pow(m2.getCoordX() - coordX, 2) + Math.pow(m2.getCoordY() - coordY, 2);

                if (dist1 > dist2) {
                    // Intercambiar las bases
                    motos.set(j, m2);
                    motos.set(j + 1, m1);
                }
            }
        }
        return motos;
    }

    public void mostrarLista(List<?> lista, String tipo) {
        System.out.println('\n' + "-----------------------------------------------------------------------------------------");
        System.out.println("Esta es la lista de " + tipo + '\n');
        if (lista.isEmpty()) {
            System.out.println("No hay " + tipo);
        } else {
            for (Object elemento : lista) {
                System.out.println(elemento);
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------" + '\n');
    }

}
