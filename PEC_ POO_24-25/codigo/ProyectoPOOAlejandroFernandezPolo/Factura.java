import java.time.LocalDateTime;

public class Factura
{
    private int idFactura;
    private Mecanico mecanico;
    private Vehiculo vehiculo;
    private Base base;
    private double importe;
    private LocalDateTime fecha;

    public Factura(int idFactura, Mecanico mecanico, Vehiculo vehiculo, Base base, double
            importe, LocalDateTime fecha)
    {
        this.idFactura = idFactura;
        this.mecanico = mecanico;
        this.vehiculo = vehiculo;
        this.base = base;
        this.importe = importe;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "idFactura=" + idFactura +
                ", mecanico=" + mecanico +
                ", vehiculo=" + vehiculo +
                ", base=" + base +
                ", importe=" + importe +
                ", fecha=" + fecha +
                '}';
    }
}
