public class Main {

    /**
     * Punto de entrada del programa.
     * Procesa los argumentos de línea de comandos y ejecuta las operaciones correspondientes.
     *
     * Formatos válidos:
     * - [-h] : muestra la ayuda
     * - fichero_entrada [fichero_salida] : carga red, resuelve y exporta (modo silencioso)
     * - [-t] fichero_entrada [fichero_salida] : carga red, resuelve y exporta (modo trazas)
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        Help help = new Help();
        Archives a = null;

        // Caso: 4 argumentos (formato poco habitual, se verifica si alguno es ayuda)
        if (args.length == 4) {
            if (args[0].equalsIgnoreCase("-h") || args[1].equalsIgnoreCase("-h")) {
                help.showHelp();
            } else {
                System.out.println("Error de argumentos, vea ayuda con [-h]");
            }
            // Caso: primer argumento es fichero (sin flags -h o -t)
        } else if (!args[0].equalsIgnoreCase("-h") && !args[0].equalsIgnoreCase("-t") ) {
            a = new Archives(args[0]);
            a.loadNetwork(false);// Carga sin mostrar trazas
            if (args.length == 1) {
                // Si solo hay un argumento, muestra la solución por consola
                a.getSolution(null);
            }else{
                // Si hay segundo argumento, exporta la solución al fichero indicado
                a.getSolution(args[1]);
            }
            // Caso: se solicita ayuda con flag -h
        } else if (args[0].equalsIgnoreCase("-h")) {
            help.showHelp();
            // Caso: modo trazas activado con flag -t
        } else if (args[0].equalsIgnoreCase("-t")) {
            a = new Archives(args[1]);
            a.loadNetwork(true); // Carga mostrando mensajes explicativos
            if (args.length == 2) {
                // Si solo hay dos argumentos (flag + fichero), muestra la solución por consola
                a.getSolution(null);
            }else{
                // Si hay tercer argumento, exporta la solución al fichero indicado
                a.getSolution(args[2]);
            }
            // Caso por defecto: argumentos no reconocidos
        } else {
            System.out.println("Error de argumentos, vea ayuda con [-h]");
        }
    }
}
