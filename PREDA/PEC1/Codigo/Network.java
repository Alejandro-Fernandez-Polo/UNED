import java.sql.Array;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Representa una red de nodos y aristas.
 * Selecciona un subconjunto de aristas de mínimo coste que conecte todos los nodos
 * sin formar ciclos (árbol de expansión mínimo con algoritmo de Kruskal + UnionFind).
 */
public class Network {
    /** Número de nodos en la red. */
    public int nodes;
    /** Parámetro Y usado para validar/corregir el coste de las aristas. */
    public int y;
    /** Lista de todas las aristas disponibles en la entrada. */
    public List<Edge> edges;
    /** Aristas seleccionadas tras aplicar el algoritmo (resultado). */
    public List<Edge> selectedConnections;
    /** Matriz de adyacencia de las aristas seleccionadas (opcional). */
//    public int[][] adjacency;
    /** Coste total de las aristas seleccionadas. */
    public int totalCost;

    /** Conjunto para registrar mensajes ya mostrados y evitar repetición. */
    private final Set<String> shownMessages = new HashSet<>();

    /**
     * Inicializa la red con el número de nodos y el parámetro Y.
     * Crea listas vacías.
     */
    public Network(int nodes, int y) {
        this.nodes = nodes;
        this.y = y;
        this.edges = new java.util.ArrayList<>();
        this.selectedConnections = new java.util.ArrayList<>();
//        this.adjacency = new int[nodes][nodes];
    }

    /**
     * Muestra un mensaje una única vez si 'show' es true.
     */
    private void printOnce(String key, String message, boolean show) {
        if (!show) return;
        if (shownMessages.add(key)) {
            System.out.println(message);
        }
    }

    /**
     * Calcula el coste total sumando los costes de las aristas seleccionadas.
     */
    public int getTotalCost() {
        totalCost = 0;
        for (Edge edge : selectedConnections) {
            totalCost += edge.cost;
        }
        return totalCost;
    }

    /**
     * Añade una arista a la lista de disponibles y verifica/ajusta su coste con Y.
     */
    public void addEdge(Edge edge) {
        edges.add(edge);
        edge.checkCost(y);
    }

    /**
     * Ordena las aristas por coste ascendente (precondición de Kruskal).
     */
    public void sortEdges() {
        edges.sort(Edge::compareTo);
    }

    /**
     * Selecciona las conexiones usando Kruskal:
     * - Ordena aristas por coste
     * - Recorre y añade aristas que no formen ciclo según UnionFind
     * - Finaliza al tener 'nodes - 1' aristas (árbol de expansión)
     *
     * @param show si es true, muestra trazas del proceso
     */
    public void selectConnections(Boolean show) {
        printOnce("show_message", "Se mostrarán los mensajes explicativos de forma única", show);
        selectedConnections.clear();
        printOnce("show_available", "Se ordenan las conexiones disponibles por coste", show);
        sortEdges();

        // Estructura de conjuntos disjuntos para detectar ciclos eficientemente
        UnionFind uf = new UnionFind(nodes);
        printOnce("union_find_created", "Se crea una estructura UnionFind para detectar ciclos eficientemente.", show);

        // Recorre las aristas en orden creciente de coste
        for (Edge edge : edges) {
            printOnce("select_explain", "Se intenta añadir cada conexión verificando que no forme ciclos usando UnionFind.", show);

            // Indices ajustados a base 0 para UnionFind
            if (uf.union(edge.i - 1, edge.j - 1)) {
                // Si la unión se realiza, no hay ciclo y se selecciona la arista
                printOnce("select_added", "Añadimos la conexión a la red seleccionada, ya que cumple con los requisitos.", show);
                if (show) {
                    System.out.println("Se añade la conexión de coste " + edge.cost + " entre los nodos " + edge.i + " y " + edge.j);
                }
                selectedConnections.add(edge);
            } else {
                // Si ya estaban conectados, la arista formaría ciclo y se descarta
                if (show) {
                    System.out.println("No se añade la conexión de coste " + edge.cost + " entre los nodos " + edge.i + " y " + edge.j + " para evitar ciclos.");
                }
                printOnce("select_not_added", "No se añade la conexión a la red seleccionada para evitar ciclos.", show);
            }

            // Un árbol de expansión mínimo en un grafo conectado tiene (n - 1) aristas
            if (selectedConnections.size() == nodes - 1) {
                if (show) {
                    System.out.println("Se han seleccionado todas las conexiones necesarias para conectar todos los nodos, es decir, " + (nodes - 1) + " conexiones para " + nodes + " nodos.");
                }
                break;
            }
        }
    }

}

