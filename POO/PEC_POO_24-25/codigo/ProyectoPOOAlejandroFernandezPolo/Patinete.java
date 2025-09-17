/**
 * Clase que representa un patinete eléctrico.
 * Hereda de la clase abstracta Vehiculo y proporciona una implementación específica
 * del cálculo de consumo de batería para este tipo de vehículo.
 *
 * @author
 */
public class Patinete extends Vehiculo {

    /**
     * Constructor de la clase Patinete.
     * Inicializa un nuevo patinete con los parámetros especificados.
     *
     * @param id           Identificador único del patinete
     * @throws IllegalArgumentException Si el estado proporcionado no es válido
     */
    public Patinete(int id) throws IllegalArgumentException {
        // Llama al constructor de la clase base Vehiculo
        super(id);
    }

    /**
     * Implementación del método abstracto para calcular el consumo de batería.
     * Para los patinetes, se asume un consumo de 0.5% de batería por minuto de uso.
     *
     * @param minutos Tiempo de uso en minutos
     * @return Consumo estimado de batería en porcentaje
     */
    @Override
    public void calcularConsumoBateria(int minutos) {
        setNivelBateria(this.nivelBateria- (minutos * 0.5));
    }

    /**
     * Devuelve una representación en cadena del objeto Patinete.
     * Incluye todos los atributos relevantes del vehículo.
     *
     * @return Cadena con la información del patinete
     */
    @Override
    public String toString() {
        return "Patinete{" +
                "id=" + id +
                ", nivelBateria=" + nivelBateria +
                ", estado=" + estado +
                '}';
    }
}