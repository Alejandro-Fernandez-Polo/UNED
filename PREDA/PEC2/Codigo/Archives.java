import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Clase de gestión de archivos para el sistema de backtracking.
 * Responsable de:
 * - Leer la configuración inicial desde un archivo de entrada
 * - Ejecutar el algoritmo de backtracking
 * - Exportar la solución a consola o archivo de salida
 */
public class Archives {
    /** Ruta o nombre del fichero de entrada que contiene el número de cursos. */
    public String fichero;
    /** Instancia del motor de backtracking que resuelve el problema. */
    public Backtracking backtracking;

    /**
     * Constructor que asocia la instancia a un archivo de entrada.
     *
     * @param fichero nombre del fichero a leer (ruta relativa o absoluta)
     */
    public Archives(String fichero) {
        this.fichero = fichero;
    }

    /**
     * Carga y ejecuta el algoritmo de backtracking desde el archivo de entrada.
     * Lee el número de cursos del archivo y lanza el proceso de asignación.
     *
     * Formato esperado del archivo de entrada:
     * Primera línea: número entero N (cantidad de cursos, aulas y profesores)
     *
     * @param show si true, muestra trazas detalladas del proceso de búsqueda
     */
    public void loadBacktracking(Boolean show) {
        try {
            // Abre el fichero y prepara el lector de enteros
            Scanner sr = new Scanner(new File(fichero));
            // Inicializa el backtracking con el número leído del archivo
            this.backtracking = new Backtracking(sr.nextInt());

            if (show) {
                System.out.println("Se va a intentar asignar " + backtracking.getNumberOfCourses() + " cursos, "  +
                        backtracking.getNumberOfCourses() + " aulas y " + backtracking.getNumberOfCourses() + " profesores.");
            }
            // Ejecuta el algoritmo de backtracking comenzando por el curso 1
            backtracking.cursosEscuela(1, show);
        } catch (Exception e) {
            // Si ocurre cualquier error (fichero inexistente, formato inválido, etc.)
            System.out.println("Error, el fichero no existe.");
        }
    }

    /**
     * Obtiene y muestra la solución del backtracking.
     * Puede escribir en consola o exportar a un archivo según el parámetro.
     *
     * Formatos de salida:
     * - Si hay solución: líneas con formato "aula curso profesor"
     * - Sin solución: imprime "0"
     *
     * @param fichero ruta del archivo de salida. Si es null, muestra por consola
     */
    public void getSolution(String fichero) {
        // Salida por consola cuando no se especifica fichero de salida
        if (fichero == null) {
            for (Course course : backtracking.getSolution()) {
                // Si algún curso no tiene asignación, no hay solución completa
                if (course == null) {
                    System.out.println("0");
                    return;
                }
                System.out.println(course);
            }
            return;
        }

        // Verifica que el fichero de salida no exista para evitar sobreescritura accidental
        if (new File(fichero).exists()) {
            System.err.println("Error: El fichero de salida ya existe.");
            return;
        }

        // Salida a fichero cuando se especifica ruta/nombre de salida
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichero))) {
            for (Course course : backtracking.getSolution()) {
                // Si no hay solución válida, escribe "0"
                if (course == null) {
                    writer.write("0");
                    writer.newLine();
                    return;
                }
                // Escribe cada curso en formato "aula curso profesor"
                writer.write(String.valueOf(course));
                writer.newLine();
            }
            System.out.println("Archivo creado correctamente.");
        } catch (IOException e) {
            // Manejo de errores de escritura
            System.err.println("Error en la escritura de archivo: " + e.getMessage());
        }
    }
}
