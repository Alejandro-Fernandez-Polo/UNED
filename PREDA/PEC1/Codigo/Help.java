public class Help {
    /**
     * Constructor clase Ayuda
     */
    public Help() {
    }

    /*Método que muestra por pantalla la ayuda*/
    public void showHelp() {
        System.out.println("Ayuda para la Operadora de telecomunicaciones");
        System.out.println("La sintaxis del comando para la Operadora de telecomunicaciones es:");
        System.out.println("[-t] [-h] [fichero_entrada] [fichero_salida]");
        System.out.println("-h: Muestra esta ayuda. (OPCIONAL)");
        System.out.println("-t: Modo traza. Muestra mensajes explicativos del funcionamiento del programa. (OPCIONAL)");
        System.out.println("fichero_entrada: Fichero de texto que contiene la descripción de la ciudad. (REQUERIDO)");
        System.out.println("fichero_salida: Fichero de texto donde se guardará la solución. (OPCIONAL)");
        System.out.println("Si no se especifica fichero_salida, la solución se mostrará por pantalla.");
    }
}
