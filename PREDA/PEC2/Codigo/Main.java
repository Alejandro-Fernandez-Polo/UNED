public class Main {

    public static void main(String[] args) {
        // Instancia del helper de ayuda
        Help help = new Help();
        // Variable para manejar archivos, inicialmente null
        Archives a = null;

        // Validación: si hay 4 argumentos, verifica si alguno solicita ayuda
        if (args.length == 4) {
            if (args[0].equalsIgnoreCase("-h") || args[1].equalsIgnoreCase("-h")) {
                help.showHelp();
            } else {
                // Número de argumentos inválido
                System.out.println("Error de argumentos, vea ayuda con [-h]");
            }
        }
        // Caso 1: Procesamiento estándar sin flags
        // El primer argumento es directamente el archivo de entrada
        else if (!args[0].equalsIgnoreCase("-h") && !args[0].equalsIgnoreCase("-t")) {
            a = new Archives(args[0]);
            a.loadBacktracking(false); // Ejecuta backtracking sin mostrar trazas

            if (args.length == 1) {
                // Solo archivo de entrada: muestra resultado en consola
                a.getSolution(null);
            } else {
                // Con archivo de salida: exporta resultado al archivo
                a.getSolution(args[1]);
            }
        }
        // Caso 2: Flag de ayuda
        else if (args[0].equalsIgnoreCase("-h")) {
            help.showHelp();
        }
        // Caso 3: Modo trazas activado
        // Muestra el proceso paso a paso del backtracking
        else if (args[0].equalsIgnoreCase("-t")) {
            a = new Archives(args[1]); // El archivo está en la segunda posición
            a.loadBacktracking(true); // Ejecuta backtracking mostrando trazas

            if (args.length == 2) {
                // Solo flag y archivo: muestra resultado en consola
                a.getSolution(null);
            } else {
                // Con archivo de salida: exporta resultado al archivo
                a.getSolution(args[2]);
            }
        }
        // Caso por defecto: formato no válido
        else {
            System.out.println("Error de argumentos, vea ayuda con [-h]");
        }
    }

}
