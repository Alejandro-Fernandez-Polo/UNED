public class UnionFind {
    // parent[i] almacena el padre del nodo i; si parent[i] == i, i es raíz de su conjunto
    private int[] parent;
    // rank[i] aproxima la altura del árbol cuyo representante es i
    private int[] rank;

    /**
     * Crea una estructura Union-Find de tamaño dado.
     * Cada elemento empieza en su propio conjunto como raíz.
     *
     * @param size número de elementos, indexados 0.
     */
    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;  // cada nodo es su propio padre al inicio
            rank[i] = 0;    // rango inicial 0
        }
    }

    /**
     * Encuentra el representante del conjunto que contiene p.
     * Aplica compresión de caminos para aplanar el árbol y acelerar futuras búsquedas.
     *
     * @param p elemento a consultar
     * @return representante del conjunto de p
     */
    public int find(int p) {
        // Si p no es raíz, subir recursivamente y comprimir el camino
        if (parent[p] != p) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    /**
     * Une los conjuntos que contienen i y j usando unión por rango.
     * Si ya están en el mismo conjunto, no hace nada.
     *
     * @param i primer elemento
     * @param j segundo elemento
     * @return true si se ha realizado la unión, false si ya estaban conectados
     */
    public boolean union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);

        // Si comparten raíz, ya están conectados y unirlos crearía ciclo
        if (rootI == rootJ) {
            return false;
        }

        // Unión por rango: unir el árbol de menor rango al de mayor rango
        if (rank[rootI] < rank[rootJ]) {
            parent[rootI] = rootJ;
        } else if (rank[rootI] > rank[rootJ]) {
            parent[rootJ] = rootI;
        } else {
            // Si tienen el mismo rango, elegir uno como nueva raíz y aumentar su rango
            parent[rootJ] = rootI;
            rank[rootI]++;
        }
        return true;
    }

}
