/**
 * Clase que representa una moto eléctrica.
 * Hereda de la clase abstracta Vehiculo e incorpora un atributo adicional: la cilindrada.
 * El consumo de batería varía en función del tipo de cilindrada (PEQUEÑA o GRANDE).
 *
 * @author
 */

// Enumeración que define los posibles tipos de cilindrada para una moto
enum Cilindradas {
    PEQUEÑA, GRANDE
}

public class Moto extends Vehiculo {

    // Atributo adicional específico de las motos
    public Cilindradas cilindrada;
    public int coordX;                // Coordenada X de la ubicación de la moto
    public int coordY;                // Coordenada Y de la ubicación de la moto

    /**
     * Constructor de la clase Moto.
     * Inicializa una moto con los valores especificados.
     *
     * @param id           Identificador único de la moto
     * @param coordX       Coordenada X de ubicación
     * @param coordY       Coordenada Y de ubicación
     * @param cilindrada   Tipo de cilindrada como cadena ("PEQUEÑA" o "GRANDE")
     * @throws IllegalArgumentException Si el estado o la cilindrada no son válidos
     */
    public Moto(int id, int coordX, int coordY, String cilindrada) throws IllegalArgumentException {
        // Llama al constructor de la clase base Vehiculo
        super(id);
        this.coordX = coordX;
        this.coordY = coordY;
        setCilindrada(cilindrada);

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

    /**
     * Devuelve la cilindrada de la moto.
     *
     * @return Cilindrada de la moto (enum)
     */
    public Cilindradas getCilindrada() {
        return cilindrada;
    }

    /**
     * Establece la cilindrada de la moto a partir de una cadena.
     * La cadena debe coincidir con los valores del enum: "PEQUEÑA" o "GRANDE".
     *
     * @param cilindrada Cadena que representa el tipo de cilindrada
     * @throws IllegalArgumentException Si el valor no es válido
     */
    public void setCilindrada(String cilindrada) {
        try {
            this.cilindrada = Cilindradas.valueOf(cilindrada.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El estado debe ser 'PEQUEÑA' o 'GRANDE'. Valor recibido: " + cilindrada);
        }
    }

    /**
     * Implementación del método abstracto que calcula el consumo de batería.
     * El consumo depende del tipo de cilindrada:
     * - PEQUEÑA: 0.4% por minuto
     * - GRANDE: 0.25% por minuto (más eficiente)
     *
     * @param minutos Tiempo de uso en minutos
     * @return Consumo estimado de batería en porcentaje
     */
    @Override
    public void calcularConsumoBateria(int minutos) {
        if (cilindrada == Cilindradas.PEQUEÑA) {
            setNivelBateria(this.nivelBateria- (minutos * 0.4));
        } else {
            setNivelBateria(this.nivelBateria- (minutos * 0.25));
        }
    }

    /**
     * Devuelve una representación textual del objeto Moto.
     * Incluye todos los atributos principales del vehículo y su cilindrada.
     *
     * @return Cadena con la información de la moto
     */
    @Override
    public String toString() {
        return "Moto{" +
                "id=" + id +
                ", nivelBateria=" + nivelBateria +
                ", coordX=" + coordX +
                ", coordY=" + coordY +
                ", estado=" + estado +
                ", cilindrada=" + cilindrada +
                '}';
    }
}