//    public void selectConnections(Boolean show) {
//        printOnce("show_message", "Se mosrtraran los mensajes explicativos de forma única", show);
//        selectedConnections.clear();
//        printOnce("show_available", "Se ordenan las conexiones disponibles por coste", show);
//        sortEdges();
//        for (Edge edge : edges) {
//            printOnce("select_explain", "En caso de no haber ninguna conexion previa, se añade la conexion," +
//                    " si ya exise una o mas conexiones se comprueba que no se forme un ciclo antes de añadirla.", show);
//
//            if (!isIn(edge, selectedConnections, show)) {
//                printOnce("select_added", "Añadimos la conexion a la red seleccionada, ya que cumple con " +
//                        "los requisitos explicados anteriormente.", show);
//                if (show){
//                    System.out.println("Se añade la conexion de coste " + edge.cost + " entre los nodos " + edge.i + " y " + edge.j);
//                }
//                selectedConnections.add(edge);
//            }else{
//                if (show){
//                    System.out.println("No se añade la conexion de coste " + edge.cost + " entre los nodos " + edge.i + " y " + edge.j + " para evitar ciclos.");
//                }
//                printOnce("select_not_added", "No se añade la conexion a la red seleccionada para evitar ciclos.", show);
//            }
//            if (selectedConnections.size() == nodes - 1) {
//                if (show) {
//                    System.out.println("Se han seleccionado todas las conexiones necesarias para conectar todos los nodos, " +
//                            "es decir, " +(nodes - 1) + " conexiones para " + nodes + " nodos.");
//                }
//                break;
//            }
//        }
//    }

//    public void buildAdjacency(boolean show) {
//        printOnce("build_adjacency", "Se crea la matriz de adyacencia de las conexiones seleccionadas, o se" +
//                " añaden las conexiones nuevas a la matriz ya existente.", show);
//        for (Edge ed : selectedConnections) {
//            adjacency[ed.i - 1][ed.j - 1] = 1;
//            adjacency[ed.j - 1][ed.i - 1] = 1;
//        }
//    }

//    public boolean isIn(Edge edge, List<Edge> connections, boolean show) {
//        if (!connections.isEmpty()) {
//            boolean iExists = false;
//            boolean jExists = false;
//            boolean alreadyConnected = false;
//            Edge node = null;
//            for (Edge ed : connections) {
//                if (ed.i == edge.i || ed.j == edge.i) {
//                    iExists = true;
//                    node = ed;
//                }
//                if (ed.i == edge.j || ed.j == edge.j) {
//                    jExists = true;
//                }
//                if (iExists && jExists) {
//                    break;
//                }
//            }
//            printOnce("check_nodes", "Comprobamos si los nodos ya aparecen en las conexiones seleccionadas," +
//                    " en caso negativo se añade la conexion.", show);
//            if (iExists && jExists) {
//                alreadyConnected = isConnection(node, edge, show);
//            }
//            return iExists && jExists && alreadyConnected;
//        }
//        return false;
//    }

//    public boolean isConnection(Edge node, Edge edge, boolean show) {
//        printOnce("check_cycle_start", "Ambos nodos ya existen en las conexiones seleccionadas, comprobamo" +
//                "s si ya hay una conexion entre ellos para evitar ciclos.", show);
//        buildAdjacency(show);
//        int a = node.i;
//        boolean aAppears = false;
//        boolean bAppears = false;
//        printOnce("getting_connected_nodes", "Obtenemos todos los nodos conectados a el nodo que estamos estudiando.", show);
//        List<Integer> connectedNodes = new java.util.ArrayList<>();
//        connectedNodes.add(a);
//        connectedNodes = addConnection(a, connectedNodes);
//        for (int connectedNode : connectedNodes) {
//            if (connectedNode == edge.i) {
//                aAppears = true;
//            }
//            if (connectedNode == edge.j) {
//                bAppears = true;
//            }
//            if (aAppears && bAppears) {
//                break;
//            }
//        }
//        if (aAppears && bAppears) {
//            printOnce("cycle_detected", "En caso de que los dos nodos aparezcan en la lista de conexiones, " +
//                    "ya hay una conexion entre ellos y no se añade la conexion para evitar ciclos.", show);
//            return true;
//        }
//        return false;
//    }

//    public List<Integer> addConnection(int a, List<Integer> connectedNodes) {
//        a = a - 1;
//        for (int k = 1; k <= adjacency[a].length; k++) {
//            if (adjacency[a][k - 1] == 1 && !connectedNodes.contains(k)) {
//                connectedNodes.add(k);
//                addConnection(k, connectedNodes);
//            }
//        }
//        return connectedNodes;
//    }