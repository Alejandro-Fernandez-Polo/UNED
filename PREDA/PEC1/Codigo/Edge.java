/**
 * Representa una arista (conexión) entre dos nodos en la red.
 * Cada arista conecta los nodos i y j con un coste asociado.
 */
public class Edge {
    /** Identificador del primer nodo de la arista. */
    public int i;
    /** Identificador del segundo nodo de la arista. */
    public int j;
    /** Coste asociado a esta arista. */
    public int cost;

    /**
     * Crea una arista entre dos nodos con un coste dado.
     *
     * @param i primer nodo
     * @param j segundo nodo
     * @param cost coste de la conexión
     */
    public Edge(int i, int j, int cost) {
        this.i = i;
        this.j = j;
        this.cost = cost;
    }

    /**
     * Verifica y ajusta el coste si es necesario.
     * Si el coste es -1 (no especificado), se calcula como (i * j) % y.
     *
     * @param y parámetro usado para calcular el coste cuando no está definido
     */
    public void checkCost(int y) {
        if (cost == -1) {
            cost = (i * j) % y;
        }
    }

    /**
     * Compara esta arista con otra según su coste.
     * Permite ordenar aristas de menor a mayor coste.
     *
     * @param other arista con la que comparar
     * @return valor negativo si this.cost < other.cost,
     *         cero si son iguales,
     *         positivo si this.cost > other.cost
     */
    public int compareTo(Edge other) {
        return Integer.compare(this.cost, other.cost);
    }

}
