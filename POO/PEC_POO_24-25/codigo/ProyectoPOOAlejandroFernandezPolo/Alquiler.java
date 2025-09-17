import java.time.Duration;
import java.time.LocalDateTime;

public class Alquiler
{
    // instance variables - replace the example below with your own
    private int idAlquiler;
    private Vehiculo vehiculo;
    private Usuario usuario;
    private Base baseInicio;
    private Base baseFin;
    private int coordenadasInicioX;
    private int coordenadasInicioY;
    private int coordenadasFinX;
    private int coordenadasFinY;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private int tiempoViaje;
    private Tarifa tarifa;
    private double importe;

    public Alquiler(int idAlquiler, Vehiculo vehiculo, Usuario usuario, Base baseInicio, Base
            baseFin, int coordenadasInicioX, int coordenadasInicioY, int coordenadasFinX, int coordenadasFinY, Tarifa tarifa, LocalDateTime fechaHoraInicio,LocalDateTime fechaHoraFin)
    {
        this.idAlquiler = idAlquiler;
        this.vehiculo = vehiculo;
        this.usuario = usuario;
        this.baseInicio = baseInicio;
        this.baseFin = baseFin;
        if (vehiculo instanceof Moto) {
            this.coordenadasInicioX = coordenadasInicioX;
            this.coordenadasInicioY = coordenadasInicioY;
            this.coordenadasFinX = coordenadasFinX;
            this.coordenadasFinY = coordenadasFinY;
        }else {
            this.coordenadasInicioX = baseInicio.getCoordX();
            this.coordenadasInicioY = baseInicio.getCoordY();
            this.coordenadasFinX = baseFin.getCoordX();
            this.coordenadasFinY = baseFin.getCoordY();
        }
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.tarifa = tarifa;
        this.importe = 0;
    }

    public Alquiler(int idAlquiler, Vehiculo vehiculo, Usuario usuario, Base baseInicio, Base
            baseFin, int coordenadasInicioX, int coordenadasInicioY, int coordenadasFinX, int coordenadasFinY, Tarifa tarifa)
    {
        this.idAlquiler = idAlquiler;
        this.vehiculo = vehiculo;
        this.usuario = usuario;
        this.baseInicio = baseInicio;
        this.baseFin = baseFin;
        if (vehiculo instanceof Moto) {
            this.coordenadasInicioX = coordenadasInicioX;
            this.coordenadasInicioY = coordenadasInicioY;
            this.coordenadasFinX = coordenadasFinX;
            this.coordenadasFinY = coordenadasFinY;
        }else {
            this.coordenadasInicioX = baseInicio.getCoordX();
            this.coordenadasInicioY = baseInicio.getCoordY();
            this.coordenadasFinX = baseFin.getCoordX();
            this.coordenadasFinY = baseFin.getCoordY();
        }
        this.fechaHoraInicio = LocalDateTime.now();
        this.tarifa = tarifa;
        this.importe = 0;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Base getBaseInicio() {
        return baseInicio;
    }

    public Base getBaseFin() {
        return baseFin;
    }

    public int getCoordenadasFinY() {
        return coordenadasFinY;
    }

    public int getCoordenadasFinX() {
        return coordenadasFinX;
    }

    public int getTiempoViaje() {
        return tiempoViaje;
    }

    public double getImporte() {
        return importe;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setImporte() {
        int descuentoPremium = 0;
        importe = tarifa.getPrecioPorMinuto() * tiempoViaje;
        if (usuario instanceof UsuarioPremium) {
            descuentoPremium = tarifa.getDescuentoPremium();
            importe = importe - (importe * descuentoPremium / 100);
        }
        this.importe = importe;
    }

    public void finalizarAlquiler(){
        System.out.println(fechaHoraInicio);
        fechaHoraFin = LocalDateTime.now();
        System.out.println(fechaHoraFin);
        tiempoViaje =  (int) Duration.between(fechaHoraInicio, fechaHoraFin).toMinutes();
        setImporte();
        System.out.println("El precio del viaje han sido " + importe + "€");
    }

    @Override
    public String toString() {
        return "Alquiler{" +
                "idAlquiler=" + idAlquiler +
//                ", vehiculo=" + vehiculo +
//                ", usuario=" + usuario +
//                ", baseInicio=" + baseInicio +
//                ", baseFin=" + baseFin +
//                ", coordenadasInicioX=" + coordenadasInicioX +
//                ", coordenadasInicioY=" + coordenadasInicioY +
//                ", coordenadasFinX=" + coordenadasFinX +
//                ", coordenadasFinY=" + coordenadasFinY +
                ", fechaHoraInicio=" + fechaHoraInicio +
                ", fechaHoraFin=" + fechaHoraFin +
//                ", tiempoViaje=" + tiempoViaje +
//                ", tarifa=" + tarifa +
//                ", importe=" + importe +
                '}';
    }
}
