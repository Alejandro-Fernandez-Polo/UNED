import java.util.ArrayList;

public class Base
{
    // instance variables - replace the example below with your own
    public int id;
    public int capacidad;
    public int coordX;
    public int coordY;
    private ArrayList<Vehiculo> vehiculosDisponibles = new ArrayList();
    public int huecosDisponibles;
    public boolean tieneFallosMecanicos;
    
    public Base(int id, int capacidad, int coordX, int coordY)
    {
        this.id = id;
        this.coordX = coordX;
        this.coordY = coordY;
        this.capacidad = capacidad;
        this.huecosDisponibles = capacidad;
        this.tieneFallosMecanicos = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
        int ocupados = getVehiculosDisponibles().size();
        this.huecosDisponibles = capacidad-ocupados;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public ArrayList<Vehiculo> getVehiculosDisponibles() {
        return vehiculosDisponibles;
    }

    public ArrayList<Vehiculo> getBicicletasDisponibles() {
        ArrayList<Vehiculo> bicicletasDisponibles = new ArrayList();
        for (Vehiculo v : vehiculosDisponibles) {
            try {
                if (v.getClass() == Class.forName("Bicicleta") && v.getEstado() == EstadosVehiculo.DISPONIBLE){
                    bicicletasDisponibles.add(v);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        return bicicletasDisponibles;
    }

    public void agregarVehiculoDisponible(Vehiculo vehiculo) {
        if (this.huecosDisponibles > 0){
            this.vehiculosDisponibles.add(vehiculo);
            this.huecosDisponibles--;
        }else{
            System.out.println("No hay huecos disponibles, nose ha podido agregar el vehiculo");
        }
    }

    public ArrayList<Vehiculo> getPatinetesDisponibles() {
        ArrayList<Vehiculo> patinetesDisponibles = new ArrayList();
        for (Vehiculo v : vehiculosDisponibles) {
            try {
                if (v.getClass() == Class.forName("Patinete") && v.getEstado() == EstadosVehiculo.DISPONIBLE){
                    patinetesDisponibles.add(v);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        return patinetesDisponibles;
    }

    public void eliminarVehiculoDisponible(Vehiculo vehiculo) {
        this.vehiculosDisponibles.remove(vehiculo);
        this.huecosDisponibles++;
    }

    public int getHuecosDisponibles() {
        return huecosDisponibles;
    }

    public void setHuecosDisponibles(int huecosDisponibles) {
        this.huecosDisponibles = huecosDisponibles;
    }

    public boolean getTieneFallosMecanicos() {
        return tieneFallosMecanicos;
    }

    public void setTieneFallosMecanicos(boolean tieneFallosMecanicos) {
        this.tieneFallosMecanicos = tieneFallosMecanicos;
    }

    @Override
    public String toString() {
        return "Base{" +
                "id=" + id +
                ", capacidad=" + capacidad +
                ", coordX=" + coordX +
                ", coordY=" + coordY +
                ", vehiculosDisponibles=" + vehiculosDisponibles +
                ", huecosDisponibles=" + huecosDisponibles +
                ", tieneFallosMecanicos=" + tieneFallosMecanicos +
                '}';
    }
}
