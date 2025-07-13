package es.uned.lsi.eped.pract2024_2025;

/**
 * La clase Tarea representa una tarea con una descripción, una fecha  y un estado de finalización.
 * Implementa la interfaz TaskIF.
 */
public class Task implements TaskIF {

	/* Declaración de atributos para almacenar la información de una tarea */
	private String text = null;
	private int date;
	private boolean completion;
	
	/**
	 * Crea una nueva tarea con la descripción y fecha especificadas.
	 * La tarea se inicializa como no completada. Si la descripción está vacía,
	 * la tarea no se agregará y se mostrará el mensaje correspondiente.
	 *
	 * @param text La descripción de la tarea. No debe estar vacía ni en blanco.
	 * @param date La fecha de la tarea representada como un número entero.
	 */
	public Task(String text, int date) {
	     if (textEmpty(text)) {
	    	 this.text = text;
	    	 this.date = date;
	         this.completion = false;
	     } else {
			 System.out.println("Texto vacío no se ha añadido la tarea" + text);
	     }

	}
	
	/**
	 * Comprueba si el texto proporcionado está vacío o contiene solo espacios en blanco.
	 *
	 * @param text la cadena para comprobar si está vacía
	 * @return verdadero si el texto está vacío o contiene solo espacios en blanco, falso en caso contrario
	 */
	private boolean textEmpty(String text) {
        return !text.trim().isEmpty();
    }

	/**
	 * Actualiza la fecha de la tarea.
	 *
	 * @param date la nueva fecha, representada como un número entero
	 */
	public void setDate(int date) {
		this.date = date;
	}

	/**
	 * Marca la tarea como completada estableciendo su estado de finalización en verdadero.
	 */
	public void setCompleted() {
		this.completion = true;
	}
	
	/**
	 * Devuelve el texto de la tarea.
	 *
	 * @return el texto de la tarea
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Devuelve la fecha de la tarea.
	 *
	 * @return la fecha de la tarea, representada como un número entero
	 */
	public int getDate() {
		return this.date;
	}

	/**
	 * Devuelve el estado de finalización de la tarea.
	 *
	 * @return verdadero si la tarea ha sido completada, falso en caso contrario
	 */
	public boolean getCompletion() {
		return this.completion;
	}

	/**
	 * Compara la tarea actual con la tarea especificada en función de sus fechas de vencimiento.
	 *
	 * @param T La tarea a comparar con la tarea actual
	 * @return -1 si la fecha de la tarea actual es anterior a la fecha de la tarea especificada,
	 *	 		1 si la fecha de la tarea actual es posterior a la fecha de la tarea especificada,
	 *  		y 0 si ambas tareas tienen la misma fecha
	 */
	public int compareTo(TaskIF T) {
	    if (this.date < T.getDate()) {
	        return -1;
	    } else if (this.date > T.getDate()) {
	        return 1;
	    } else {
	        return 0;
	    }
	}

	@Override
	public String toString(){
		return "nombre: " + this.text + " fecha: " + this.date + " completed: " + this.completion;
	}


}
