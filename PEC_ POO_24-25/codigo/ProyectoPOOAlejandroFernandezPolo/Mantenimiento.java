import java.util.ArrayList;

public class Mantenimiento extends Empleado
{
    SistemaGestion sistema = SistemaGestion.getInstancia();
    private ArrayList<Vehiculo> vehiculosAsignados;
    private ArrayList<Vehiculo> vehiculosRecogidos;

    public Mantenimiento(String dni, String nombre, int fNacimiento) throws IllegalArgumentException
    {
        super(dni, nombre, fNacimiento);
        this.vehiculosAsignados = new ArrayList<>();
        this.vehiculosRecogidos = new ArrayList<>();
    }

    public ArrayList<Vehiculo> getVehiculosAsignados() {
        return vehiculosAsignados;
    }

    public ArrayList<Vehiculo> getVehiculosRecogidos() {
        return vehiculosRecogidos;
    }

    public void gestionarTareas(){
        sistema.gestionarTareasMantenimiento(this);
    }

    public void agregarVehiculoAsignado(Vehiculo vehiculo){
        vehiculosAsignados.add(vehiculo);
    }

    public void agregarVehiculoRecogido(Vehiculo vehiculo){
        vehiculosRecogidos.add(vehiculo);
    }

    public void verVehiculosAsignados(){
        System.out.println("Tiene asignados: " + vehiculosAsignados);
    }

    public void recogerVehiculo(){
        sistema.recogerVehiculo(this);
    }

    public void administrarVehiculosRecogidos(){
        sistema.administrarVehiculosRecogidos(this);
    }

    public void devolverVehiculo(Vehiculo vehiculo){
        sistema.devolverVehiculoMant(vehiculo, this);
    }

    @Override
    public String toString() {
        return "Empleado de mantenimiento{" +
                "vehiculosAsignados=" + vehiculosAsignados +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fNacimiento=" + fNacimiento +
                '}';
    }
}
