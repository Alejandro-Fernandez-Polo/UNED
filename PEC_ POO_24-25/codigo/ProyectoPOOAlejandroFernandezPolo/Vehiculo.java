/**
 * Clase abstracta que representa un vehículo genérico.
 * Define atributos comunes y métodos esenciales relacionados con su estado y localización.
 *
 * @author
 */

// Enumeración que define los posibles estados de un vehículo
enum EstadosVehiculo {
    DISPONIBLE, RESERVADO, AVERIADO
}

public abstract class Vehiculo {
    // Atributos de instancia
    public int id;                     // Identificador único del vehículo
    public double nivelBateria;       // Nivel actual de batería (0 a 100)
    public EstadosVehiculo estado;    // Estado actual del vehículo

    /**
     * Constructor de la clase Vehiculo.
     * Inicializa los atributos con los valores recibidos como parámetros.
     *
     * @param id           Identificador del vehículo
     */
    public Vehiculo(int id) {
        this.id = id;
        setNivelBateria(100); // Se asegura de que el nivel de batería no supere el 100%
        setEstado("DISPONIBLE");
    }


    // Métodos getter y setter para acceder y modificar los atributos de la clase
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNivelBateria() {
        return nivelBateria;
    }

    /**
     * Establece el nivel de batería del vehículo.
     * Si el valor recibido es mayor a 100, se ajusta automáticamente a 100.
     *
     * @param nivelBateria Valor a establecer
     */
    public void setNivelBateria(double nivelBateria) {
        if (nivelBateria > 100) {
            nivelBateria = 100;
        }
        this.nivelBateria = nivelBateria;
    }

    public void modificarNivelBateria(int nivelBateria) {
        this.nivelBateria = nivelBateria;
    }

    public EstadosVehiculo getEstado() {
        return estado;
    }

    /**
     * Establece el estado del vehículo a partir de una cadena.
     * Se convierte la cadena a mayúsculas y se valida que coincida con uno de los valores del enum.
     *
     * @param estado Estado a establecer como cadena
     */
    public void setEstado(String estado) {
        try {
            this.estado = EstadosVehiculo.valueOf(estado.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El estado debe ser 'DISPONIBLE', 'RESERVADO' o 'AVERIADO'. Valor recibido: " + estado);
        }
    }

    /**
     * Método abstracto que debe ser implementado por las subclases.
     * Calcula el consumo de batería del vehículo en función del tiempo de uso.
     *
     * @param minutos Tiempo de uso en minutos
     */
    public abstract void calcularConsumoBateria(int minutos);

}