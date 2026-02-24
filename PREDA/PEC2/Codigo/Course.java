/**
 * Representa un curso que necesita ser asignado a un aula y un profesor.
 */
public class Course {
    // Identificador del aula asignada al curso
    private int classroom;
    // Identificador del profesor asignado al curso
    private int professor;
    // Número identificador único del curso
    private int number;

    /**
     * Constructor que inicializa un curso con su número identificador.
     *
     * @param number identificador del curso
     */
    public Course(int number) {
        this.number = number;
    }


    public void setProfessor(int professor) {
        this.professor = professor;
    }

    public void setClassroom(int classroom) {
        this.classroom = classroom;
    }

    /**
     * Representación en formato de texto del curso.
     * Formato: "aula curso profesor"
     *
     * @return cadena con los valores de aula, número de curso y profesor separados por espacios
     */
    @Override
    public String toString() {
        return classroom + " " + number + " " + professor;
    }
}