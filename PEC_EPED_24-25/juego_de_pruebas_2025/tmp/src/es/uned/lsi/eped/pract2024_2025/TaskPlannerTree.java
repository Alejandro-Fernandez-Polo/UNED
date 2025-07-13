package es.uned.lsi.eped.pract2024_2025;

import es.uned.lsi.eped.DataStructures.*;

/**
 * TaskPlannerTree es una clase de gestión de tareas que implementa la interfaz TaskPlannerIF.
 * Organiza las tareas mediante un árbol de búsqueda binario para las próximas tareas y una lista para las completadas.
 * La clase permite añadir, eliminar, mover, ejecutar y descartar tareas,
 * manteniendo la organización histórica y futura de las tareas.
 */
public class TaskPlannerTree implements TaskPlannerIF {

    /**
     * Un campo protegido que almacena una lista de tareas pasadas gestionadas por TaskPlannerTree.
     * Esta lista contiene las tareas ya ejecutadas o completadas.
     * <p>
     * Esto se implementa mediante una interfaz ListIF de objetos de tarea.
     */
    protected ListIF<Task> pastTasks;
    /**
     * Un árbol de búsqueda binario que almacena tareas programadas para su ejecución futura.
     * Las tareas se organizan automáticamente según su orden natural, según lo define la interfaz TaskIF.
     * <p>
     * Este campo permite gestionar tareas con fechas aún no realizadas y proporciona métodos de acceso para organizar,
     * añadir, eliminar e iterar sobre estas tareas futuras.
     */
    protected BSTreeIF<TaskIF> futureTasks;

    /**
     * Construye una nueva instancia de TaskPlannerTree que funciona como planificador de tareas.
     * <p>
     * Este constructor inicializa el planificador de tareas con:
     * - `pastTasks`: Una lista de tareas completadas o pasadas.
     * - `futureTasks`: Un árbol binario de búsqueda (BSTree) utilizado para almacenar y gestionar las próximas tareas.
     * <p>
     * La lista `pastTasks` permite el almacenamiento cronológico de las tareas completadas,
     * mientras que el árbol `futureTasks` ofrece operaciones eficientes para agregar, eliminar o recuperar tareas
     * según sus fechas programadas.
     */
    public TaskPlannerTree() {
        pastTasks = new List<Task>();
        futureTasks = new BSTree<TaskIF>();
    }

    /**
     * Añade una nueva tarea a la colección futureTasks si el texto de la tarea no es nulo
     * y la tarea no está ya incluida en la colección. Si se añade la tarea,
     * se imprime un mensaje de confirmación; de lo contrario, se muestra un mensaje que indica que la fecha
     * ya tiene una tarea asignada.
     *
     * @param text Descripción de la tarea. No debe ser nula ni estar vacía.
     * @param date La fecha de la tarea, representada como un número entero.
     */
    public void add(String text, int date) {
        Task tarea = new Task(text, date);
        if (tarea.getText() != null && !futureTasks.contains(tarea)) {
            futureTasks.add(tarea);
            System.out.println(tarea + ": Tarea añadida");
        } else {
            System.out.println("Esta fecha ya tiene una tarea asignada");
        }
    }

    /**
     * Elimina una tarea de la lista de tareas futuras según la fecha especificada.
     *
     * @param date la fecha de la tarea que se eliminará, representada como un número entero
     */
    public void delete(int date) {
        Task nuevaTarea = (Task) getTaskByDate(date);
        futureTasks.remove(nuevaTarea);
    }

    /**
     * Mueve una tarea de una fecha a otra dentro del sistema de planificación de tareas.
     * Si ya hay una tarea programada para la nueva fecha, no se realiza el traslado.
     * La tarea se restaura a su estado original.
     *
     * @param origDate la fecha original de la tarea que se va a mover, representada como un número entero
     * @param newDate  La nueva fecha a la que se debe trasladar la tarea, representada como un número entero
     */
    public void move(int origDate, int newDate) {
        Task t = (Task) getTaskByDate(origDate);
        futureTasks.remove(t);
        int oldDate = t.getDate();
        t.setDate(newDate);
        if (getTaskByDate(newDate) == null) {
            futureTasks.add(t);
            System.out.println(origDate + " -> " + newDate + ": Tarea movida");
        } else {
            t.setDate(oldDate);
            futureTasks.add(t);
            System.out.println("ERROR: Ya existe una tarea para la fecha " + newDate);
        }
    }

