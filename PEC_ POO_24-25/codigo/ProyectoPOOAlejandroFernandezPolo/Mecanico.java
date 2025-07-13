import java.util.ArrayList;

public class Mecanico extends Empleado
{
    SistemaGestion sistema = SistemaGestion.getInstancia();
    private ArrayList<Vehiculo> vehiculosAsignados;
    private ArrayList<Base> basesAsignadas;
    
    public Mecanico(String dni, String nombre, int fNacimiento) throws IllegalArgumentException
    {
        super(dni, nombre, fNacimiento);
        this.vehiculosAsignados = new ArrayList<>();
        this.basesAsignadas = new ArrayList<>();
    }

    public ArrayList<Vehiculo> getVehiculosAsignados() {
        return vehiculosAsignados;
    }

    public ArrayList<Base> getBasesAsignadas() {
        return basesAsignadas;
    }

    public void gestionarTareas(){
        sistema.gestionarTareasMecanico(this);
    }

    public void agregarVehiculoAsignado(Vehiculo vehiculo){
        vehiculosAsignados.add(vehiculo);
    }

    public void agregaBaseAsignada(Base base){
        basesAsignadas.add(base);
    }

    public void verVehiculosAsignados(){
        System.out.println("Tiene asignados los vehiculos : " + vehiculosAsignados);
    }

    public void verBasesAsignadas(){
        System.out.println("Tiene asignados las bases: " + basesAsignadas);
    }

    public void administrarVehiculosAsignados(){
        sistema.administrarVehiculosAsignados(this);
    }

    public void administrarBasesAsignadas(){
        sistema.administrarBasesAsignadas(this);
    }

    public Vehiculo repararVehiculo(Vehiculo vehiculo){
        vehiculo.setEstado("disponible");
        return vehiculo;
    }

    public Vehiculo repararBase(Vehiculo vehiculo){
        vehiculo.setEstado("disponible");
        return vehiculo;
    }

    public void devolverVehiculo(Vehiculo vehiculo){
        sistema.devolverVehiculoMeca(vehiculo, this);
    }

    @Override
    public String toString() {
        return "Mecanico{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fNacimiento=" + fNacimiento +
                ", vehiculosAsignados=" + vehiculosAsignados +
                ", basesAsignadas=" + basesAsignadas +
                '}';
    }
}
