/**
 * Implementa un algoritmo de backtracking para asignar cursos a aulas y profesores.
 * Resuelve el problema de asignación de recursos con restricciones de:
 * - Un aula por curso
 * - Un profesor por curso
 * - Validación de capacidad de aulas
 * - Validación de especialidad de profesores
 */
public class Backtracking {

    // Gestor de restricciones de validación
    private Restrictions restrictions;
    // Array que almacena la solución: cada posición contiene un curso asignado
    private Course[] solution;
    // Indica si se ha encontrado una solución válida
    private boolean success;
    // Control de aulas ya asignadas (evita duplicados)
    private boolean[] asigAula;
    // Control de profesores ya asignados (evita duplicados)
    private boolean[] asigProf;
    // Número total de cursos a asignar
    private int numberOfCourses;

    /**
     * Constructor que inicializa el sistema de backtracking.
     *
     * @param n número de cursos, aulas y profesores (mismo tamaño)
     */
    public Backtracking(int n) {
        this.restrictions = new Restrictions();
        this.solution = new Course[n];
        this.success = false;
        this.asigAula = new boolean[n];
        this.asigProf = new boolean[n];
        this.numberOfCourses = n;
    }

    public int getNumberOfCourses() {
        return numberOfCourses;
    }

    public Course[] getSolution() {
        return solution;
    }

    /**
     * Algoritmo recursivo de backtracking para asignar cursos.
     * Prueba todas las combinaciones posibles de aula-profesor para cada curso
     * hasta encontrar una solución válida o agotar todas las posibilidades.
     *
     * @param courseNumber número del curso actual a asignar (1-indexed)
     * @param show si true, muestra trazas del proceso de búsqueda
     */
    public void cursosEscuela(int courseNumber, boolean show) {

        // Caso base: ya se encontró solución, terminar
        if (this.success) return;

        if (show) {
            System.out.println("Intentando asignar curso " + courseNumber);
        }

        // Crear objeto curso para asignación
        Course course = new Course(courseNumber);
        int classroom = 1;

        // BUCLE EXTERNO: probar todas las aulas disponibles
        while (classroom <= this.numberOfCourses && !this.success) {
            // Verificar si el aula cumple las restricciones del curso
            boolean validClassroom = this.restrictions.valid(classroom, courseNumber);

            if (show) {
                System.out.println("Probando aula " + classroom + " para el curso " + courseNumber + ". ¿Aula válida? " + validClassroom);
            }

            // Si el aula está libre Y es válida para el curso
            if (!this.asigAula[classroom-1] && validClassroom) {
                int professor = 1;

                // BUCLE INTERNO: probar todos los profesores disponibles
                while (professor <= this.numberOfCourses && !this.success) {
                    // Verificar si el profesor tiene la especialidad requerida
                    boolean validProfessor = this.restrictions.specialty(professor, courseNumber);

                    if (show) {
                        System.out.println("Probando profesor " + professor + " para el curso " + courseNumber + ". ¿Profesor válido? " + validProfessor);
                    }

                    // Si el profesor está libre Y tiene la especialidad
                    if (!this.asigProf[professor-1] && validProfessor) {

                        // *** MARCAR (ASIGNAR) ***
                        course.setClassroom(classroom);
                        course.setProfessor(professor);
                        this.solution[courseNumber-1] = course;

                        if (show) {
                            System.out.println("Aqui se ha asignado en la solucion el curso: " + this.solution[courseNumber-1]);
                        }

                        // Marcar recursos como ocupados
                        this.asigAula[classroom-1] = true;
                        this.asigProf[professor-1] = true;

                        // CASO BASE: ¿Es el último curso?
                        if (courseNumber == this.numberOfCourses) {
                            this.success = true; // Solución completa encontrada
                        } else {
                            // RECURSIÓN: intentar asignar el siguiente curso
                            cursosEscuela(courseNumber + 1, show);
                        }

                        // *** DESMARCAR (BACKTRACKING) ***
                        // Si la recursión no tuvo éxito, deshacer la asignación
                        if (!this.success) {
                            if (show) {
                                System.out.println("Deshaciendo asignación del curso " + courseNumber + " (aula " + classroom + ", profesor " + professor + ")");
                            }
                            // Liberar recursos
                            this.asigAula[classroom-1] = false;
                            this.asigProf[professor-1] = false;
                            this.solution[courseNumber-1] = null;
                        }
                    }
                    professor++; // Probar siguiente profesor
                }
            }
            classroom++; // Probar siguiente aula
        }
    }

}