    /**
     * Ejecuta la siguiente tarea de la colección de tareas futuras, marcándola como completada
     * y trasladándola a la colección de tareas pasadas.
     *
     * Este método realiza las siguientes operaciones:
     * 1. Comprueba si hay tareas disponibles en la colección de tareas futuras.
     * 2. Recupera la siguiente tarea que se ejecutará mediante el método privado getNextTask.
     * 3. Elimina la tarea recuperada de la colección de tareas futuras.
     * 4. Marca la tarea como completada.
     * 5. Inserta la tarea completada en la colección de tareas pasadas en la posición adecuada.
     *
     * Condición previa:
     * El método asume que `futureTasks` y `pastTasks` se inicializaron correctamente.
     *
     * Poscondición:
     * La tarea con la prioridad más alta (determinada por getNextTask) se ha eliminado
     * de `futureTasks`, se ha marcado como completada y se ha añadido al final de `pastTasks`.
     */
    public void execute() {
        if (!futureTasks.isEmpty()) {
            Task next = getNextTask();
            futureTasks.remove(next);
            next.setCompleted();
            pastTasks.insert(pastTasks.size() + 1, next);
        }
    }

    /**
     * Mueve la siguiente tarea de la colección de tareas futuras a la colección de tareas pasadas.
     *
     * Este método identifica la siguiente tarea en la colección `futureTasks`,
     * la elimina de allí y la inserta en la colección `pastTasks` en la siguiente posición disponible.
     * Si `futureTasks` está vacío, el método no realiza ninguna acción.
     *
     * Condición previa: La tarea identificada como la "siguiente" tarea debe existir en la colección `futureTasks`.
     * Condición posterior: La tarea identificada se mueve de `futureTasks` al final de `pastTasks` y
     * `futureTasks` ya no contiene la tarea.
     */
    public void discard() {
        if (!futureTasks.isEmpty()) {
            Task next = getNextTask();
            futureTasks.remove(next);
            pastTasks.insert(pastTasks.size() + 1, next);
        }
    }

    /**
     * Crea y devuelve un iterador para la lista de tareas futuras. Las tareas se recuperan
     * de un árbol de búsqueda binario en modo de recorrido de orden directo y luego se ordenan en una lista
     * en orden ascendente según su comparación natural. Si no hay tareas futuras,
     * se devuelve un iterador para una lista vacía.
     *
     * @return un IteratorIF<TaskIF> que itera a través de las tareas futuras en orden ascendente.
     */
    public IteratorIF<TaskIF> iteratorFuture() {
        ListIF<TaskIF> orderedList = new List<TaskIF>();

        IteratorIF<TaskIF> it = futureTasks.iterator(BSTreeIF.IteratorModes.DIRECTORDER);
        if (it == null) {
            return orderedList.iterator();
        }

        while (it.hasNext()) {
            TaskIF task = it.getNext();
            int pos = 1;
            while (pos <= orderedList.size() && orderedList.get(pos).compareTo(task) < 0) {
                pos++;
            }
            orderedList.insert(pos, task);
        }
        return orderedList.iterator();
    }


    /**
     * Recupera una tarea de la lista de tareas futuras que coincida con la fecha especificada.
     *
     * @param date la fecha de la tarea a recuperar
     * @return la tarea que coincide con la fecha especificada, o nulo si no se encuentra ninguna tarea con la fecha dada
     */
    private TaskIF getTaskByDate(int date) {
        IteratorIF<TaskIF> it = futureTasks.iterator(BSTreeIF.IteratorModes.DIRECTORDER);
        while (it.hasNext()) {
            TaskIF task = it.getNext();
            if (task.getDate() == date) {
                return task;
            }
        }
        return null;
    }


    /**
     * Itera sobre la lista de tareas pasadas en orden ascendente según su orden natural.
     * El método crea una nueva lista ordenada de tareas pasadas y devuelve un iterador para recorrerla.
     *
     * @return un iterador sobre la lista ordenada de tareas pasadas
     */
    public IteratorIF<TaskIF> iteratorPast() {
        ListIF<TaskIF> orderedList = new List<TaskIF>();
        for (int i = 1; i <= pastTasks.size(); i++) {
            Task task = pastTasks.get(i);
            int pos = 1;
            while (pos <= orderedList.size() && orderedList.get(pos).compareTo(task) < 0) {
                pos++;
            }
            orderedList.insert(pos, task);
        }
        return orderedList.iterator();
    }

    /**
     * Recupera la siguiente tarea de la lista de tareas futuras con la fecha más temprana.
     *
     * El método itera por todas las tareas de la colección futureTasks utilizando un modo de iteración
     * de orden directo. Determina la tarea con la fecha más temprana comparando las tareas según su
     * propiedad de fecha. Si la colección futureTasks está vacía, devuelve nulo.
     *
     * @return la tarea con la fecha más temprana de la colección futureTasks, o nulo si no hay tareas presentes
     */
    private Task getNextTask() {
        Task minTask = null;
        IteratorIF<TaskIF> it = futureTasks.iterator(BSTreeIF.IteratorModes.DIRECTORDER);
        while (it.hasNext()) {
            Task current = (Task) it.getNext();
            if (minTask == null || current.compareTo(minTask) < 0) {
                minTask = current;
            }
        }
        return minTask;
    }

}
