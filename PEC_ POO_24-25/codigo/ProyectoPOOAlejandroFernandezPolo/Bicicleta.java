/**
 * Clase que representa una bicicleta eléctrica.
 * Extiende la clase abstracta Vehiculo e implementa el comportamiento específico
 * de cálculo de consumo de batería para bicicletas.
 *
 * @author
 */

public class Bicicleta extends Vehiculo {

    /**
     * Constructor de la clase Bicicleta.
     * Inicializa una bicicleta con los valores especificados.
     *
     * @param id           Identificador único de la bicicleta
     * @throws IllegalArgumentException Si el estado proporcionado no es válido
     */
    public Bicicleta(int id) throws IllegalArgumentException {
        // Llama al constructor de la clase base Vehiculo
        super(id);
    }

    /**
     * Implementación del método abstracto calcularConsumoBateria.
     * En el caso de las bicicletas eléctricas, se estima un consumo de 1% de batería por minuto de uso.
     *
     * @param minutos Tiempo de uso en minutos
     */
    @Override
    public void calcularConsumoBateria(int minutos) {
        setNivelBateria(this.nivelBateria- (minutos * 1));
    }

    /**
     * Devuelve una representación textual del objeto Bicicleta.
     * Incluye los valores actuales de sus atributos principales.
     *
     * @return Cadena con la información formateada del objeto Bicicleta
     */
    @Override
    public String toString() {
        return "Bicicleta{" +
                "id=" + id +
                ", nivelBateria=" + nivelBateria +
                ", estado=" + estado +
                '}';
    }
}


