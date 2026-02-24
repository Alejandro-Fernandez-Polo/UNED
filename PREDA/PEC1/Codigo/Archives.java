import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Clase responsable de cargar una red (Network) desde un fichero de texto
 * y de volcar la solución (coste total y aristas seleccionadas) por consola
 * o a un fichero de salida.
 */
public class Archives {
    /** Ruta o nombre del fichero de entrada que contiene la definición de la red. */
    public String fichero;
    /** Instancia de la red cargada desde el fichero. */
    public Network network;

    /**
     * Crea una instancia asociada a un fichero de entrada.
     *
     * @param fichero nombre del fichero a leer (ruta relativa o absoluta)
     */
    public Archives(String fichero) {
        this.fichero = fichero;
    }

    /**
     * Carga la red desde el fichero indicado en 'fichero'.
     * Formato esperado:
     * - Primera línea: dos enteros -> número de nodos y valor Y.
     * - Resto de líneas: triples de enteros -> i j coste (una arista por línea).
     *
     * @param show si es true, muestra mensajes explicativos por consola
     */
    public void loadNetwork(Boolean show) {
        try {
            int i, j, cost;
            // Abre el fichero y prepara el lector de enteros
            Scanner sr = new Scanner(new File(fichero));
            // Lee cabecera: número de nodos y Y, y crea la red
            this.network = new Network(sr.nextInt(), sr.nextInt());
            if (show) {
                System.out.println("Red creada con " + network.nodes + " nodos y Y = " + network.y);
            }
            // Lee cada arista (i, j, coste) hasta agotar la entrada y la añade a la red
            while (sr.hasNext()) {
                i = sr.nextInt();
                j = sr.nextInt();
                cost = sr.nextInt();
                network.addEdge(new Edge(i, j, cost));
            }
            // Selecciona las conexiones según la política definida en Network
            network.selectConnections(show);
        } catch (Exception e) {
            // Si ocurre cualquier error (por ejemplo, fichero inexistente o formato inválido)
            System.out.println("Error, el fichero no existe.");
        }
    }

    /**
     * Obtiene la solución de la red ya procesada.
     * - Si 'fichero' es null, imprime el coste total y las aristas seleccionadas por consola.
     * - Si 'fichero' no es null, escribe el coste total en la primera línea y
     *   las aristas seleccionadas en líneas posteriores en el fichero indicado.
     *
     * @param fichero nombre del fichero de salida; si es null, se muestra por consola
     */
    public void getSolution(String fichero) {
        // Salida por consola cuando no se especifica fichero de salida
        if (fichero == null) {
            System.out.println("Coste total: " + network.getTotalCost());
            for (Edge edge : network.selectedConnections) {
                System.out.println(edge.i + " " + edge.j + " " + edge.cost);
            }
            return;
        }
        // Verifica que el fichero de salida no exista para evitar sobreescritura
         if (new File(fichero).exists()) {
            System.err.println("Error: El fichero de salida ya existe.");
            return;
        }
        // Salida a fichero cuando se especifica ruta/nombre de salida
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichero))) {
            // Primera línea: coste total
            writer.write(String.valueOf(network.getTotalCost()));
            writer.newLine();
            // Líneas siguientes: aristas seleccionadas en formato "i j coste"
            for (Edge edge : network.selectedConnections) {
                writer.write(String.valueOf(edge.i) +' '+ edge.j +' '+ edge.cost);
                writer.newLine();
            }
            System.out.println("Archivo creado correctamente.");
        } catch (IOException e) {
            // Manejo de errores de escritura (permisos, disco, ruta, etc.)
            System.err.println("Error en la escritura de archivo: " + e.getMessage());
        }
    }
}
