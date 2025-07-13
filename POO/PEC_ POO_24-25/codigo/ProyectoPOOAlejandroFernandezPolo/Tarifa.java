enum TipoVehiculos {
    MOTO, BICICLETA, PATINETE
}

public class Tarifa
{
    private TipoVehiculos tipoVehiculo;
    private int precioPorMinuto;
    private int descuentoPremium;
    
    public Tarifa(TipoVehiculos tipoVehiculo, int precioPorMinuto, int descuentoPremium)
    {
        this.tipoVehiculo = tipoVehiculo;
        this.precioPorMinuto = precioPorMinuto;
        this.descuentoPremium = descuentoPremium;
    }

    public TipoVehiculos getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculos tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public int getPrecioPorMinuto() {
        return precioPorMinuto;
    }

    public void setPrecioPorMinuto(int precioPorMinuto) {
        this.precioPorMinuto = precioPorMinuto;
    }

    public int getDescuentoPremium() {
        return descuentoPremium;
    }

    public void setDescuentoPremium(int descuentoPremium) {
        this.descuentoPremium = descuentoPremium;
    }

    @Override
    public String toString() {
        return '\n' + "La tarifa de " + tipoVehiculo + " es:"+ '\n' + "Precio por minuto: " + precioPorMinuto+ "€" +
                '\n' + "Descuento premium " + descuentoPremium + "%" + '\n';
    }
}
