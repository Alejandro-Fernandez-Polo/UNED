import java.util.Random;

/**
 * Gestiona las restricciones de asignación entre cursos, aulas y profesores.
 * Utiliza generación aleatoria para simular reglas de validación.
 */
public class Restrictions {

    // Generador de números aleatorios para simular decisiones de validación
    private Random rnd;

    /**
     * Constructor que inicializa el generador aleatorio.
     */
    public Restrictions() {
        this.rnd = new Random(12);
    }

    /**
     * Verifica si un curso puede ser asignado a un aula específica.
     * Simula restricciones de compatibilidad aula-curso.
     *
     * @param classroom identificador del aula
     * @param course identificador del curso
     * @return true si la asignación es válida, false en caso contrario (aleatorio)
     */
    public boolean valid(int classroom, int course) {
        // Devuelve verdadero o falso aleatoriamente
        return rnd.nextBoolean();
    }

    /**
     * Verifica si un profesor tiene la especialidad requerida para impartir un curso.
     * Simula restricciones de compatibilidad profesor-curso.
     *
     * @param professor identificador del profesor
     * @param course identificador del curso
     * @return true si el profesor puede impartir el curso, false en caso contrario (aleatorio)
     */
    public boolean specialty(int professor, int course) {
        // Devuelve verdadero o falso aleatoriamente
        return rnd.nextBoolean();
    }
}
